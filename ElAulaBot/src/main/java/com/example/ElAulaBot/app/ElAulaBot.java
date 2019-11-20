package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.CursoBl;
import com.example.ElAulaBot.bl.EstudianteBl;
import com.example.ElAulaBot.bl.ProfesorBl;
import com.example.ElAulaBot.bl.UsuarioBl;
import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Curso;
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

    @Autowired
    public ElAulaBot(ProfesorBl profesorBl, EstudianteBl estudianteBl, CursoBl cursoBl,UsuarioBl usuarioBl) {
        this.profesorBl = profesorBl;
        this.estudianteBl = estudianteBl;
        this.cursoBl = cursoBl;
        this.usuarioBl = usuarioBl;
    }

    private long chatId;
    private User user;
    List<String> messages;
    String lastMessageReceived="";
    String lastMessageSent="";

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getFrom().getId();
            user = update.getMessage().getFrom();
            messages = usuarioBl.processUpdate(update.getMessage().getFrom(),update);
            lastMessageSent = usuarioBl.getlastMessageSent(update,user);
            lastMessageReceived = usuarioBl.getlastMessageReceived(update,user);
            String userMessage = update.getMessage().getText();
            switch (userMessage){
                case "/start":

                    chatId = update.getMessage().getFrom().getId();
                    user = update.getMessage().getFrom();
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Bienvenido como desea registrarse : " + " ID -> "+update.getMessage().getFrom().getId());
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Profesor").setCallbackData("profesor"));
                rowInline.add(new InlineKeyboardButton().setText("Estudiante").setCallbackData("estudiante"));

                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                    usuarioBl.setlastMessageSent(update,user,"/start");
                    usuarioBl.setlastMessageReceived(update,user,message.getText());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                break;

                case "/curso":
                    chatId = update.getMessage().getFrom().getId();
                    user = update.getMessage().getFrom();
                    // Verificacion de que el usuario es un profesor y no un estudiante
                    if(profesorBl.findProfesorByChatId(user.getId())!=null){

                        SendMessage messageCurso = new SendMessage()
                                .setChatId(chatId)
                                .setText("Que desea hacer:");

                        InlineKeyboardMarkup markupInlineCurso = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInlineCurso = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineCurso = new ArrayList<>();
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Crear Curso").setCallbackData("crear"));
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Ver Cursos").setCallbackData("ver"));

                        rowsInlineCurso.add(rowInlineCurso);

                        markupInlineCurso.setKeyboard(rowsInlineCurso);
                        messageCurso.setReplyMarkup(markupInlineCurso);
                        try {
                            execute(messageCurso);
                            usuarioBl.setlastMessageReceived(update,user,messageCurso.getText());
                            usuarioBl.setlastMessageSent(update,user,"/curso");
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else  if(estudianteBl.findEstudianteByChatId(user.getId())!=null){

                        SendMessage messageCursoEst = new SendMessage()
                                .setChatId(chatId)
                                .setText("Que desea hacer:");

                        InlineKeyboardMarkup markupInlineCurso = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInlineCurso = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineCurso = new ArrayList<>();
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Inscribirse a un Curso").setCallbackData("inscripcion"));
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Ver Cursos").setCallbackData("verCursosEst"));

                        rowsInlineCurso.add(rowInlineCurso);

                        markupInlineCurso.setKeyboard(rowsInlineCurso);
                        messageCursoEst.setReplyMarkup(markupInlineCurso);
                        try {
                            execute(messageCursoEst);
                            usuarioBl.setlastMessageReceived(update,user,messageCursoEst.getText());
                            usuarioBl.setlastMessageSent(update,user,"/curso");
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else {
                        SendMessage messageDefault = new SendMessage()
                                .setChatId(chatId)
                                .setText("Usted no esta inscrito, por favor inicie el comando /start.");
                        try {
                            execute(messageDefault);
                            usuarioBl.setlastMessageReceived(update,user,messageDefault.getText());
                            usuarioBl.setlastMessageSent(update,user,"/curso");
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;


            }
            if(lastMessageReceived!=null && lastMessageSent!=null){
                switch (lastMessageReceived){

                    case "Ingrese el nombre del curso a crear: ":

                        chatId = update.getMessage().getFrom().getId();
                        user = update.getMessage().getFrom();
                        System.out.println(chatId);
                        System.out.println(user);
                        String nombreCurso = update.getMessage().getText();
                        System.out.println(nombreCurso);
                        String answerCrearCurso = "";
                        try{
                            String codigo = cursoBl.crearCurso(user,nombreCurso);
                            answerCrearCurso = "Curso Creado. Codigo del curso -> "+ codigo;
                        }catch (DataIntegrityViolationException e){
                            answerCrearCurso = "Curso ya existente";
                        }
                        SendMessage new_messageInsCurso = new SendMessage()
                                .setChatId(chatId)
                                .setText(answerCrearCurso);
                        try {
                            execute(new_messageInsCurso);
                            usuarioBl.setlastMessageReceived(update,user,new_messageInsCurso.getText());
                            usuarioBl.setlastMessageSent(update,user,nombreCurso);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ALGO MAS IRA AQUI ":
                        break;
                }
            }

        } else if (update.hasCallbackQuery()) {

            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            String answer = "";
            switch (call_data){
                case "profesor":
                    List<String> messages = profesorBl.processUpdate(user);
                    answer = "Gracias por registrarte "+" como profesor. ";
                    answer+= " ID -> "+chatId;
                    EditMessageText new_messagePro = new EditMessageText()
                            .setChatId(chat_id)
                            .setMessageId(toIntExact(message_id))
                            .setText(answer);
                    try {
                        execute(new_messagePro);
                        usuarioBl.setlastMessageReceived(update,user,new_messagePro.getText());
                        usuarioBl.setlastMessageSent(update,user,"profesor");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "estudiante":
                    List<String> messagesEstudiante = estudianteBl.processUpdate(user);
                    answer = "Gracias por registrarte "+user.getFirstName()+" "+user.getLastName()+" como estudiante.";
                    answer += " Su ID: "+chatId;
                    EditMessageText new_messageEst = new EditMessageText()
                            .setChatId(chat_id)
                            .setMessageId(toIntExact(message_id))
                            .setText(answer);
                    try {
                        execute(new_messageEst);
                        usuarioBl.setlastMessageReceived(update,user,new_messageEst.getText());
                        usuarioBl.setlastMessageSent(update,user,"estudiante");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "crear":

                    answer = "Ingrese el nombre del curso a crear: ";
                    EditMessageText new_messageCrearCurso = new EditMessageText()
                            .setChatId(chat_id)
                            .setMessageId(toIntExact(message_id))
                            .setText(answer);
                    try {
                        execute(new_messageCrearCurso);
                        usuarioBl.setlastMessageReceived(update,user,new_messageCrearCurso.getText());
                        usuarioBl.setlastMessageSent(update,user,"crear curso");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                break;
                case "inscripcion":
                    answer = "Ingrese el codigo del curso al que desea inscribirse: ";
                    EditMessageText new_messageInsCurso = new EditMessageText()
                            .setChatId(chat_id)
                            .setMessageId(toIntExact(message_id))
                            .setText(answer);
                    try {
                        execute(new_messageInsCurso);
                        usuarioBl.setlastMessageReceived(update,user,new_messageInsCurso.getText());
                        usuarioBl.setlastMessageSent(update,user,"inscribir curso");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    break;
                case "verCursosEst":


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
