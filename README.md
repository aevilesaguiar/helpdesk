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


## Referencias

- https://www.devmedia.com.br/enums-no-java/38764
- https://wpsilva.medium.com/utilizando-banco-de-dados-h2-com-spring-de-forma-r%C3%A1pida-e-simples-6d896e15a4af
- https://www.baeldung.com/spring-response-entity