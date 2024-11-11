package com.srd.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.srd.demo.entity.TradeMongo;
import com.srd.demo.repo.jpa.TradeSqlRepo;
import com.srd.demo.repo.mongo.TradeMongoRepo;
import com.srd.demo.service.TradeMongoService;
import com.srd.demo.service.TradeSqlService;
import com.srd.demo.entity.Trade;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TradeMongoRepo tMongoRepo, TradeSqlRepo tradeSqlRepo) {

		return runner -> {
			createMongoTrade(tMongoRepo);
			createSqlTrade(tradeSqlRepo);
		};
	}

	private void createMongoTrade(TradeMongoRepo tMongoRepo) {

		// create the trade object
		System.out.println("Creating new Mongo trade ...");
		TradeMongoService tMongoService = new TradeMongoService(tMongoRepo);
		//TradeMongo tradeMongo = new TradeMongo("Shishir",132);
		//tMongoService.saveTrade(tradeMongo);
	}

	private void createSqlTrade(TradeSqlRepo tradeSqlRepo)
	{
		System.out.println("Creating new SQL trade ...");
		TradeSqlService tradeSqlService = new TradeSqlService(tradeSqlRepo);
		Trade trade = new Trade(4,"Shishir",4,"Vyas");
		tradeSqlService.saveTradeSql(trade);
	}
}
