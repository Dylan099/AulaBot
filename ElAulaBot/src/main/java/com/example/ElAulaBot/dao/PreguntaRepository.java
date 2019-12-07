package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Examen;
import com.example.ElAulaBot.domain.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {
    List<Pregunta> findAllByStatus(int status);
    List<Pregunta> findPreguntaByIdExamen(Examen idExamen);
    Pregunta findPreguntaByIdPregunta(int idPregunta);
}
