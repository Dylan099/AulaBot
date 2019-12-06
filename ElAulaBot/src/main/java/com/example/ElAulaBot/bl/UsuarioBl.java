package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.UsuarioRepository;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.domain.Usuario;
import com.example.ElAulaBot.dto.Status;
import com.example.ElAulaBot.dto.UsuarioDto;
import com.google.inject.internal.cglib.core.$DuplicatesPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UsuarioBl {
    UsuarioRepository usuarioRepository;
    ProfesorBl profesorBl;
    EstudianteBl estudianteBl;
    CursoBl cursoBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioBl.class);

    @Autowired
    public UsuarioBl(UsuarioRepository usuarioRepository, ProfesorBl profesorBl, EstudianteBl estudianteBl,CursoBl cursoBl) { this.usuarioRepository = usuarioRepository;
    this.profesorBl = profesorBl; this.estudianteBl = estudianteBl; this.cursoBl = cursoBl;}

    public Usuario findUsuarioByChatId(String chatId){
        Usuario usuario = this.usuarioRepository.findUsuarioByChatId(chatId);
        if(usuario != null)
        {
            return usuario;
        }
        else {
            LOGGER.info("El usuario no esta registrado");
            return usuario;
        }
    }

    public List<UsuarioDto> findAllUsuario(){
        List<UsuarioDto> usuarioList = new ArrayList<>();
        for(Usuario usuario: usuarioRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            usuarioList.add(new UsuarioDto(usuario));

        }
        return usuarioList;
    }

    public SendMessage processUpdate(Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        Usuario usuario = initUsuario(update);
        setlastMessageSent(update,update.getMessage().getText());
        SendMessage algo = continueChatWithUser(update, usuario);
        setlastMessageReceived(update,algo.getText());
        return algo;
    }

    public EditMessageText processCallBack(Update update){
        LOGGER.info("Recibiendo update {} ", update);
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
        setlastCallbackSent(update,update.getCallbackQuery().getData());
        EditMessageText algo = continueCallBack(update, usuario);
        setlastCallbackReceived(update,algo.getText());
        return algo;
    }

    private EditMessageText continueCallBack (Update update, Usuario usuario){
        Usuario lastMessage = usuarioRepository.findLastChatByUserId(usuario.getIdUser());
        System.out.println("AQUIIIIII______________>"+lastMessage.getLastMessageSent()+"  "+lastMessage.getLastMessageReceived());
        LOGGER.info("Primer mensaje del usuario botUserId{}", update.getCallbackQuery().getFrom().getId());
        // Preparo la vaiable para retornar la respuesta
        // Si el ultimo mensaje no existe (es la primera conversación)
        EditMessageText chatResponse=null;

        if (lastMessage == null) {
            // Retornamos 1
            LOGGER.info("Primer mensaje del usuario botUserId{}", usuario.getIdUser());
            chatResponse.setChatId(lastMessage.getChatId()).setText("1");
        } else {
            String lastSent = update.getCallbackQuery().getData();
            switch (lastSent){
                case "profesor":
                    List<String> messages = profesorBl.processUpdate(update.getCallbackQuery().getFrom());
                    String answer = "Gracias por registrarte "+" como profesor. ";
                    answer+= " ID -> "+lastMessage.getChatId();
                    chatResponse = new EditMessageText()
                            .setChatId(lastMessage.getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setText(answer);
                    break;
                case "crear":

                    answer = "Ingrese el nombre del curso a crear: ";
                    chatResponse = new EditMessageText()
                            .setChatId(lastMessage.getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setText(answer);
                    break;


            }
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getCallbackQuery().getData(), usuario.getIdUser());
        return chatResponse;
    }

    private SendMessage continueChatWithUser(Update update,Usuario lastMessage) {
        // Obtener el ultimo mensaje que envió el usuario
        LOGGER.info("Primer mensaje del usuario botUserId{}", update.getMessage().getFrom().getId());
        // Preparo la vaiable para retornar la respuesta
        // Si el ultimo mensaje no existe (es la primera conversación)
        SendMessage chatResponse=null;

        if (lastMessage == null) {
            // Retornamos 1
            LOGGER.info("Primer mensaje del usuario botUserId{}", lastMessage.getIdUser());
            chatResponse.setChatId(lastMessage.getChatId()).setText("1");
        } else {
            String lastSent = lastMessage.getLastMessageSent();
            switch (lastSent){
                case "/start":
                    chatResponse = new SendMessage()
                            .setChatId(lastMessage.getChatId())
                            .setText("Bienvenido como desea registrarse : " + " ID -> "+update.getMessage().getFrom().getId());
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    rowInline.add(new InlineKeyboardButton().setText("Profesor").setCallbackData("profesor"));
                    rowInline.add(new InlineKeyboardButton().setText("Estudiante").setCallbackData("estudiante"));

                    rowsInline.add(rowInline);
                    markupInline.setKeyboard(rowsInline);
                    chatResponse.setReplyMarkup(markupInline);
                    break;
                case "/curso":

                    // Verificacion de que el usuario es un profesor y no un estudiante
                    if(profesorBl.findProfesorByChatId(update.getMessage().getFrom().getId())!=null){

                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText("Que desea hacer:");

                        InlineKeyboardMarkup markupInlineCurso = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInlineCurso = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineCurso = new ArrayList<>();
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Crear Curso").setCallbackData("crear"));
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Ver Cursos").setCallbackData("verCursosProf"));

                        rowsInlineCurso.add(rowInlineCurso);

                        markupInlineCurso.setKeyboard(rowsInlineCurso);
                        chatResponse.setReplyMarkup(markupInlineCurso);
                    } else  if(estudianteBl.findEstudianteByChatId(update.getMessage().getFrom().getId())!=null){

                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText("Que desea hacer:");

                        InlineKeyboardMarkup markupInlineCurso = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInlineCurso = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineCurso = new ArrayList<>();
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Inscribirse a un Curso").setCallbackData("inscripcion"));
                        rowInlineCurso.add(new InlineKeyboardButton().setText("Ver Cursos").setCallbackData("verCursosEst"));

                        rowsInlineCurso.add(rowInlineCurso);

                        markupInlineCurso.setKeyboard(rowsInlineCurso);
                        chatResponse.setReplyMarkup(markupInlineCurso);
                    } else {
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText("Usted no esta inscrito, por favor inicie el comando /start.");
                    }
                    break;

            }
            switch (lastMessage.getLastMessageReceived()){
                case "Ingrese el nombre del curso a crear: ":
                    String nombreCurso = lastSent;
                    System.out.println(nombreCurso);
                    String answerCrearCurso = "";
                    try{
                        String codigo = cursoBl.crearCurso(update.getMessage().getFrom(),nombreCurso);
                        answerCrearCurso = "Curso Creado. Codigo del curso -> "+ codigo;
                    }catch (DataIntegrityViolationException e){
                        answerCrearCurso = "Curso ya existente";
                    }
                    chatResponse = new SendMessage()
                            .setChatId(lastMessage.getChatId())
                            .setText(answerCrearCurso);
                    break;
            }
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), lastMessage.getIdUser());
        return chatResponse;
    }

    private Usuario initUsuario(Update update) {
            Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(update.getMessage().getFrom().getId()));
            if (usuario == null) {
                Usuario nuevoUsuario =new Usuario();
                nuevoUsuario.setChatId(String.valueOf(update.getMessage().getFrom().getId()));
                nuevoUsuario.setLastMessageSent(update.getMessage().getText());
                nuevoUsuario.setLastMessageReceived("");
                nuevoUsuario.setTxhost("localhost");
                nuevoUsuario.setTxuser("admin");
                nuevoUsuario.setTxdate(new Date());
                usuarioRepository.save(nuevoUsuario);
                return nuevoUsuario;
            }

        return usuario;
    }
    public String getlastMessageReceived(Update update, User user){
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        return usuario.getLastMessageReceived();
    }

    public String getlastMessageSent(Update update, User user){
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        return usuario.getLastMessageSent();
    }
    public void setlastMessageReceived(Update update, String messageReceived){
        User user = update.getMessage().getFrom();
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageReceived(messageReceived);
    }

    public void setlastMessageSent(Update update, String messageSent){
        User user = update.getMessage().getFrom();
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageSent(messageSent);
    }

    public void setlastCallbackReceived(Update update, String messageReceived){
        User user = update.getCallbackQuery().getFrom();
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageReceived(messageReceived);
    }

    public void setlastCallbackSent(Update update, String messageSent){
        User user = update.getCallbackQuery().getFrom();
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageSent(messageSent);
    }








}
