package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.EstudianteHasExamen;

public class EstudianteExamenDto {

    int idEhe;
    int estudiante_idEstudiante;
    int examen_idExamen;
    float nota;
    int status;

    public EstudianteExamenDto(EstudianteHasExamen estudianteHasExamen) {
        this.idEhe = estudianteHasExamen.getIdEhe();
        this.estudiante_idEstudiante = estudianteHasExamen.getIdEstudiante().getIdEstudiante();
        this.examen_idExamen = estudianteHasExamen.getIdExamen().getIdExamen();
        this.nota = estudianteHasExamen.getNotaExamen();
        this.status = estudianteHasExamen.getStatus();
    }

    public int getIdEhe() {
        return idEhe;
    }

    public void setIdEhe(int idEhe) {
        this.idEhe = idEhe;
    }

    public int getEstudiante_idEstudiante() {
        return estudiante_idEstudiante;
    }

    public void setEstudiante_idEstudiante(int estudiante_idEstudiante) {
        this.estudiante_idEstudiante = estudiante_idEstudiante;
    }

    public int getExamen_idExamen() {
        return examen_idExamen;
    }

    public void setExamen_idExamen(int examen_idExamen) {
        this.examen_idExamen = examen_idExamen;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
