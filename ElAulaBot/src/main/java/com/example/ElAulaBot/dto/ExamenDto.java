package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Examen;

public class ExamenDto {
    int idExamen;
    int idCurso;
    int puntuacion;
    String fechaCreacionEx;
    int status;

    public ExamenDto() {
    }

    public ExamenDto(Examen examen) {
        this.idExamen = examen.getIdExamen();
        this.idCurso = examen.getIdCurso().getIdCurso();
        this.puntuacion = Integer.parseInt(examen.getPuntuacion().toString());
        this.fechaCreacionEx = examen.getFechaPublicacionEx().toString();
        this.status = examen.getStatus();
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFechaCreacionEx() {
        return fechaCreacionEx;
    }

    public void setFechaCreacionEx(String fechaCreacionEx) {
        this.fechaCreacionEx = fechaCreacionEx;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
