package com.hespinoza.designpatterns;

import com.hespinoza.designpatterns.bettatech.domain.model.Enemy;
import com.hespinoza.designpatterns.bettatech.factory.EnemyFactory;
import com.hespinoza.designpatterns.bettatech.factory.EnemyRandomFactory;
import com.hespinoza.designpatterns.bettatech.factory.BooFactory;
import com.hespinoza.designpatterns.bettatech.factory.GoombaFactory;
import com.hespinoza.designpatterns.bettatech.factory.KoopaFactory;
import com.hespinoza.designpatterns.bettatech.factory.RandomFactory;
import com.hespinoza.designpatterns.fintech.domain.model.Account;
import com.hespinoza.designpatterns.fintech.factory.AccountFactory;
import com.hespinoza.designpatterns.fintech.factory.CheckingAccountFactory;
import com.hespinoza.designpatterns.fintech.factory.InvestmentAccountFactory;
import com.hespinoza.designpatterns.fintech.factory.SavingsAccountFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
		// FACTORY PATTERN
		// Nos permite crear objetos sin especificar la clase exacta del objeto que se creará. Para lograr esto, los
		// objetos se crean llamando a un método de fábrica en lugar de llamar a un constructor.
		// Por lo general, la creación de objetos en Java se realiza así:
		// Falcon objeto = new Falcon();
		// El problema con el enfoque anterior es que el código que usa el objeto de Falcon, de repente ahora se vuelve
		// dependiente de la implementación concreta de Falcon. No hay nada de malo en usar new para crear objetos, pero
		// viene con el bagaje de acoplar estrechamente nuestro código a la clase de implementación concreta, lo que
		// ocasionalmente puede ser problemático.
		SpringApplication.run(DesignPatternsApplication.class, args);
		// Exercise 01
		AccountFactory accountFactory = getAccountFactory("investment");
		Account account = accountFactory.createAccount();
		account.deposit(BigDecimal.valueOf(200));
		account.withdraw(BigDecimal.valueOf(50));
		System.out.println("Interest " + account.calculateInterest());
		// Exercise 02
		EnemyFactory enemyFactory = getEnemyFactory("goomba");
		Enemy enemy = enemyFactory.createEnemy();
		System.out.println("Soy " + enemy.getName() + " / Fortaleza: " + enemy.power());
		System.out.println("Debilidad: " + enemy.weaknesses());
		System.out.println("Puntos de poder: " + enemy.strengths());
		// Exercise 03
		EnemyRandomFactory enemyRandomFactory = new RandomFactory();
		Enemy enemyRandom = enemyRandomFactory.createEnemyRamdonFactory();
		System.out.println("-----RANDOM ENEMY-----");
		System.out.println("Soy " + enemyRandom.getName() + " / Fortaleza: " + enemyRandom.power());
		// Exercise 04
		// Cómo damos prioridad a aparecer a los enemigos más poderosos sin modificar la lógica que ya tenemos
		// implementado y sin que afecte a todos los jugadores por igual, ya que puede existir jugadores que están
		// cómodos con la versión aleatoria de generar enemigos.
		// Tenemos que ENCAPSULAR LAS ESTRATEGIAS DE FABRICACIÓN de los enemigos. Aquí es donde entra a trabajar el PATRON
		// FACTORY. La FACTORY sería la responsable de mantener la lógica encargada de construir enemigos, sería una clase
		// que reutilizariamos y que podemos usar de manera POLYMORFICA. Todas las factories (GoombaFactory) siguen la
		// misma interfaz porque
		// todas ellas heredan de una abstracción que define el metodo creador de enemigos (EnemyFactory) a partir de acá
		// si queremos incorporar un método creador de enemigos basta con crear una nueva clase factory (EnemyRandomFactory)
		// que heredara la misma interfaz, implementando ese mecanismo en la clase createEnemyRamdonFactory().
		// Esto es muy importante y es lo que nos permite utilizar POLYMORFISMO ya que cada vez que necesitemos usar algo
		// para crear enemigos vamos a usar la clase padre de la factory, por lo que podemos modificar las implementaciones
		// concretas (Goomba, Koopa, Boo) cuando y como queramos, si queremos que ahora todos los enemigos sean Goomba,
		// pues solo pasamos esa
		// clase, si queremos todos los enemigos pero de manera aleatoria, pasamos EnemyRandomFactory entonces hemos
		// generalizado la forma de crear enemigos a una interfaz general, de hecho no tenemos límtes aquí, podemos pasar
		// parametros a una factory, % de cada enemigos, o pasar un minimo de cada enemigo para elegir enemigos a partir
		// de eso. Todo esto es tan general que no afecta a ninguna otra parte del codigo ya que todas las dependencias
		// son entre interfaces.
	}

	private static AccountFactory getAccountFactory(String type) {
		switch (type) {
			case "savings":
				// De esta forma lo que hacemos es que el código cliente interactúa con el creador (Creator) a través de
				// una interfaz o clase abstracta, sin conocer detalles específicos sobre qué tipo de producto concreto
				// (ConcreteProduct) será creado. Es decir, el cliente trabaja con la abstracción del producto, no con
				// implementaciones concretas.
				return new SavingsAccountFactory();
			case "checking":
				return new CheckingAccountFactory();
			case "investment":
				return new InvestmentAccountFactory();
			default:
				throw new IllegalArgumentException("Unknown account type");
		}
	}
	// De esta forma se puede crear y gestionar diferentes tipos de cuentas bancarias sin especificar clases concretas
	// en el código cliente que maneja las cuentas. El cliente decide qué tipo de cuenta necesita (ahorro, corriente
	// o inversión) mediante una cadena de entrada y usa getAccountFactory para obtener la fábrica correspondiente.
	// Cada tipo de cuenta tiene su propia lógica para depósitos, retiros y cálculo de intereses, gestionada de
	// manera transparente por la interfaz Account.
	// Este patrón permite a la fintech añadir nuevos tipos de cuentas sin alterar el código que las utiliza, haciendo
	// el sistema más modular y fácil de expandir. Además, facilita la prueba de distintas configuraciones de cuenta en
	// diferentes entornos o escenarios de uso sin cambios significativos en el código base.
	// BENEFICIOS DE ESTE ENFOQUE
	// Desacoplamiento: El cliente no depende de las clases concretas de los productos. Esto significa que puedes
	// cambiar, agregar o modificar las clases de productos sin que el cliente necesite cambiar.
	// Flexibilidad: Puedes introducir nuevos tipos de productos (nuevos ConcreteProduct) y nuevos creadores
	// (ConcreteCreator) sin alterar el código que los utiliza.
	// OJO: Aunque la elección de un ConcreteCreator específico implica conocer una clase concreta, este
	// conocimiento se utiliza solamente para la configuración inicial y no afecta la flexibilidad y mantenibilidad del
	// uso de los productos en sí, que siguen siendo manejados a través de una interfaz abierta y genérica. Esto permite
	// que el sistema sea fácil de expandir y modificar.
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
	// Esto puede servir para ir agregando diferentes tipos de enemigos al juego ya se manera directa o aleatoria
	// de acuerdo a sus características a medida que van avanzando en los niveles, por lo tanto, en nuestro videojuego
	// tendremos un conjunto de clase donde cada uno será un tipo de enemigo (Goomba, Koopa y Boo) las cuales en nuestra
	// aplicación son entidades lógicas del juego, entidades que hacen algo: se mueven, interaccionan con el jugador,
	// con el mapa, las cuales van a heredar de la clase "Enemy" lo que genera que apliquemos polymorfismo por lo tanto
	// cada vez que tengamos que actualizar el estado del juego podemos tratar a todas las entidades por igual.
	// Si queremos generar enemigos de manera aleatoria en algún punto de nuestro código y/o función tendremos la lógica
	// para crear nuestros enemigos.
	// Y cómo construimos a nuestros enemigos? usamos new Koopa(), new Goomba(), NO! PORQUE NO PODEMOS INSTANCIAR UNA
	// ENTIDAD PORQUE NO ES UNA CLASE CONCRETA SINO UNA CLASE ABSTRACTA.
}
