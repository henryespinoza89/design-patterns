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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DesignPatternsApplication {

	private static final Logger logger = LoggerFactory.getLogger(DesignPatternsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);
		// Exercise 01
		AccountFactory accountFactory = getAccountFactory("investment");
		Account account = accountFactory.createAccount();
		account.deposit(BigDecimal.valueOf(200));
		account.withdraw(BigDecimal.valueOf(50));
		logger.info("Interest {}", account.calculateInterest());
		// Exercise 02
		EnemyFactory enemyFactory = getEnemyFactory("goomba");
		Enemy enemy = enemyFactory.createEnemy();
		logger.info("I'm {} / Strengths: {}", enemy.getName(), enemy.power());
		logger.info("Weaknesses: {}", enemy.weaknesses());
		logger.info("Power points: {}", enemy.strengths());
		// Exercise 02 - creating random enemies
		EnemyRandomFactory enemyRandomFactory = new RandomFactory();
		Enemy enemyRandom = enemyRandomFactory.createEnemyRamdonFactory();
		logger.info("-----RANDOM ENEMY-----");
		logger.info("I'm {} / Strengths: {}", enemyRandom.getName(), enemyRandom.power());
	}

	private static AccountFactory getAccountFactory(String type) {
		switch (type) {
			case "savings":
				return new SavingsAccountFactory();
			case "checking":
				return new CheckingAccountFactory();
			case "investment":
				return new InvestmentAccountFactory();
			default:
				throw new IllegalArgumentException("Unknown account type");
		}
	}

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
}
