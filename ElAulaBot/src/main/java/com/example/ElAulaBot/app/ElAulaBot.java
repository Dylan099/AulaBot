package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.CursoBl;
import com.example.ElAulaBot.bl.EstudianteBl;
import com.example.ElAulaBot.bl.ProfesorBl;
import com.example.ElAulaBot.bl.UsuarioBl;
import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.ProfesorDto;
import org.springframework.beans.factory.annotation.Autowired;
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


    public ElAulaBot(ProfesorBl profesorBl, EstudianteBl estudianteBl, CursoBl cursoBl,UsuarioBl usuarioBl) {
        this.profesorBl = profesorBl;
        this.estudianteBl = estudianteBl;
        this.cursoBl = cursoBl;
        this.usuarioBl = usuarioBl;
    }

    private long chatId;
    private User user;
    List<String> messages;

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            messages = usuarioBl.processUpdate(update.getMessage().getFrom(),update);
            switch (update.getMessage().getText()){
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
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } else {
                        SendMessage messageDefault = new SendMessage()
                                .setChatId(chatId)
                                .setText("Usted no esta inscrito, por favor inicie el comando /start.");
                        try {
                            execute(messageDefault);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
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
