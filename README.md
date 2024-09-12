## :speech_balloon: Patrones de diseño - Strategy

Strategy es un patrón de diseño de comportamiento que te permite definir una familia de algoritmos, colocar cada uno de ellos en una clase separada y hacer sus objetos intercambiables. Es útil cuando tienes varias maneras de realizar una operación y deseas que el cliente pueda elegir cuál usar en tiempo de ejecución.

`Ejemplo 01`
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




