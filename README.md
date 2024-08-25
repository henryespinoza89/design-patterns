## :speech_balloon: Patrones de diseño - Patrón Factory

Nos permite crear objetos sin especificar la clase exacta del objeto que se creará. Para lograr esto, los objetos se crean llamando a un método de fábrica en lugar de llamar a un constructor. Por lo general, la creación de objetos en Java se realiza así:
</br>
Falcon objeto = new Falcon();
</br>
El problema con el enfoque anterior es que el código que usa el objeto de Falcon, de repente ahora se vuelve dependiente de la implementación concreta de Falcon. No hay nada de malo en usar new para crear objetos, pero viene con el bagaje de acoplar estrechamente nuestro código a la clase de implementación concreta, lo que ocasionalmente puede ser problemático.

`Exercise 01`
1. De esta forma se puede crear y gestionar diferentes tipos de cuentas bancarias sin especificar clases concretas en el código cliente que maneja las cuentas. El cliente decide qué tipo de cuenta necesita (ahorro, corriente o inversión) mediante una cadena de entrada y usa getAccountFactory para obtener la fábrica correspondiente. Cada tipo de cuenta tiene su propia lógica para depósitos, retiros y cálculo de intereses, gestionada de manera transparente por la interfaz Account.
2. Este patrón permite a la fintech añadir nuevos tipos de cuentas sin alterar el código que las utiliza, haciendo el sistema más modular y fácil de expandir. Además, facilita la prueba de distintas configuraciones de cuenta en diferentes entornos o escenarios de uso sin cambios significativos en el código base.

> [!NOTE]
> De esta forma lo que hacemos es que el código cliente interactúa con el creador (Creator: SavingsAccountFactory) a través de una interfaz o clase abstracta, sin conocer detalles específicos sobre qué tipo de producto concreto (ConcreteProduct) será creado. Es decir, el cliente trabaja con la abstracción del producto, no con implementaciones concretas.

3. Clase creador: En el ejemplo proporcionado, aunque el cliente elige un ConcreteCreator específico (como SavingsAccountFactory o CheckingAccountFactory), lo importante es que el tipo de producto que recibe de estos creadores se maneja a través de la interfaz Account. De este modo, el cliente no necesita saber si está trabajando con un SavingsAccountFactory o un InvestmentAccountFactory; solo sabe que está manejando objetos que implementan la interfaz Account. Esto le permite operar de manera genérica sobre los productos, reduciendo el acoplamiento entre el cliente y las clases concretas de los productos.

```sh
design-patterns/src/main/java/com/hespinoza/designpatterns/fintech/factory/SavingsAccountFactory.java
```

> [!NOTE]
> Interfaces: Las interfaces son una forma de especificar que una clase debe implementar ciertos métodos, pero como las interfaces no pueden tener ninguna implementación concreta de métodos (hasta antes de Java 8, después se pueden tener métodos default y estáticos con implementaciones), tampoco se pueden instanciar. Las interfaces definen un contrato que las clases deben seguir, sin proporcionar ninguna funcionalidad concreta.

```sh
design-patterns/src/main/java/com/hespinoza/designpatterns/fintech/factory/AccountFactory.java
```

4. Clase abstracta Account: Son clases que no se pueden instanciar por sí mismas porque están diseñadas para ser clases base de otras clases. Una clase abstracta puede contener métodos abstractos (que no tienen implementación y deben ser implementados por las clases derivadas) así como métodos con implementación completa (métodos concretos). El propósito de una clase abstracta es proporcionar una plantilla o un esqueleto para clases derivadas que amplíen y concreten su funcionalidad.

```sh
design-patterns/src/main/java/com/hespinoza/designpatterns/fintech/domain/model/Account.java
```

`Beneficios de este enfoque`
1. Desacoplamiento: El cliente no depende de las clases concretas de los productos. Esto significa que puedes cambiar, agregar o modificar las clases de productos sin que el cliente necesite cambiar.
2. Flexibilidad: Puedes introducir nuevos tipos de productos (nuevos ConcreteProduct) y nuevos creadores (ConcreteCreator) sin alterar el código que los utiliza.
3. OJO: Aunque la elección de un ConcreteCreator específico implica conocer una clase concreta, este conocimiento se utiliza solamente para la configuración inicial y no afecta la flexibilidad y mantenibilidad del uso de los productos en sí, que siguen siendo manejados a través de una interfaz abierta y genérica. Esto permite que el sistema sea fácil de expandir y modificar.







