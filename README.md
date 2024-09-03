## :speech_balloon: Patrones de diseño - Patrón Factory

El patrón Factory es un patrón creacional de diseño que se utiliza para crear objetos sin exponer la lógica de creación al cliente y permite a las subclases modificar el tipo de objetos que se crean. Para este primer ejemplo, el patrón Factory se usa para crear diferentes tipos de cuentas (Account), como cuentas de ahorro, cuentas corrientes y cuentas de inversión, sin que el cliente necesite saber qué tipo específico de cuenta se está creando.
</br></br>
Por lo tanto, el patrón factory nos permite crear objetos sin especificar la clase exacta del objeto que se creará. Para lograr esto, los objetos se crean llamando a un método de fábrica en lugar de llamar a un constructor. Por lo general, la creación de objetos en Java se realiza así:
```sh
Falcon objeto = new Falcon();
```
El problema con el enfoque anterior es que el código que usa el objeto de Falcon, de repente ahora se vuelve dependiente de la implementación concreta de Falcon. No hay nada de malo en usar new para crear objetos, pero viene con el bagaje de acoplar estrechamente nuestro código a la clase de implementación concreta, lo que ocasionalmente puede ser problemático.

`Ejercicio 01`
1. De esta forma se puede crear y gestionar diferentes tipos de cuentas bancarias sin especificar clases concretas en el código cliente que maneja las cuentas. El cliente decide qué tipo de cuenta necesita (ahorro, corriente o inversión) mediante una cadena de entrada y usa getAccountFactory para obtener la fábrica correspondiente. Cada tipo de cuenta tiene su propia lógica para depósitos, retiros y cálculo de intereses, gestionada de manera transparente por la interfaz Account.
2. Este patrón permite a la fintech añadir nuevos tipos de cuentas sin alterar el código que las utiliza, haciendo el sistema más modular y fácil de expandir. Además, facilita la prueba de distintas configuraciones de cuenta en diferentes entornos o escenarios de uso sin cambios significativos en el código base.

> [!NOTE]
> De esta forma lo que hacemos es que el código cliente interactúa con el creador (Creator: SavingsAccountFactory) a través de una interfaz o clase abstracta, sin conocer detalles específicos sobre qué tipo de producto concreto (ConcreteProduct) será creado. Es decir, el cliente trabaja con la abstracción del producto, no con implementaciones concretas.

3. Clase creador: </br>
Cada tipo de cuenta tiene su propia fábrica que implementa la interfaz AccountFactory:
```sh
public class SavingsAccountFactory implements AccountFactory {
  @Override
  public Account createAccount() {
    return new SavingsAccountServiceImpl();
  }
}
```
Por ejemplo, SavingsAccountFactory es responsable de crear instancias de SavingsAccountServiceImpl.
</br></br>
En el ejemplo proporcionado, aunque el cliente elige un ConcreteCreator específico (como SavingsAccountFactory o CheckingAccountFactory), lo importante es que el tipo de producto que recibe de estos creadores se maneja a través de la interfaz Account. De este modo, el cliente no necesita saber si está trabajando con un SavingsAccountFactory o un InvestmentAccountFactory; solo sabe que está manejando objetos que implementan la interfaz Account (en resumen, en este punto, Account es implementado en función de quién lo llame). Esto le permite operar de manera genérica sobre los productos, reduciendo el acoplamiento entre el cliente y las clases concretas de los productos.

```sh
designpatterns/fintech/factory/SavingsAccountFactory.java
```

4. La interfaz AccountFactory: </br>
Define un método createAccount() que es implementado por las distintas fábricas concretas (SavingsAccountFactory, CheckingAccountFactory, InvestmentAccountFactory). Este método es responsable de crear y devolver instancias de Account.

```sh
designpatterns/fintech/factory/AccountFactory.java
```

```sh
public interface AccountFactory {
  Account createAccount();
}
```

> [!NOTE]
> Interfaces: Las interfaces son una forma de especificar que una clase debe implementar ciertos métodos, pero como las interfaces no pueden tener ninguna implementación concreta de métodos (hasta antes de Java 8, después se pueden tener métodos default y estáticos con implementaciones), tampoco se pueden instanciar. Las interfaces definen un contrato que las clases deben seguir, sin proporcionar ninguna funcionalidad concreta.

5. Clase abstracta Account: </br>
La clase abstracta Account define los métodos comunes que deben implementarse en todas las cuentas, como deposit, withdraw y calculateInterest. Cada tipo de cuenta (por ejemplo, SavingsAccountServiceImpl) implementa estos métodos de acuerdo con las reglas específicas de ese tipo de cuenta.

```sh
designpatterns/fintech/domain/model/Account.java
```

> [!NOTE]
> Son clases que no se pueden instanciar por sí mismas porque están diseñadas para ser clases base de otras clases. Una clase abstracta puede contener métodos abstractos (que no tienen implementación y deben ser implementados por las clases derivadas) así como métodos con implementación completa (métodos concretos). El propósito de una clase abstracta es proporcionar una plantilla o un esqueleto para clases derivadas que amplíen y concreten su funcionalidad.

6. Implementación de un tipo de cuenta: </br>
SavingsAccountServiceImpl es una implementación concreta de Account que representa una cuenta de ahorros. Tiene un balance y una tasa de interés, y los métodos deposit, withdraw y calculateInterest están implementados con lógica específica para una cuenta de ahorros.

```sh
designpatterns/fintech/application/impl/SavingsAccountServiceImpl.java
```

7. Uso del Patrón Factory
Finalmente, para crear una cuenta, se usa el método getAccountFactory que devuelve una fábrica específica en función del tipo de cuenta que se desea crear:
```sh
AccountFactory accountFactory = getAccountFactory("investment");
Account account = accountFactory.createAccount();
```
Este código selecciona la fábrica correcta (InvestmentAccountFactory) y crea una instancia de InvestmentAccountServiceImpl. Luego, puedes realizar operaciones en esta cuenta, como depósitos, retiros y calcular el interés.
</br></br>
En la primera línea estamos declarando una variable accountFactory de tipo AccountFactory, que puede contener cualquier objeto que implemente esa interfaz. Esto es posible porque las interfaces en Java pueden ser usadas como tipos de referencia, al igual que las clases.

> [!NOTE]
> Aunque AccountFactory es solo una interfaz y no puede ser instanciada directamente, una variable de tipo AccountFactory puede almacenar cualquier instancia de una clase que implemente esa interfaz. En este caso, SavingsAccountFactory, CheckingAccountFactory y InvestmentAccountFactory son clases que implementan la interfaz AccountFactory. Así que, cuando getAccountFactory("investment") es llamado, retorna un objeto del tipo InvestmentAccountFactory, que es una clase concreta, pero como esta clase implementa AccountFactory, es válido asignarla a una variable de tipo AccountFactory.

> [!NOTE]
> Una de las principales ventajas de usar interfaces como tipo de dato es que tu código se vuelve más flexible y menos dependiente de implementaciones concretas. Puedes cambiar la implementación de la fábrica (por ejemplo, usar una fábrica diferente) sin tener que cambiar el código que usa la fábrica. Esto es un principio fundamental en programación orientada a objetos: programar para interfaces, no para implementaciones.

`Beneficios de este enfoque`
1. Desacoplamiento: El cliente no depende de las clases concretas de los productos. Esto significa que puedes cambiar, agregar o modificar las clases de productos sin que el cliente necesite cambiar.
2. Flexibilidad: Puedes introducir nuevos tipos de productos (nuevos ConcreteProduct) y nuevos creadores (ConcreteCreator) sin alterar el código que los utiliza.
3. OJO: Aunque la elección de un ConcreteCreator específico implica conocer una clase concreta, este conocimiento se utiliza solamente para la configuración inicial y no afecta la flexibilidad y mantenibilidad del uso de los productos en sí, que siguen siendo manejados a través de una interfaz abierta y genérica. Esto permite que el sistema sea fácil de expandir y modificar.

`Ejercicio 02`
</br></br>
Iniciamos con algo similar al ejercicio anterior, la creación de enemigos para el videojuego dependenerá de la entrada que el cliente asigne al método, de la siguiente manera:

```sh
private static EnemyFactory getEnemyFactory(String type) {
  switch (type) {
    case "goomba":
      return new GoombaFactory();
    case "koopa":
      return new KoopaFactory();
    case "boo":
      return new BooFactory();
    default:
      throw new IllegalArgumentException("Unknown account type");
  }
}
```

Esto puede servir para ir agregando diferentes tipos de enemigos al juego ya se manera directa o aleatoria de acuerdo a sus características a medida que van avanzando en los niveles, por lo tanto, en nuestro videojuego tendremos un conjunto de clase donde cada uno será un tipo de enemigo (Goomba, Koopa y Boo) las cuales en nuestra aplicación son entidades lógicas del juego, entidades que hacen algo: se mueven, interaccionan con el jugador, con el mapa, las cuales van a heredar de la clase "Enemy" lo que genera que apliquemos polymorfismo por lo tanto cada vez que tengamos que actualizar el estado del juego podemos tratar a todas las entidades por igual. Si queremos generar enemigos de manera aleatoria en algún punto de nuestro código y/o función tendremos la lógica para crear nuestros enemigos. Y esto es lo que veremos a continuación de la siguiente manera:

```sh
public Enemy createEnemyRamdonFactory() {
  Enemy enemy;
  Double randomNum = Math.random();
  if (randomNum > 0.00 && randomNum < 0.30) {
    enemy = new Koopa();
  } else if (randomNum >= 0.30 && randomNum < 0.80) {
    enemy = new Goomba();
  } else {
    enemy = new Boo();
  }
  return enemy;
}
```

En este punto podemos plantearnos lo siguiente: Cómo damos prioridad a aparecer a los enemigos más poderosos sin modificar la lógica que ya tenemos implementado y sin que afecte a todos los jugadores por igual? ya que puede existir jugadores que están cómodos con la versión aleatoria de generar enemigos.
</br></br>
Tenemos que ENCAPSULAR LAS ESTRATEGIAS DE FABRICACIÓN de los enemigos. Aquí es donde entra a trabajar el PATRON FACTORY. La FACTORY sería la responsable de mantener la lógica encargada de construir enemigos, sería una clase que reutilizariamos y que podemos usar de manera POLYMORFICA. Todas las factories (GoombaFactory) siguen la misma interfaz porque todas ellas heredan de una abstracción que define el metodo creador de enemigos (EnemyFactory) a partir de acá si queremos incorporar un método creador de enemigos basta con crear una nueva clase factory (EnemyRandomFactory) que heredara la misma interfaz, implementando ese mecanismo en la clase createEnemyRamdonFactory().
</br></br>
Esto es muy importante y es lo que nos permite utilizar POLYMORFISMO ya que cada vez que necesitemos usar algo para crear enemigos vamos a usar la clase padre de la factory, por lo que podemos modificar las implementaciones concretas (Goomba, Koopa, Boo) cuando y como queramos, si queremos que ahora todos los enemigos sean Goomba, pues solo pasamos esa clase, si queremos todos los enemigos pero de manera aleatoria, pasamos EnemyRandomFactory entonces hemos generalizado la forma de crear enemigos a una interfaz general, de hecho no tenemos límtes aquí, podemos pasar parametros a una factory, % de cada enemigos, o pasar un minimo de cada enemigo para elegir enemigos a partir de eso. Todo esto es tan general que no afecta a ninguna otra parte del codigo ya que todas las dependencias son entre interfaces.

> [!NOTE]
> Puedes encontrar más información en el siguiente link: [Factory-method](https://refactoring.guru/es/design-patterns/factory-method)
> </br>
> Más información: [Factory-method-BettaTech](https://www.youtube.com/watch?v=lLvYAzXO7Ek&t=4s)
























