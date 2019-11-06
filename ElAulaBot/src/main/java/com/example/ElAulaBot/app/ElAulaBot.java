package com.example.ElAulaBot.app;

import com.example.ElAulaBot.bl.ProfesorBl;
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

    public ElAulaBot(ProfesorBl profesorBl) {
        this.profesorBl = profesorBl;
    }

    private long chatId;
    private User user;
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            chatId=update.getMessage().getFrom().getId();
            user = update.getMessage().getFrom();
            if (update.getMessage().getText().equals("/start")) {
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
            }

        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            if (call_data.equals("profesor")) {
                List<String> messages = profesorBl.processUpdate(user);
                String answer = "Gracias por registrarte "+" como profesor. ";
                answer+= " ID -> "+chatId;
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (call_data.equals("estudiante")) {
                String answer = "Gracias por registrarte "+" como estudiante.";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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
