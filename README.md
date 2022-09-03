# Sistema Help Desk

![image](https://user-images.githubusercontent.com/52088444/187006050-a16ba0bf-cab3-4c28-9187-5da0f072695e.png)

## Iniciaremos o desenvolvimento pelos os Enums

**Em Java, uma enum é um tipo no qual declaramos um conjunto de valores constantes pré-definidos** . Sua sintaxe é muito semelhante à declaração de uma classe, exceto pelo uso da palavra chave **enum** que antecede seu nome.

- Classes enum e herança

  Ao declarar uma enum estamos implicitamente estendendo a classe **java.lang.Enum**. Isso cria algumas limitações, porque o Java não suporta herança múltipla, o que impede uma **classe enum** de estender outras classes. Porém, uma classe enum pode ter propriedades, assim como um construtor e métodos getters.
- **O construtor de uma enum é sempre privado** , não podendo ser invocado diretamente. Nele são iniciados todos os campos, que por serem constantes devem declarados antes das propriedades e do construtor da classe.
- Deve ser evitado criar setters para as propriedades de uma enum, pois isso vai de encontro com sua caracterísitca imutável.
- Métodos herdados de Enum

  Um dos mais utilizados dentre eles é **values()**, que retorna um array contendo todos os campos da enum.

  No exemplo a seguir imprimimos a descrição de cada um dos campos da enum Turno:

  for (Turno t : Turno.values()) {

System.out.println(t.getDescricao());
}

![image](https://user-images.githubusercontent.com/52088444/187006036-395f36b6-6519-42ec-af00-616399fa1bf0.png)

## Criação da classe Pessoa

A classe Pessoa é herdada pela classe Cliente e Tecnico. A classe Pessao será abstrata , não poderemos criar instancia de Pessoa e sim de Tecnico e Cliente( verificar UML no inicio do projeto), não teremos objetos de Pessoa. Quando quisermos criar um usuario ele será um cliente ou um técnico.

```
public abstract class Pessoa {
}

```

Os atributos da classe Pessoa não podem ser private , eleas deverão ser protected, quando os atributos forem privates isso significa que só a classe Pessoa terá acesso aos atributos, por isso utilizaremos protected, protected todas as classes filhas de Pessoa como tecnico e Cliente terão acesso.

## Implements "Serializable"

Serializable Serve para seja criado uma sequencia de bytes das instancias da nossa classe para que possam ser trafegados em rede,
podem ser armazenados em arquivos de memória, pode ser descerealizados e recuperadas em memória posteriormente

## Padrão de camadas

![](.README_images/0112c4bc.png)

Nós temos a nossa aplicação cliente que fará uma requisição ao back end. A camada do backEnd  que vai receber o primeiro
contato da nossa aplicação cliente é a camada de controladores/camada REST/resources ela se comunica com a camada de serviços que é onde fica
implementado as nossas lógicas de negócios, que tem contato com a camada de acesso a dados que são os repositorys,
e os repositorys vão servir para fazer a persistencia das informações no nosso banco.

## implements CommandLineRunner

O CommandLineRunner ele tem um método run, e ele roda toda vez que eu startar a aplicação.

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

//Não é uma boa prática fazer dessa forma a inclusão dos dados
//injentando dependencias em deteminado trecho de código
@Autowired
private TecnicoRepository tecnicoRepository;
@Autowired
private ClienteRepository clienteRepository;
@Autowired
private ChamadoRepository chamadoRepository;
public static void main(String[] args) {
	SpringApplication.run(HelpdeskApplication.class, args);
}

@Override
public void run(String... args) throws Exception {

	Tecnico tec1= new Tecnico(null,"Aeviles Aguiar","111.123.222-89","aasemail@email.com","123");
	tec1.addPerfil(Perfil.ADMIN);


	Cliente cli1= new Cliente(null,"Mark Zukemberg","123.456.789-00","mark@email.com","123");
	cli1.addPerfil(Perfil.CLIENTE);

	Chamado c1= new Chamado(null, Prioridade.MEDIA,Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado",tec1,cli1);

	tecnicoRepository.saveAll(Arrays.asList(tec1));
	clienteRepository.saveAll(Arrays.asList(cli1));
	chamadoRepository.saveAll(Arrays.asList(c1));

}
}

## Usando Spring ResponseEntity para manipular a resposta HTTP

ResponseEntity representa toda a resposta HTTP: código de status, cabeçalhos e corpo . Como resultado, podemos usá-lo para configurar totalmente a resposta HTTP.

Se quisermos usá-lo, temos que devolvê-lo do endpoint;

ResponseEntity é um tipo genérico. Consequentemente, podemos usar qualquer tipo como o corpo da resposta.
Como especificamos o status da resposta programaticamente, podemos retornar com códigos de status diferentes para diferentes cenários.
Além disso, podemos definir cabeçalhos HTTP

Além disso, ResponseEntity fornece duas interfaces de construtor aninhadas : HeadersBuilder e sua subinterface, BodyBuilder . Portanto, podemos acessar seus recursos por meio dos métodos estáticos de ResponseEntity .

O caso mais simples é uma resposta com um corpo e um código de resposta HTTP 200.

Para os códigos de status HTTP mais populares , obtemos métodos estáticos:
Ao retornar o objeto ResponseEntity<T> do controlador, podemos obter uma exceção ou erro ao processar a solicitação e gostaríamos de retornar informações relacionadas ao erro para o usuário representado como algum outro tipo, digamos E .

O Spring 3.2 traz suporte para um  @ExceptionHandler  global com a nova  anotação @ControllerAdvice, que  lida com esses tipos de cenários. Para obter detalhes aprofundados, consulte nosso artigo existente aqui .

Embora ResponseEntity seja muito poderoso, não devemos usá-lo em demasia. Em casos simples, existem outras opções que atendem às nossas necessidades e resultam em um código muito mais limpo.

## Alternativas

@ResponseBody
Em aplicativos Spring MVC clássicos, os endpoints geralmente retornam páginas HTML renderizadas. Às vezes, precisamos apenas retornar os dados reais; por exemplo, quando usamos o endpoint com AJAX.

Nesses casos, podemos marcar o método do manipulador de solicitação com @ResponseBody e o Spring trata o valor do resultado do método como o próprio corpo da resposta HTTP .

Para obter mais informações, este artigo é um bom ponto de partida .

@ResponseStatus
Quando um endpoint retorna com sucesso, o Spring fornece uma resposta HTTP 200 (OK). Se o endpoint lançar uma exceção, o Spring procurará um manipulador de exceção que informe qual status HTTP usar.

Podemos marcar esses métodos com @ResponseStatus e, portanto, o Spring retorna com um status HTTP personalizado .

Manipule a resposta diretamente
O Spring também nos permite acessar o objeto javax.servlet.http.HttpServletResponse diretamente; temos apenas que declará-lo como um argumento de método:

@GetMapping("/manual")
void manual(HttpServletResponse response) throws IOException {
response.setHeader("Custom-Header", "foo");
response.setStatus(200);
response.getWriter().println("Hello World!");
}
Como o Spring fornece abstrações e recursos adicionais acima da implementação subjacente, não devemos manipular a resposta dessa maneira .

De acordo com a documentação oficial: Criando controladores REST com a anotação @RestController

@RestController é uma anotação de estereótipo que combina @ResponseBody e @Controller. Mais do que isso, dá mais significado ao seu Controller e também pode trazer semânticas adicionais em futuras versões do framework.

Parece que é melhor usar @RestControllerpara maior clareza, mas você também pode combiná -lo com ResponseEntityflexibilidade quando necessário ( de acordo com o tutorial oficial e o código aqui e minha pergunta para confirmar isso ).

Por exemplo:

@RestController
public class MyController {

@GetMapping(path = "/test")
@ResponseStatus(HttpStatus.OK)
public User test() {
    User user = new User();
    user.setName("Name 1");

    return user;
}
}
é o mesmo que:

@RestController
public class MyController {

@GetMapping(path = "/test")
public ResponseEntity<User> test() {
    User user = new User();
    user.setName("Name 1");

    HttpHeaders responseHeaders = new HttpHeaders();
    // ...
    return new ResponseEntity<>(user, responseHeaders, HttpStatus.OK);
}
}
Dessa forma, você pode definir ResponseEntityapenas quando necessário.

Atualizar

Você pode usar isso:

return ResponseEntity.ok().headers(responseHeaders).body(user);
## entendendo

Como já dito, JPA é somente a tecnologia de acesso ao banco de dados. Com ele você pode usar os Design Patterns
(padrão de design) que não fazem parte do JPA especificamente.

Repository: é um Design Pattern onde os dados são obtidos do banco de dados e ocorre também a regra de negócio.
Este retorna objetos de domínio que seriam as Entidades (classes anotadas com @Entity).

DAO: é outro Design Pattern onde somente há a comunicação com o banco de dados sem regra de negócio.

Service: seria outro Desing Pattern onde há somente a regra de negócio e não tem acesso direto ao banco de dados.

Controller: Ele é utilizado para lidar com a ligação da View com as outras partes do sistema que são a regra de negócio e banco de dados.

## Observações

@JsonIgnore é usado no nível do campo para marcar uma propriedade ou lista de propriedades a serem ignoradas.

## coletar e converter um fluxo em um conjunto - usando a API de fluxos do Java 8, com a Collectorsclasse.

Uma stream representa uma sequência de elementos e suporta diferentes tipos de operações que levam ao resultado desejado.
A origem de uma stream geralmente é uma Collection ou um array,  a partir do qual os dados são transmitidos.

Os streams diferem das  collections de várias maneiras; mais notavelmente porque os fluxos não são uma estrutura de
dados que armazena elementos. Eles são funcionais por natureza e vale a pena observar que as operações em um fluxo
produzem um resultado e normalmente retornam outro fluxo, mas não modificam sua origem.
Para "solidificar" as alterações, você coleta os elementos de um fluxo de volta em um arquivo Collection.

## Collectors e Stream.collect()

Os coletores representam implementações da Collectorinterface, que implementa várias operações de redução úteis,
como acumular elementos em coleções, resumir elementos com base em um parâmetro específico etc.

Todas as implementações predefinidas podem ser encontradas dentro da Collectorsclasse.

Você também pode facilmente implementar seu próprio collector e usá-lo em vez dos predefinidos - você pode ir muito longe
com os collectors internos, pois eles cobrem a grande maioria dos casos em que você pode querer usá-los.

Para poder usar a classe em nosso código, precisamos importá-la:

## Guia para Collectors.toSet()

O toSet()método é usado para coletar um fluxo em um conjunto. Ele funciona de maneira semelhante ao toList()método, mas,
em última análise, coleta em uma estrutura de dados subjacente diferente, retornando um Collectorque acumula os elementos
de entrada em um novo arquivo Set.

Vale a pena notar que não há garantias sobre o tipo, mutabilidade, serialização ou segurança de thread do Setretornado:

public static <T> Collector<T,?,Set<T>> toSet()

A Setnão permite elementos duplicados ou em termos mais formais - conjuntos não contêm nenhum par de elementos a e b tal
que a.equals(b), e pode conter no máximo um null elemento.

Se você coletar um fluxo com elementos duplicados em um Set- é uma maneira rápida de remover duplicatas:

Stream<String> stream =
Stream.of("This", "forms", "forms", "a", "short", "a", "sentence", "sentence");
Set<String> sentenceSet = stream.collect(Collectors.toSet());

No entanto, este exemplo destaca uma característica importante de como os conjuntos são preenchidos - os elementos não
mantêm sua ordem relativa quando coletados, como fazem, digamos, no toList()coletor. Isso ocorre porque a implementação
padrão de a Seté a HashSet, que ordena os elementos com base em seus hashes e nem garante a consistência dessa ordem ao
longo do tempo.

A execução deste trecho de código resulta em:

[sentence, a, This, short, forms]


## Autenticação com JWT(JSON Web Token)

**JWT** ou **JSON Web Token** é um padrão da indústria definido pela RFC7519 que tem como objetivo transmitir ou armazenar de forma compacta e segura objetos JSON entre diferentes aplicações. O **JWT** é digitalmente assinado usando uma chave secreta com o algoritmo HMAC ou um par de chaves pública e privada RSA ou ECDSA.


JWT, sigla para JSON Web Token, é uma técnica definida na RFC 7519 para autenticação remota entre duas partes. Ele é uma das formas mais utilizadas para autenticar usuários em APIs RESTful.


No exemplo da **Figura 1** vemos um cliente que enviará uma requisição HTTP ao endpoint de autenticação de uma API. Nela o cliente envia, no corpo da requisição dados como endereço de e-mail e senha.

![image.png](assets/image.png?t=1662135806468)

**Figura 1** . Cliente enviando requisição com dados de autenticação


Uma vez que os dados enviados pelo cliente tenham sido autenticados no servidor, este **criará um token JWT** assinado com um segredo interno da API e enviará este token de volta ao cliente, como mostra a  **Figura 2** .

![image.png](assets/image.png?t=1662135862761)


Provido com o token autenticado, o cliente possui acesso aos endpoints da aplicação que antes lhes eram restritos. Para realizar esse acesso, conforme mostra a **Figura 3** é preciso informar esse token no header Authorization da requisição e, por convenção, após a palavra **Bearer**.


![image.png](assets/image.png?t=1662135903712)



### Como é por dentro?

Um JSON Web Token é composto por três componentes básicos: HEADER, PAYLOAD e SIGNATURE. Vamos passar por cada um desses componentes com um exemplo.

#### Header

É o cabeçalho do token e contém dois campos: **alg**, que informa o algoritmo usado para criar a hash da assinaturas; e **typ**, que indica que este se trata de um token JWT.

```
{
    "alg": "HS256",
    "typ": "JWT"
}
```

#### Payload

É o componente onde se encontram os dados referentes à própria autenticação.

```
{
    "email": "aylan@boscarino.com",
    "password": "ya0gsqhy4wzvuvb4"
}
```

#### Signature

É a assinatura única de cada token que é gerada a partir de um algoritmo de criptografia e tem seu corpo com base no header, no payload e no segredo definido pela aplicação. No próximo tópico entraremos em mais detalhes.


# Autenticação x Autorização

-Autenticação

A autenticação verifica a identidade digital do usuário, ou seja, processo de verificação de uma identidade. Em termos mais simples, é quando o usuário prova de fato quem ele é.

Um exemplo bem comum de autenticação é a combinação Username e Password (Usuário e senha).

Desta forma, ao logar-se em qualquer sistema que necessite deste procedimento, o usuário está passando por um processo de autenticação. Porém, não é apenas este procedimento que autentica um usuário.

Podemos citar como exemplos:

* Validações por token;
* CPF e senha;
* E-mail e senha;
* Certificado digital, entre outros.

Autorização

Por sua vez, a autorização é o processo que ocorre após ser validada a autenticação. Diz respeito aos privilégios que são concedidos a determinado usuário ao utilizar uma aplicação.

Serve para verificar se determinado usuário terá a permissão para utilizar, executar recursos ou manipular determinadas ações, que é de fundamental importância dentro de uma aplicação.

Um exemplo que podemos atribuir a autorização é o uso de um ERP de uma determinada empresa.

Após realizar a autenticação no sistema, o usuário do financeiro terá acesso apenas aos módulos correspondentes à realização de seu trabalho, como contas a pagar, contas a receber, etc.

## Incluir as dependencias do Spring Security

```
<!-- Security -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>
	</dependencies>
```
Se eu incluir apenas essas dependencias no meu pom, quando faço uma requisição para listar ele retorna 401 não autorizado. Ou seja ele bloqueia todos os meus endpoints.


## Cross-origin resource sharing(Compartilhamento de recursos entre origens)


Depois que incluimos as dependencias nossos endpoints estão todos bloqueados, não recebemos mais requisições, então temos que liberar o acesso, liberar as requisições para o back end, e isso tem que ser feito de forma explicita no spring.


O compartilhamento de recursos entre origens é um mecanismo que permite que recursos restritos em uma página da Web sejam solicitados de outro domínio fora do domínio do qual o primeiro recurso foi servido. Uma página da web pode incorporar livremente imagens de origem cruzada, folhas de estilo, scripts, iframes e vídeo


```
Por padrão, o CorsConfiguration não permite solicitações de origem cruzada e deve ser configurado explicitamente. Use esse método para alternar para os padrões que permitem todas as solicitações de origem cruzada para GET, HEAD e POST, mas sem substituir nenhum valor que já tenha sido definido.
Os seguintes padrões são aplicados para valores que não estão definidos:
Permitir todas as origens com o valor especial "*" definido na especificação CORS. Isso é definido apenas se nem origens nem originPatterns já estiverem definidos.
Permitir métodos "simples" GET, HEAD e POST.
Permitir todos os cabeçalhos.
```

Basicamente quando você coloca a anotação @Bean, você está dizendo pro Spring que quer criar esse objeto e deixar ele disponível para outras classes utilizarem ele como dependência, por exemplo.

Se a anotação @Bean está no método, é importante que a classe em si tenha alguma anotação que indique pro Spring que a classe deve ser "processada". Pode colocar, por exemplo a anotação @Component na classe em si. Sem essa anotação o método não é invocado.

Para os parâmetros, ele vai buscar algum bean que satisfaça o parâmetro, e vai dar erro se não encontrar, ou se encontrar duas definições para o mesmo parâmetro. É meio que assim que ele vai saber "qual é o certo".

## Contratos que serão habilitados

A classe UserSecuritySS deve implementar interfaces UserDetais e implemenatr os contratos, e as mesmas podem ser implementadas de acordo com a regra de negócio do seu cliente



## Referencias

- https://www.devmedia.com.br/enums-no-java/38764
- https://wpsilva.medium.com/utilizando-banco-de-dados-h2-com-spring-de-forma-r%C3%A1pida-e-simples-6d896e15a4af
- https://www.baeldung.com/spring-response-entity
- https://stackabuse.com/guide-to-java-8-collectors-toset/
- https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonignore.htm
- https://www.devmedia.com.br/como-o-jwt-funciona/40265
