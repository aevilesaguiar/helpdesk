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





## Referencias


- https://www.devmedia.com.br/enums-no-java/38764
