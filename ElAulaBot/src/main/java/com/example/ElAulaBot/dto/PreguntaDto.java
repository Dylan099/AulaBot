package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Pregunta;

import javax.persistence.criteria.CriteriaBuilder;

public class PreguntaDto {
    int preguntaId;
    int examenId;
    String enunciado;
    int puntuacion;
    int status;

    public PreguntaDto() {
    }

    public PreguntaDto(Pregunta pregunta) {
        this.preguntaId = pregunta.getIdPregunta();
        this.examenId = pregunta.getIdExamen().getIdExamen();
        this.enunciado = pregunta.getEnunciado();
        this.puntuacion = Integer.parseInt(pregunta.getPuntuacion().toString());
        this.status = pregunta.getStatus();
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getExamenId() {
        return examenId;
    }

    public void setExamenId(int examenId) {
        this.examenId = examenId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
