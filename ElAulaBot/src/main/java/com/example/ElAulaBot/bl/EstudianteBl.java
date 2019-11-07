package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.EstudianteRepository;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.dto.EstudianteDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteBl {

    EstudianteRepository estudianteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteBl.class);


    @Autowired
    public EstudianteBl (EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
    }


    public List<EstudianteDto> findAllEstudiante(){
        List<EstudianteDto> estudianteDtoList = new ArrayList<>();
        for(Estudiante estudiante: estudianteRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            estudianteDtoList.add(new EstudianteDto(estudiante));
        }
        return estudianteDtoList;
    }

    public Estudiante findEstudianteByChatId(int chatId){
        Estudiante estudiante =this.estudianteRepository.findEstudianteByChatId(chatId);
        if(estudiante != null){
            return estudiante;
        }else{
            LOGGER.info("El usuario no es un estudiante registrado");
            return null;
        }
    }

    private boolean initEstudiante(User user) {
        boolean result = false;
        Estudiante estudiante = estudianteRepository.findEstudianteByChatId(user.getId());
        if (estudiante == null) {
            Estudiante nuevoEstudiante =new Estudiante();
            nuevoEstudiante.setPrimerNombreEs(user.getFirstName());
            if(user.getLastName()==null){
                nuevoEstudiante.setPrimerApellidoEs(" ");
            } else {
                nuevoEstudiante.setPrimerApellidoEs(user.getLastName());
            }
            nuevoEstudiante.setChatId(user.getId());
            nuevoEstudiante.setCelularEs("73048478");
            nuevoEstudiante.setCursoHasEstudianteCollection(null);
            nuevoEstudiante.setStatus(Status.ACTIVE.getStatus());
            nuevoEstudiante.setTxhost("localhost");
            nuevoEstudiante.setTxuser("admin");
            nuevoEstudiante.setTxdate(new Date());
            estudianteRepository.save(nuevoEstudiante);
            result = true;
        }
        return result;
    }

    public List<String> processUpdate(User user) {
        LOGGER.info("Recibiendo solicitud {} ", user);
        List<String> result = new ArrayList<>();
        if (initEstudiante(user)){
            LOGGER.info("Primer inicio de sesion para: {} ", user );
            result.add("Registro completado exitosamente");
        }else{
            LOGGER.info("Bienvenida a: {} ", user );
            result.add("Nuevo textline");
        }
        return result;
    }












}
