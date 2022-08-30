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


## Referencias

- https://www.devmedia.com.br/enums-no-java/38764
