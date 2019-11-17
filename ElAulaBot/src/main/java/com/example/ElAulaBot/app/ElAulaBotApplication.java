package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.CursoBl;
import com.example.ElAulaBot.bl.EstudianteBl;
import com.example.ElAulaBot.bl.ProfesorBl;
import com.example.ElAulaBot.bl.UsuarioBl;
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
	EstudianteBl estudianteBl;
	CursoBl cursoBl;
	UsuarioBl usuarioBl;

	@Autowired
	public ElAulaBotApplication(ProfesorBl profesorBl, EstudianteBl estudianteBl,UsuarioBl usuarioBl) {
		this.profesorBl = profesorBl;
		this.estudianteBl = estudianteBl;
		this.cursoBl = cursoBl;
		this.usuarioBl = usuarioBl;
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
			botsApi.registerBot(new ElAulaBot(profesorBl,estudianteBl,cursoBl,usuarioBl));
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
