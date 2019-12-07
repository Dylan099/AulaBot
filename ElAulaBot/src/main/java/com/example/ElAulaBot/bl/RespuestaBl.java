package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.PreguntaRepository;
import com.example.ElAulaBot.dao.RespuestaRepository;
import com.example.ElAulaBot.domain.Pregunta;
import com.example.ElAulaBot.domain.Respuesta;
import com.example.ElAulaBot.dto.RespuestaDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RespuestaBl {
    RespuestaRepository respuestaRepository;
    PreguntaRepository preguntaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RespuestaBl.class);

    @Autowired
    public RespuestaBl(RespuestaRepository respuestaRepository, PreguntaRepository preguntaRepository) {
        this.respuestaRepository = respuestaRepository;
        this.preguntaRepository = preguntaRepository;
    }

    public List<RespuestaDto> findAllRespuestas(){
        List<RespuestaDto> respuestaDtos = new ArrayList<>();
        for(Respuesta respuesta:respuestaRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            respuestaDtos.add(new RespuestaDto(respuesta));
        }
        return respuestaDtos;
    }

    public List<Respuesta> findAllByPreguntaId(Pregunta pregunta){
        List<Respuesta> respuestas = respuestaRepository.findAllByIdPregunta(pregunta);
        if(!respuestas.isEmpty()){
            return respuestas;
        }else
            LOGGER.info("Pregunta sin respuestas" + pregunta.getIdPregunta());
        return null;
    }

    public Respuesta findRespuestaByIdRespuesta(Integer pk){
        Respuesta respuesta=respuestaRepository.findRespuestaByIdRespuesta(pk);
        if(respuesta!=null){
            return respuesta;
        }else
            LOGGER.info("Imposible hallar respuesta");
        return null;
    }

    public Respuesta crearRespuesta(Pregunta pregunta, String enunciado){
        LOGGER.info("Creando Respuesta para -> "+pregunta.getIdPregunta()+" "+enunciado);
        Respuesta respuesta= new Respuesta();
        respuesta.setIdPregunta(pregunta);
        respuesta.setEnunciadoRe(enunciado);
        respuesta.setCorrecto(false);
        respuesta.setStatus(Status.ACTIVE.getStatus());
        respuesta.setTxdate(new Date());
        respuesta.setTxhost("localhost");
        respuesta.setTxuser("admin");
        respuestaRepository.save(respuesta);
        return respuesta;
    }

    public void setRespuestaCorrecta(Update update,List<Respuesta> respuestas){
        Respuesta respuesta = respuestas.get(Integer.parseInt(update.getMessage().getText())-1);
        respuesta.setCorrecto(true);
    }
}
