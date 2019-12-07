package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.*;
import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.CursoHasEstudiante;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.CursoDto;
import com.example.ElAulaBot.dto.ProfesorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class ElAulaBot extends TelegramLongPollingBot {

    ProfesorBl profesorBl;
    CursoBl cursoBl;
    EstudianteBl estudianteBl;
    UsuarioBl usuarioBl;
    CursoEstudianteBl cursoEstudianteBl;
    ExamenBl examenBl;
    PreguntaBl preguntaBl;
    RespuestaBl respuestaBl;
    AnuncioBl anuncioBl;

    @Autowired
    public ElAulaBot(ProfesorBl profesorBl, EstudianteBl estudianteBl, CursoBl cursoBl, UsuarioBl usuarioBl, CursoEstudianteBl cursoEstudianteBl,
                     ExamenBl examenBl, PreguntaBl preguntaBl, RespuestaBl respuestaBl, AnuncioBl anuncioBl) {
        this.profesorBl = profesorBl;
        this.estudianteBl = estudianteBl;
        this.cursoBl = cursoBl;
        this.usuarioBl = usuarioBl;
        this.cursoEstudianteBl = cursoEstudianteBl;
        this.examenBl=examenBl;
        this.preguntaBl = preguntaBl;
        this.respuestaBl = respuestaBl;
        this.anuncioBl = anuncioBl;
    }

    private long chatId;
    private User user;
    List<String> messages;
    String lastMessageReceived="";
    String lastMessageSent="";

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = usuarioBl.processUpdate(update);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if(update.hasCallbackQuery()){
            System.out.println(update.getCallbackQuery().getMessage().getText());
            EditMessageText messageText = usuarioBl.processCallBack(update);
            try {
                execute(messageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "ElAulaBot";
    }

    public String getBotToken() {
        return "981092223:AAH16-cmUCALjzNDGm5b909TfEykX5pIoX8";
    }

}
