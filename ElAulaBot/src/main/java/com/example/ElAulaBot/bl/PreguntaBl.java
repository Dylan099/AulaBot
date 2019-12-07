package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.ExamenRepository;
import com.example.ElAulaBot.dao.PreguntaRepository;
import com.example.ElAulaBot.domain.Examen;
import com.example.ElAulaBot.domain.Pregunta;
import com.example.ElAulaBot.dto.PreguntaDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PreguntaBl {
    PreguntaRepository preguntaRepository;
    ExamenRepository examenRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PreguntaBl.class);

    @Autowired
    public PreguntaBl(PreguntaRepository preguntaRepository, ExamenRepository examenRepository) {
        this.preguntaRepository = preguntaRepository;
        this.examenRepository = examenRepository;
    }

    public List<PreguntaDto> findAllPregunta(){
        List<PreguntaDto> preguntaDtos = new ArrayList<>();
        for(Pregunta pregunta: preguntaRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            preguntaDtos.add(new PreguntaDto(pregunta));
        }
        return preguntaDtos;
    }

    public List<Pregunta> findPreguntaByIdExamen(Examen examen){
        List<Pregunta> preguntas = preguntaRepository.findPreguntaByIdExamen(examen);
        if(!preguntas.isEmpty()){
            return preguntas;
        }else
            LOGGER.info("Examen Vacio "+examen.getIdExamen()+" "+examen.getTitulo());
        return null;
    }

    public Pregunta findPreguntaByIdPregunta(Integer pk){
        Pregunta pregunta = preguntaRepository.findPreguntaByIdPregunta(pk);
        if(pregunta!=null){
            return pregunta;
        }else
            LOGGER.info("Imposible encontrar pregunta");
        return null;
    }

    public Pregunta crearPregunta(Examen examen,String enunciado){
        LOGGER.info("Creando pregunta para examen -> "+examen.getIdExamen() + " "+enunciado);
        Pregunta pregunta=new Pregunta();
        pregunta.setIdExamen(examen);
        pregunta.setEnunciado(enunciado);
        pregunta.setPuntuacion(examen.getPuntuacion());
        pregunta.setTxhost("localhost");
        pregunta.setTxuser("admin");
        pregunta.setTxdate(new Date());
        pregunta.setStatus(Status.ACTIVE.getStatus());
        preguntaRepository.save(pregunta);
        return pregunta;
    }
}
