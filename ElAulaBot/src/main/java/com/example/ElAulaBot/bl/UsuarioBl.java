package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.UsuarioRepository;
import com.example.ElAulaBot.domain.*;
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
import org.telegram.telegrambots.meta.api.objects.inlinequery.ChosenInlineQuery;
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
    CursoEstudianteBl cursoEstudianteBl;
    CursoBl cursoBl;
    ExamenBl examenBl;
    PreguntaBl preguntaBl;
    RespuestaBl respuestaBl;
    AnuncioBl anuncioBl;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioBl.class);

    @Autowired
    public UsuarioBl(UsuarioRepository usuarioRepository, ProfesorBl profesorBl, EstudianteBl estudianteBl,CursoBl cursoBl, CursoEstudianteBl cursoEstudianteBl, ExamenBl examenBl,
                     PreguntaBl preguntaBl, RespuestaBl respuestaBl, AnuncioBl anuncioBl) { this.usuarioRepository = usuarioRepository;
    this.profesorBl = profesorBl; this.estudianteBl = estudianteBl; this.cursoBl = cursoBl; this.cursoEstudianteBl = cursoEstudianteBl;
    this.examenBl=examenBl;this.preguntaBl=preguntaBl;this.respuestaBl=respuestaBl;this.anuncioBl=anuncioBl;}

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
                case "/stop":
                    chatResponse = new SendMessage()
                            .setChatId(lastMessage.getChatId())
                            .setText("Gracias por usar el bot");
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
                case "Ingrese el codigo de curso al que desea inscribirse: ":
                    String codigoCurso = lastSent;
                    System.out.println(codigoCurso);
                    String answerIncribirCurso = "";
                    System.out.println(update.getMessage().getFrom());

                    if (cursoEstudianteBl.verificarEstudiante(update.getMessage().getFrom(),codigoCurso)){
                        answerIncribirCurso = "Ya perteneces a este grupo, porfavor ingresa un codigo valido.";
                    }else{
                        try{
                            String nomCur = cursoEstudianteBl.incribirCurso(update.getMessage().getFrom(), codigoCurso);
                            if (nomCur == ""){
                                answerIncribirCurso = "Curso no existente";
                            }else{
                                answerIncribirCurso = "Te uniste al curso -> "+nomCur;
                            }

                        }catch (DataIntegrityViolationException e){
                            answerIncribirCurso = "Curso no existente";
                        }
                    }
                    chatResponse = new SendMessage()
                            .setChatId(lastMessage.getChatId())
                            .setText(answerIncribirCurso);
                    break;
                case "Envie archivos solamente en formato PDF, Word, Excel":
                    //NOSE PORQUE NO MUESTRA el tamanoooo
                    LOGGER.info("CASCOOOOOOOOOOOOOOOOOOOOO");
                    chatResponse = new SendMessage()
                            .setChatId(lastMessage.getChatId())
                            .setText("El tamano del archivo es:"+update.getMessage().getDocument().getFileSize());
                    break;

            }
            if(lastMessage.getLastMessageReceived().contains(">")){
                String [] opcion = lastMessage.getLastMessageReceived().split(">");
                switch (opcion[1]){
                    case "Ingrese nombre del examen:":
                        Curso selectExamen=cursoBl.findCursoByCursoId(Integer.parseInt(opcion[0]));
                        Examen nuevoExamen = examenBl.crearExamen(selectExamen,update.getMessage().getText());
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText(nuevoExamen.getIdExamen()+">Ingrese enunciado de la pregunta:(Ingrese Fin si desea terminar y guardar el examen)");
                        break;
                    case "Ingrese enunciado de la pregunta:(Ingrese Fin si desea terminar y guardar el examen)":
                        Examen targetExamen = examenBl.findExamenByExamenId(Integer.parseInt(opcion[0]));
                        if(update.getMessage().getText().compareToIgnoreCase("Fin")==0){
                            chatResponse = new SendMessage()
                                    .setChatId(lastMessage.getChatId())
                                    .setText("Examen Registrado");
                        }else{
                            Pregunta targetPregunta = preguntaBl.crearPregunta(targetExamen,update.getMessage().getText());
                            chatResponse = new SendMessage()
                                    .setChatId(lastMessage.getChatId())
                                    .setText(targetPregunta.getIdPregunta()+">Ingrese respuesta 1:");
                        }
                        break;
                    case "Ingrese respuesta 1:":
                        Pregunta selectPreg1 = preguntaBl.findPreguntaByIdPregunta(Integer.parseInt(opcion[0]));
                        Respuesta respuesta=respuestaBl.crearRespuesta(selectPreg1,update.getMessage().getText());
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText(selectPreg1.getIdPregunta()+">Ingrese respuesta 2:");
                        break;
                    case "Ingrese respuesta 2:":
                        Pregunta selectPreg2 = preguntaBl.findPreguntaByIdPregunta(Integer.parseInt(opcion[0]));
                        Respuesta respuesta2=respuestaBl.crearRespuesta(selectPreg2,update.getMessage().getText());
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText(selectPreg2.getIdPregunta()+">Ingrese respuesta 3:");
                        break;
                    case "Ingrese respuesta 3:":
                        Pregunta selectPreg3 = preguntaBl.findPreguntaByIdPregunta(Integer.parseInt(opcion[0]));
                        Respuesta respuesta3=respuestaBl.crearRespuesta(selectPreg3,update.getMessage().getText());
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText(selectPreg3.getIdPregunta()+">Ingrese respuesta 4:");
                        break;
                    case "Ingrese respuesta 4:":
                        Pregunta selectPreg4 = preguntaBl.findPreguntaByIdPregunta(Integer.parseInt(opcion[0]));
                        Respuesta respuesta4=respuestaBl.crearRespuesta(selectPreg4,update.getMessage().getText());
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText(selectPreg4.getIdPregunta()+">¿Cual es la pregunta correcta?");
                        break;
                    case "¿Cual es la pregunta correcta?":
                        Pregunta selectPregFinal = preguntaBl.findPreguntaByIdPregunta(Integer.parseInt(opcion[0]));
                        if(update.getMessage().getText().matches("[1-4]+")){
                            respuestaBl.setRespuestaCorrecta(update,respuestaBl.findAllByPreguntaId(selectPregFinal));
                            chatResponse = new SendMessage()
                                    .setChatId(lastMessage.getChatId())
                                    .setText("Examen Creado exitosamente");
                        }else{
                            chatResponse = new SendMessage()
                                    .setChatId(lastMessage.getChatId())
                                    .setText(selectPregFinal.getIdPregunta()+">¿Cual es la pregunta correcta?");
                        }
                        break;
                    case "Ingrese el contenido del anuncio: ":
                        Curso curso = cursoBl.findCursoByCursoId(Integer.parseInt(opcion[0]));
                        Anuncio anuncio = anuncioBl.crearAnuncio(curso, update.getMessage().getText());
                        chatResponse = new SendMessage()
                                .setChatId(lastMessage.getChatId())
                                .setText("Anuncio Creado");
                        break;
                }

            }
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), lastMessage.getIdUser());
        return chatResponse;
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
            if(lastSent.contains(";")){
                String []cursos=lastSent.split(";");
                System.out.println("ALGOOOOOOOOOOOO____>___>"+cursos[0]+" "+cursos[1]);
                switch (cursos[0]){
                    case "cursosProf":
                        Curso select=cursoBl.findCursoByCursoId(Integer.parseInt(cursos[1]));
                        chatResponse = new EditMessageText()
                                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                .setText("Bienvenido al curso: "+select.getNombreCurso());

                        InlineKeyboardMarkup markupInlineOpcionesCursoProf = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInlineOpcionesCursoProf = new ArrayList<>();

                        List<InlineKeyboardButton> rowInlineOpcionesCursoProf = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineOpcionesCursoProf2 = new ArrayList<>();

                        rowInlineOpcionesCursoProf.add(new InlineKeyboardButton().setText("Subir archivo").setCallbackData("subir archivo;"+select.getIdCurso()));
                        rowInlineOpcionesCursoProf.add(new InlineKeyboardButton().setText("Crear anuncio").setCallbackData("crear anuncio;"+select.getIdCurso()));
                        rowInlineOpcionesCursoProf2.add(new InlineKeyboardButton().setText("Crear examen").setCallbackData("crear examen;"+select.getIdCurso()));
                        rowInlineOpcionesCursoProf2.add(new InlineKeyboardButton().setText("Listado de estudiantes").setCallbackData("listado;"+select.getIdCurso()));

                        rowsInlineOpcionesCursoProf.add(rowInlineOpcionesCursoProf);
                        rowsInlineOpcionesCursoProf.add(rowInlineOpcionesCursoProf2);

                        markupInlineOpcionesCursoProf.setKeyboard(rowsInlineOpcionesCursoProf);
                        chatResponse.setReplyMarkup(markupInlineOpcionesCursoProf);
                        break;
                    case "cursosEst":
                        Curso selectEst=cursoBl.findCursoByCursoId(Integer.parseInt(cursos[1]));
                        chatResponse = new EditMessageText()
                                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                .setText("Bienvenido al curso:"+selectEst.getNombreCurso());

                        InlineKeyboardMarkup markupInlineOpcionesCursoEstu = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInlineOpcionesCursoEstu = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineOpcionesCursoEstu = new ArrayList<>();
                        List<InlineKeyboardButton> rowInlineOpcionesCursoEstu2 = new ArrayList<>();

                        rowInlineOpcionesCursoEstu.add(new InlineKeyboardButton().setText("Ver archivos").setCallbackData("ver archivos;"+selectEst.getIdCurso()));
                        rowInlineOpcionesCursoEstu.add(new InlineKeyboardButton().setText("Ver anuncios").setCallbackData("ver anuncios;"+selectEst.getIdCurso()));
                        rowInlineOpcionesCursoEstu2.add(new InlineKeyboardButton().setText("Tomar examen").setCallbackData("tomar examen;"+selectEst.getIdCurso()));

                        rowsInlineOpcionesCursoEstu.add(rowInlineOpcionesCursoEstu);
                        rowsInlineOpcionesCursoEstu.add(rowInlineOpcionesCursoEstu2);

                        markupInlineOpcionesCursoEstu.setKeyboard(rowsInlineOpcionesCursoEstu);
                        chatResponse.setReplyMarkup(markupInlineOpcionesCursoEstu);
                        break;
                    case "subir archivo":
                        chatResponse = new EditMessageText()
                                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                .setText("Envie archivos solamente en formato PDF, Word, Excel");
                        break;
                    case "crear examen":
                        Curso selectExamen=cursoBl.findCursoByCursoId(Integer.parseInt(cursos[1]));
                        chatResponse = new EditMessageText()
                                .setChatId(lastMessage.getChatId())
                                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                .setText(selectExamen.getIdCurso()+">Ingrese nombre del examen:");
                        break;
                    case "crear anuncio":
                        Curso anunciocurso=cursoBl.findCursoByCursoId(Integer.parseInt(cursos[1]));
                        chatResponse = new EditMessageText()
                                .setChatId(lastMessage.getChatId())
                                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                .setText(anunciocurso.getIdCurso()+">Ingrese el contenido del anuncio: ");
                        break;
                }
            }
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
                case "estudiante":
                    List<String> messagesEstudiante = estudianteBl.processUpdate(update.getCallbackQuery().getFrom());
                    answer = "Gracias por registrarte "+update.getCallbackQuery().getFrom().getFirstName()+" "+update.getCallbackQuery().getFrom().getLastName()+" como estudiante.";
                    answer += " Su ID: "+lastMessage.getChatId();
                    chatResponse = new EditMessageText()
                            .setChatId(lastMessage.getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setText(answer);
                    break;
                case "inscripcion":
                    answer = "Ingrese el codigo de curso al que desea inscribirse: ";
                    chatResponse = new EditMessageText()
                            .setChatId(lastMessage.getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setText(answer);
                    break;
                case "verCursosProf":
                    List<Curso> listCursosProf = cursoBl.cursosbyIdProf(update.getCallbackQuery().getFrom());
                    chatResponse = new EditMessageText()
                            .setChatId(update.getCallbackQuery().getMessage().getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setText("Estos son sus cursos disponibles:");

                    InlineKeyboardMarkup markupInlineCurso = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInlineCurso = new ArrayList<>();
                    List<InlineKeyboardButton> rowInlineCurso = new ArrayList<>();
                    for (Curso curso:listCursosProf) {
                        rowInlineCurso.add(new InlineKeyboardButton().setText("ID->"+curso.getIdCurso()+" "+curso.getNombreCurso()).setCallbackData("cursosProf;"+curso.getIdCurso()));
                    }

                    rowsInlineCurso.add(rowInlineCurso);

                    markupInlineCurso.setKeyboard(rowsInlineCurso);
                    chatResponse.setReplyMarkup(markupInlineCurso);
                    break;
                case "verCursosEst":
                    List<CursoHasEstudiante> listCursosEst = cursoEstudianteBl.cursosPorEstudiante(update.getCallbackQuery().getFrom());
                    List<Curso> listFinal = cursoBl.cursosbyEst(listCursosEst);
                    chatResponse = new EditMessageText()
                            .setChatId(update.getCallbackQuery().getMessage().getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setText("Estos son sus cursos disponibles:");

                    InlineKeyboardMarkup markupInlineCursoEst = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInlineCursoEst = new ArrayList<>();
                    List<InlineKeyboardButton> rowInlineCursoEst = new ArrayList<>();
                    for (Curso curso:listFinal) {
                        rowInlineCursoEst.add(new InlineKeyboardButton().setText("ID->"+curso.getIdCurso()+" "+curso.getNombreCurso()).setCallbackData("cursosEst;"+curso.getIdCurso()));
                    }

                    rowsInlineCursoEst.add(rowInlineCursoEst);

                    markupInlineCursoEst.setKeyboard(rowsInlineCursoEst);
                    chatResponse.setReplyMarkup(markupInlineCursoEst);
                    break;
            }
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getCallbackQuery().getData(), usuario.getIdUser());
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
