package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.CursoHasEstudiante;
import com.example.ElAulaBot.domain.Estudiante;

public class CursoEstudianteDto {

    int idChe;
    int curso_idCurso;
    int estudiante_idEstudiante;
    int status;

    public CursoEstudianteDto(){};

    public CursoEstudianteDto(CursoHasEstudiante cursoHasEstudiante) {
        this.idChe = cursoHasEstudiante.getIdChe();
        this.curso_idCurso = cursoHasEstudiante.getIdCurso().getIdCurso();
        this.estudiante_idEstudiante = cursoHasEstudiante.getIdEstudiante().getIdEstudiante();
        this.status = cursoHasEstudiante.getStatus();
    }

    public int getIdChe() {
        return idChe;
    }

    public void setIdChe(int idChe) {
        this.idChe = idChe;
    }

    public int getCurso_idCurso() {
        return curso_idCurso;
    }

    public void setCurso_idCurso(int curso_idCurso) {
        this.curso_idCurso = curso_idCurso;
    }

    public int getEstudiante_idEstudiante() {
        return estudiante_idEstudiante;
    }

    public void setEstudiante_idEstudiante(int estudiante_idEstudiante) {
        this.estudiante_idEstudiante = estudiante_idEstudiante;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
