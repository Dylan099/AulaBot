package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Profesor;

public class CursoDto {
    int idCurso;
    int profesor_idProfesor;
    String fechaCreacionCu;
    String nombreCurso;
    String codigoCurso;
    int status;

    Profesor profesor;

    public CursoDto() {
    }

    public CursoDto(Curso curso) {
        this.idCurso = curso.getIdCurso();
        this.profesor_idProfesor = curso.getIdProfesor().getIdProfesor();
        this.fechaCreacionCu = curso.getFechaCreacionCu();
        this.nombreCurso = curso.getNombreCurso();
        this.codigoCurso = curso.getCodigoCurso();
        this.status = curso.getStatus();
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getProfesor_idProfesor() {
        return profesor_idProfesor;
    }

    public String getFechaCreacionCu() {
        return fechaCreacionCu;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public int getStatus() {
        return status;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setProfesor_idProfesor(int profesor_idProfesor) {
        profesor_idProfesor = profesor_idProfesor;
    }

    public void setFechaCreacionCu(String fechaCreacionCu) {
        this.fechaCreacionCu = fechaCreacionCu;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
