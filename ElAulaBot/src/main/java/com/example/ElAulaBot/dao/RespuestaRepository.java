package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Pregunta;
import com.example.ElAulaBot.domain.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
    List<Respuesta> findAllByStatus(int status);
    List<Respuesta> findAllByIdPregunta(Pregunta pregunta);
    Respuesta findRespuestaByIdRespuesta(int pk);
}
