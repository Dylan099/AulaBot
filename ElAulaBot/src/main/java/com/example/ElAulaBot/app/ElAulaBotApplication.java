package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.*;
import com.example.ElAulaBot.domain.Anuncio;
import com.example.ElAulaBot.domain.Examen;
import com.example.ElAulaBot.domain.Pregunta;
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
	CursoEstudianteBl cursoEstudianteBl;
	ExamenBl examenBl;
	PreguntaBl preguntaBl;
	RespuestaBl respuestaBl;
	AnuncioBl anuncioBl;
	ArchivoBl archivoBl;

    @Autowired
    public ElAulaBotApplication(ProfesorBl profesorBl, EstudianteBl estudianteBl,CursoBl cursoBl, UsuarioBl usuarioBl, CursoEstudianteBl cursoEstudianteBl,
                                ExamenBl examenBl, PreguntaBl preguntaBl, RespuestaBl respuestaBl,ArchivoBl archivoBl, AnuncioBl anuncioBl) {
        this.profesorBl = profesorBl;
        this.estudianteBl = estudianteBl;
        this.cursoBl = cursoBl;
        this.usuarioBl = usuarioBl;
        this.cursoEstudianteBl = cursoEstudianteBl;
        this.examenBl = examenBl;
        this.preguntaBl = preguntaBl;
        this.respuestaBl = respuestaBl;
        this.archivoBl = archivoBl;
        this.anuncioBl = anuncioBl;
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
            botsApi.registerBot(new ElAulaBot(profesorBl,estudianteBl,cursoBl,usuarioBl,cursoEstudianteBl,examenBl,preguntaBl, respuestaBl,archivoBl, anuncioBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
