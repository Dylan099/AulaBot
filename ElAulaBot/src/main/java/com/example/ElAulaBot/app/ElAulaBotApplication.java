package com.example.ElAulaBot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class ElAulaBotApplication {

	public static void main(String[] args) {

		SpringApplication.run(ElAulaBotApplication.class, args);

		// TODO Initialize Api Context
		ApiContextInitializer.init();

		// TODO Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();

		// TODO Register our bot
		try {
			botsApi.registerBot(new ElAulaBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
