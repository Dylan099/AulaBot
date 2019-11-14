package com.example.ElAulaBot.bl;


import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.EstudianteRepository;
import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.dao.UserRepository;
import com.example.ElAulaBot.domain.Botuser;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;


@Service
public class BotBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private UserRepository userRepository;
    private EstudianteRepository estudianteRepository;
    private ProfesorRepository profesorRepository;
    private CursoRepository cursoRepository;
    private ProfesorBl profesorBl;
    private CursoBl cursoBl;
    private long chatId;
    private User user;

    @Autowired
    public BotBl(UserRepository userRepository, EstudianteRepository estudianteRepository,ProfesorRepository profesorRepository,CursoRepository cursoRepository,ProfesorBl profesorBl,CursoBl cursoBl)
    {
        this.userRepository = userRepository;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
        this.cursoRepository = cursoRepository;
        this.profesorBl = profesorBl;
        this.cursoBl = cursoBl;
    }

    // (EN PROCESO )esta funcion es llamada para procesar el update,
    // crear un nuevo usuario si no existe y asi poder controlar multiusuario y donde quedo la conversacion con el bot
    // deberia ser llama en la clase ElAulaBot ( int conversation = botBl.processUpdate(update); y controlar con un switch (conversation)
    /*
    public int processUpddate (Update update){
        LOGGER.info("Receiving an update from user {}",update);
        int response = 0;
        if(isNewUser(update)){
            LOGGER.info("First time using app for:{}",update.getMessage().getFrom());
            response = 1;
        }
        else{
            Integer in;

            Profesor profesor;

            Botuser botuser = userRepository.findByBotUserId(update.getMessage().getFrom().getId().toString());
            int last_conversation = botuser.getConversationId();
            switch (last_conversation){
                case 1:
                    in = botuser.getProfesorIdProfesor1().getProfesorIdProfesor().getIdProfesor();
                    LOGGER.info("Buscando el id{} en Profesor",in);
                    profesor = profesorRepository.findById(in).get();
                    profesor.setPrimerApellidoPr(update.getMessage().getText());
                    profesorRepository.save(profesor);
                    botuser.setConversationId(2);
                    userRepository.save(botuser);
                    response = 2;
                    break;
                case 2:
                    in = botuser.getProfesorIdProfesor1().getProfesorIdProfesor().getIdProfesor();
                    LOGGER.info("Buscando el id{} en Profesor",in);
                    profesor = profesorRepository.findById(in).get();
                    response = 3;
                    if(update.getMessage().getText().equals("/curso")){

                    }
                    break;

            }

        }
        return response;
    }
/*
    private boolean isNewUser(Update update){
        boolean response = false;
        User user = update.getMessage().getFrom();
        Botuser botuser = userRepository.findByBotUserId(user.getId().toString());
        Curso curso = cursoRepository.findCursoByProfId(user.getId());
        if(botuser==null){
            botuser = new Botuser();
            botuser.setBotUserId(user.getId().toString());
            botuser.setChatUserId(update.getMessage().getChatId().toString());
            botuser.setProfesorIdProfesor1(curso);
            botuser.setConversationId(1);
            botuser.setTxhost("localhost");
            botuser.setTxdate(new Date());
            userRepository.save(botuser);
            response = true;
        }
        return response;
    }
*/
}
