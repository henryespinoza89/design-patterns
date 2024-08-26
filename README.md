## :speech_balloon: Patrones de diseño - Singleton

Singleton es un patrón de diseño creacional que nos permite asegurarnos de que una clase tenga una única instancia, a la vez que proporciona un punto de acceso global a dicha instancia.

`Contexto de Spring Framework`
1. <b>Singleton por defecto:</b> </br>
cuando declaras un componente (como un servicio, un controlador, un repositorio, etc.) en Spring utilizando anotaciones como @Component, @Service, @RestController, etc., Spring crea una única instancia de ese componente y la mantiene en su contexto de aplicación durante toda la vida del contexto de la aplicación.

2. <b>Inyección de dependencias:</b> </br>
Spring se encarga de inyectar las dependencias a través de la instancia singleton del componente. Si un componente A tiene una dependencia de otro componente B, Spring inyectará la misma instancia de B en todas las instancias de A que se creen.

3. <b>Scope de Singleton:</b> </br>
Este es el comportamiento por defecto para los componentes de Spring. Puedes cambiar el scope de un componente utilizando la anotación @Scope, pero si no se especifica, será singleton.

`Controller - Método: obtenerConfiguracion()`
</br></br>
Al acceder al endpoint obtendremos la información de la configuración de la aplicación que se gestiona a través de la instancia única de SingletonConfig. Puedes modificar la configuración en tiempo de ejecución y verificar que la instancia única mantiene los cambios en toda la aplicación.

`Singleton - SingletonCounter`
</br></br>
Esta es una clase que representa un contador y asegura que solo haya una instancia de ese contador en toda la aplicación. Se define una variable estática instance que almacenará la única instancia de la clase.
</br></br>
Creamos un constructor privado para evitar instanciación externa y un método estático para obtener la única instancia.
</br>
Otros casos de uso: configuración de la aplicación, mantener una conexión de base de datos, etc.
</br>

> [!IMPORTANT]
Para que Spring administre SingletonCounter como un Singleton simplemente agregamos la anotación @Component a la clase.

`Singleton - SingletonConfig`
</br></br>
Private: Al definir la variable instance como private, se asegura que solo la propia clase SingletonConfig pueda acceder y modificar esta variable. Esto es importante para mantener el control sobre la instancia única de la clase y prevenir que otras clases creen instancias adicionales de SingletonConfig.
</br></br>
Static: Al definir la variable instance como static, se asegura que la misma instancia de SingletonConfig esté disponible para todas las instancias de la clase. Esto significa que no importa cuántas veces se cree un objeto SingletonConfig, siempre se referirá a la misma instancia única. Esto es fundamental para el patrón Singleton, ya que su objetivo es garantizar que solo haya una instancia de la clase en toda la aplicación.
</br></br>
Al combinar private y static, se logra el comportamiento deseado de tener una única instancia de SingletonConfig que sea accesible desde cualquier parte de la aplicación, pero que esté controlada por la propia clase SingletonConfig. Esto asegura que no haya múltiples instancias de la clase y que se pueda acceder a la instancia única de manera controlada a través de métodos estáticos de la clase.
</br></br>
Explicación adicional: En esta clase SingletonConfig, la variable instance se declara como static. Esto significa que la variable pertenece a la clase en lugar de pertenecer a cada instancia individual de la clase. Por lo tanto, todas las instancias de la clase SingletonConfig comparten la misma variable instance. Dado que instance es static, se puede acceder a ella utilizando la clase en lugar de una instancia específica. Esto significa que, independientemente de cuántas veces se cree un objeto SingletonConfig, siempre se referirá a la misma instancia única almacenada en instance.
</br></br>
`Ejemplo`
</br></br>
Obtenemos la instancia única de SingletonConfig:</br>
```sh
SingletonConfig config1 = SingletonConfig.getInstance();
SingletonConfig config2 = SingletonConfig.getInstance();
```
Ambas instancias hacen referencia a la misma instancia única
</br>
```sh
System.out.println(config1 == config2); // Esto imprimirá "true"
```
En este código, config1 y config2 son dos instancias separadas de SingletonConfig, pero al compararlas con ==, vemos que apuntan a la misma dirección de memoria, lo que significa que son la misma instancia. Esto demuestra que instance es compartida entre todas las instancias de la clase SingletonConfig, y por lo tanto, todas ellas acceden a la misma instancia única de la clase.

El constructor en el ejemplo de SingletonConfig está definido como private por las siguientes razones:
```sh
private SingletonConfig() {
  appName = "Mi Aplicación";
  maxConnections = 100;
}
```
1. <b>Evitar la instanciación externa:</b> Al hacer que el constructor sea privado, se evita que otras clases creen instancias de SingletonConfig utilizando new SingletonConfig(). Esto garantiza que la única instancia de SingletonConfig se cree y gestione internamente por la propia clase.
2. <b>Controlar la creación de la instancia:</b> Al tener un constructor privado, la única manera de crear una instancia de SingletonConfig es a través del método getInstance(), que es un método estático de la clase. Este método implementa la lógica para crear y devolver la única instancia de la clase, asegurando que se mantenga la unicidad de la instancia.
3. <b>Prevenir la herencia no deseada:</b> Al hacer que el constructor sea privado, se impide que otras clases hereden de SingletonConfig y creen sus propias instancias. Esto garantiza que la única instancia de SingletonConfig sea verdaderamente única en toda la aplicación.
```sh
public static synchronized SingletonConfig getInstance() {
  if (instance == null) {
    instance = new SingletonConfig();
  }
  return instance;
}
```
La razón principal por la que el método getInstance() está definido como static es porque este método es el punto de entrada para obtener la instancia única de la clase SingletonConfig, y queremos acceder a este método sin necesidad de crear una instancia previa de la clase.
</br></br>
Acceso sin instancia: Al definir getInstance() como static, podemos llamar a este método directamente desde cualquier parte del código sin necesidad de tener una instancia de SingletonConfig. Esto es útil ya que getInstance() es el punto de acceso principal para obtener la instancia única de la clase.
</br></br>
Unicidad de la instancia: La variable instance en la clase SingletonConfig también está definida como static, lo que significa que es compartida entre todas las instancias de la clase y solo hay una instancia única de SingletonConfig en toda la aplicación. Por lo tanto, al llamar a getInstance(), siempre obtendremos la misma instancia única de SingletonConfig, garantizando así la aplicación del patrón Singleton.
</br></br>
Sincronización y concurrencia: En este caso, también se ha agregado synchronized al método getInstance() para manejar la concurrencia en entornos multihilo. Al hacer que getInstance() sea static synchronized, garantizamos que solo se pueda crear una instancia única de SingletonConfig incluso en un entorno multihilo, evitando así problemas de concurrencia como la creación de múltiples instancias en paralelo.
</br></br>
En resumen, al definir getInstance() como static, permitimos un acceso fácil y global a la instancia única de SingletonConfig, lo que asegura que solo haya una instancia de la clase en toda la aplicación, y al hacerlo synchronized, garantizamos la seguridad en entornos multihilo.
</br></br>
Este comportamiento es muy útil en la mayoría de los casos, ya que asegura que las instancias de los componentes gestionados por Spring sean compartidas por toda la aplicación y evita problemas de inconsistencia de estado. Sin embargo, en algunos casos, como la gestión explícita de instancias únicas fuera del contexto de Spring o para componentes que necesitan un alcance diferente (como el alcance de sesión en aplicaciones web), puedes necesitar un enfoque diferente. En esos casos, puedes optar por utilizar el patrón Singleton de forma explícita, como se muestra en los ejemplos anteriores.

> [!NOTE]
> Puedes encontrar más información en el siguiente link: [Singleton](https://refactoring.guru/es/design-patterns/singleton)
