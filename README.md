## :speech_balloon: Patrones de diseño - Strategy

Strategy es un patrón de diseño de comportamiento que te permite definir una familia de algoritmos, colocar cada uno de ellos en una clase separada y hacer sus objetos intercambiables. Es útil cuando tienes varias maneras de realizar una operación y deseas que el cliente pueda elegir cuál usar en tiempo de ejecución.

`Ejemplo 01 - Uso de @Service y Setter Injection`
1. <b>Definir la Interfaz Estrategia:</b> </br>
Primero, define una interfaz que representará el comportamiento común para todas las estrategias de pago.</br>

```sh
PaymentStrategy.java
```
2. <b>Crear las Estrategias Concretas:</b> </br>
A continuación, implementa las diferentes estrategias de pago, como CreditCardStrategy y PayPalStrategy.</br>

```sh
CreditCardStrategy.java
```
```sh
PayPalStrategy.java
```
3. <b>Crear el Servicio para Procesar Pagos:</b> </br>
El servicio será responsable de elegir la estrategia adecuada para procesar el pago.</br>

```sh
PaymentService.java
```
> [!NOTE]
> <b>Setter Injection:</b></br>
> <b>Inyección de Dependencias:</b> En este enfoque, la inyección se realiza a través de un método setter (setPaymentStrategy). Spring llamará a este setter después de crear el bean para inyectar la dependencia PaymentStrategy.</br></br>
> <b>Flexibilidad:</b> Permite cambiar la estrategia de pago en tiempo de ejecución, ya que el setter puede ser llamado en cualquier momento para actualizar la dependencia.</br></br>
> <b>Posible NullPointerException:</b> Si el paymentStrategy no se establece antes de llamar a processPayment, se lanzará una excepción IllegalStateException. Esto puede llevar a errores si el setter no se llama correctamente.</br></br>
> <b>Inicialización Tardía:</b></br>
> <b>Dependencia Opcional:</b> En este caso, la dependencia no se inyecta en el momento de la creación del bean, lo que significa que el servicio puede estar en un estado incompleto si el setter no se ha llamado.</br></br>
> <b>Seguridad:</b> No se garantiza que el paymentStrategy esté inicializado en el momento en que se necesite.

4. <b>Clase Principal (Main) para Ejecutar el Proyecto:</b> </br>
Esta clase actuará como la clase de arranque de tu aplicación y también como el cliente que selecciona y ejecuta una estrategia de pago.</br></br>
Cuando la aplicación arranca y se crea el PaymentService como un bean de Spring, la variable private PaymentStrategy paymentStrategy; no tiene un valor por defecto asignado automáticamente. </br> De manera predeterminada, si no le pasas nada a esta variable, su valor será null.</br>
Esto significa que: </br>
No hay una estrategia concreta asignada a paymentStrategy cuando el bean de PaymentService es creado.</br>
Depende completamente de lo que le pases en el método setPaymentStrategy().</br></br>
Si no configuras una estrategia mediante el método setPaymentStrategy() antes de invocar processPayment(), se lanzará una excepción, ya que en processPayment() tienes esta verificación, esto asegura que el servicio no funcione sin una estrategia asignada.

```sh
Main.java
```

</br>

`Ejemplo 02 - Uso de @RequiredArgsConstructor con Constructor Injection`
1. <b>Definir la Interfaz Estrategia:</b> </br>
Este será el contrato que las distintas estrategias de pago deberán implementar.

```sh
PaymentStrategy.java
```
2. <b>Implementar las diferentes estrategias:</b> </br>
Ahora, creamos diferentes implementaciones de la interfaz PaymentStrategy.

```sh
CreditCardPaymentStrategy.java
```
```sh
PaypalPaymentStrategy.java
```
```sh
BankTransferPaymentStrategy.java
```

3. <b>Crear el servicio PaymentService que gestionará las estrategias:</b> </br>
Este servicio será el que elija y aplique la estrategia adecuada según el tipo de pago. Usaremos @RequiredArgsConstructor para que Spring inyecte automáticamente todas las implementaciones de PaymentStrategy en una lista.

4. <b>Controlador para interactuar con el servicio PaymentService:</b> </br>
Ahora, agregamos un controlador para interactuar con nuestro servicio y probar el patrón Strategy.

```sh
PaymentController.java
```

> [!NOTE]
> <b>Inyección de dependencias:</b></br>
> Usamos @RequiredArgsConstructor para que Spring inyecte todas las implementaciones de PaymentStrategy en la lista paymentStrategies.</br></br>
> <b>Selector de estrategia:</b></br>
> El método processPayment en PaymentService itera sobre las implementaciones de PaymentStrategy y selecciona la correcta en función del parámetro paymentType.</br></br>
> <b>Controlador:</b></br>
> El controlador expone una API que permite elegir el tipo de pago y la cantidad, invocando el servicio para procesar el pago con la estrategia adecuada.

> [!NOTE]
> Si accedes a la URL http://localhost:8080/pay?paymentType=creditcard&amount=100, usarás la estrategia CreditCardPaymentStrategy.</br></br>
> Si accedes a http://localhost:8080/pay?paymentType=paypal&amount=100, usarás la estrategia PaypalPaymentStrategy.</br></br>
> Y para http://localhost:8080/pay?paymentType=banktransfer&amount=100, usarás BankTransferPaymentStrategy.

> [!NOTE]
> <b>Instanciación de Beans:</b></br>
> Cuando la aplicación Spring Boot arranca, Spring crea instancias de todos los beans anotados con @Component, @Service, @Repository, etc., que implementan la interfaz PaymentStrategy, dichas instancias se crean en tiempo de ejecución.</br></br>
> <b>Inyección de Dependencias:</b></br>
> Con la anotación @RequiredArgsConstructor, Spring inyecta una lista de todas las implementaciones de PaymentStrategy en el campo paymentStrategies del PaymentService. Esta inyección ocurre en tiempo de ejecución cuando Spring configura el contexto de aplicación.</br></br>
> <b>Selección en Tiempo de Ejecución:</b></br>
> En el método processPayment del PaymentService, la selección de la estrategia adecuada ocurre en tiempo de ejecución. El código itera sobre la lista de estrategias inyectadas y selecciona la que corresponde al tipo de pago solicitado en el parámetro paymentType.

> [!NOTE]
> <b>Uso de @RequiredArgsConstructor:</b></br></br>
> <b>Inyección de Dependencias:</b></br>
> @RequiredArgsConstructor genera un constructor que inyecta todas las dependencias necesarias (en este caso, la lista de PaymentStrategy) automáticamente. Esto elimina la necesidad de escribir manualmente un constructor con parámetros para la inyección de dependencias.</br></br>
> <b>Instanciación Automática:</b></br>
> Cuando Spring crea una instancia de PaymentService, automáticamente inyecta la lista de todas las implementaciones de PaymentStrategy en el constructor generado por @RequiredArgsConstructor.</br></br>
> <b>Inyección por Constructor:</b></br>
> Las dependencias se inyectan a través del constructor, asegurando que el servicio esté completamente inicializado con todas las estrategias necesarias en el momento de la creación.

> [!NOTE]
> <b>Sin @RequiredArgsConstructor:</b></br></br>
> <b>Constructor Manual:</b></br>
> Aquí, el constructor que inyecta la lista de PostProcessingRuleFilter se escribe manualmente. Spring inyectará esta lista cuando cree una instancia del PostProcessingRuleManager.</br></br>
> <b>Inyección por Constructor:</b></br>
> Al igual que en el primer ejemplo, las dependencias se inyectan a través del constructor. Spring asegura que el PaymentService reciba una lista completa de PaymentStrategy durante la instanciación.

> [!NOTE]
> <b>Diferencias y Comparación</b></br></br>
> <b>Conveniencia y Reducción de Código:</b></br>
> . <b>@RequiredArgsConstructor:</b> Simplifica el código al generar automáticamente un constructor para inyección de dependencias. Esto reduce la cantidad de código manual que debes escribir.</br>
> . <b>Constructor Manual:</b> Requiere que escribas el constructor tú mismo, lo que puede ser más detallado pero también más verboso.</br></br>
> <b>Inyección de Dependencias:</b></br>
> En ambos casos, las dependencias se inyectan por el constructor. La diferencia principal radica en cómo se maneja la generación del constructor.</br></br>
> <b>Manejo de Componentes:</b></br>
> Las implementaciones de la interfaz (PaymentStrategy) está anotadas con @Component, lo que permite que Spring las descubra y las gestione como beans. Esto asegura que todas las instancias necesarias se inyecten automáticamente en los servicios que las requieren.

> [!NOTE]
> <b>Constructor Injection</b></br></br>
> <b>Inyección de Dependencias:</b></br>
> Spring inyectará todas las implementaciones de PaymentStrategy a través del constructor, gracias a la anotación @RequiredArgsConstructor. Esto asegura que el PaymentService esté completamente inicializado con las estrategias de pago necesarias desde el principio.</br></br>
> <b>Inmutabilidad:</b></br>
> Las dependencias se establecen en el momento de la creación del bean y no pueden ser modificadas después. Esto promueve un diseño más seguro y predecible.
>
> <b>Inicialización Temprana</b></br></br>
> <b>Dependencia Obligatoria:</b></br>
> El constructor asegura que todas las dependencias se inyecten antes de que el bean se utilice. Esto evita errores de estado incompleto.</br></br>
> <b>Seguridad:</b></br>
> Se garantiza que el bean siempre estará en un estado consistente y completamente inicializado.

> [!NOTE]
> <b>Recomendación</b></br>
> En general, la inyección por constructor (como en el segundo ejemplo) es preferida por ser más segura y robusta, especialmente en aplicaciones que requieren garantizar la integridad del estado de los objetos. La inyección por setter puede ser útil en casos donde se necesita flexibilidad para cambiar dependencias en tiempo de ejecución, pero conlleva riesgos adicionales que deben ser manejados adecuadamente.






