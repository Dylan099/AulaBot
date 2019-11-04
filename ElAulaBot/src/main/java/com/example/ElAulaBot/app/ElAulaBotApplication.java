package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.ProfesorBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class ElAulaBotApplication {

	ProfesorBl profesorBl;

	@Autowired
	public ElAulaBotApplication(ProfesorBl profesorBl) {
		this.profesorBl = profesorBl;
	}

	public ElAulaBotApplication() {
	}

	@PostConstruct
	public void init() {

		// TODO Initialize Api Context
		ApiContextInitializer.init();

		// TODO Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();

		// TODO Register our bot
		try {
			botsApi.registerBot(new ElAulaBot(profesorBl));
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
