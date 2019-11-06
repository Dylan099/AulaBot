package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.CursoDto;
import com.example.ElAulaBot.dto.ProfesorDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorBl {
    ProfesorRepository profesorRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfesorBl.class);

    @Autowired
    public ProfesorBl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor findProfesorById(Integer pk){
        Optional<Profesor> optional =this.profesorRepository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("Imposible encontrar el profesor con el ID: "+pk);
        }
    }
    public Profesor findProfesorByChatId(Integer chatId){
        Profesor profesor =this.profesorRepository.findProfesorByChatId(chatId);
        if(profesor != null){
            return profesor;
        }else{
            throw new RuntimeException("Imposible encontrar el profesor con el Chat ID: "+chatId);
        }
    }

    public List<ProfesorDto> findAllProfesor(){
        List<ProfesorDto> profesorList = new ArrayList<>();
        for(Profesor profesor: profesorRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            profesorList.add(new ProfesorDto(profesor));

        }
        return profesorList;
    }

    private boolean initProfesor(User user) {
        boolean result = false;
        Profesor profesor = profesorRepository.findProfesorByChatId(user.getId());
        if (profesor == null) {
            Profesor nuevoProfesor =new Profesor();
            nuevoProfesor.setPrimerNombrePr(user.getFirstName());
            if(user.getLastName()==null)
                nuevoProfesor.setPrimerApellidoPr("N/A");
            else
                nuevoProfesor.setPrimerApellidoPr(user.getLastName());
            nuevoProfesor.setChatId(user.getId());
            nuevoProfesor.setCelularPr(user.getLanguageCode());
            nuevoProfesor.setCursoCollection(null);
            nuevoProfesor.setStatus(Status.ACTIVE.getStatus());
            nuevoProfesor.setTxhost("localhost");
            nuevoProfesor.setTxuser("admin");
            nuevoProfesor.setTxdate(new Date());
            profesorRepository.save(nuevoProfesor);
            result = true;
        }
        return result;
    }

    public List<String> processUpdate(User user) {
        LOGGER.info("Recibiendo solicitud {} ", user);
        List<String> result = new ArrayList<>();

        if (initProfesor(user)) {
            LOGGER.info("Primer inicio de sesion para: {} ",user );
            result.add("Registrado con exito");
        } else { // Mostrar el menu de opciones
            LOGGER.info("Dando bienvenida a: {} ",user );
            result.add("Aqui va el nuevo textline");
        }

        //continueChatWihtUser(CpUser, CpChat)


        return result;
    }
}
