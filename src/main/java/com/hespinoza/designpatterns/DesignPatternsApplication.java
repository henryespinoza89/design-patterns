package com.hespinoza.designpatterns;

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
		SpringApplication.run(DesignPatternsApplication.class, args);
		AccountFactory accountFactory = getAccountFactory("investment");
		Account account = accountFactory.createAccount();
		account.deposit(BigDecimal.valueOf(200));
		account.withdraw(BigDecimal.valueOf(50));
		System.out.println("Interest " + account.calculateInterest());
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
}
