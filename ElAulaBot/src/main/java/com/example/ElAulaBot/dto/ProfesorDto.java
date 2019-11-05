package com.example.ElAulaBot.dto;


import com.example.ElAulaBot.domain.Profesor;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class ProfesorDto {

    private Integer idProfesor;
    private int chatId;
    private String celularPr;
    private String primerNombrePr;
    private String primerApellidoPr;
    private String segundoApellidoPr;
    private int status;

    private List<CursoDto> cursoList;

    public ProfesorDto() {

    }

    public ProfesorDto(Profesor profesor) {
        this.idProfesor = profesor.getIdProfesor();
        this.chatId = profesor.getChatId();
        this.celularPr = profesor.getCelularPr();
        this.primerNombrePr = profesor.getPrimerNombrePr();
        this.primerApellidoPr = profesor.getPrimerApellidoPr();
        this.segundoApellidoPr = profesor.getSegundoApellidoPr();
        this.status = profesor.getStatus();
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public int getChatId() {
        return chatId;
    }

    public String getCelularPr() {
        return celularPr;
    }

    public String getPrimerNombrePr() {
        return primerNombrePr;
    }

    public String getPrimerApellidoPr() {
        return primerApellidoPr;
    }

    public String getSegundoApellidoPr() {
        return segundoApellidoPr;
    }

    public int getStatus() {
        return status;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setCelularPr(String celularPr) {
        this.celularPr = celularPr;
    }

    public void setPrimerNombrePr(String primerNombrePr) {
        this.primerNombrePr = primerNombrePr;
    }

    public void setPrimerApellidoPr(String primerApellidoPr) {
        this.primerApellidoPr = primerApellidoPr;
    }

    public void setSegundoApellidoPr(String segundoApellidoPr) {
        this.segundoApellidoPr = segundoApellidoPr;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCursoList(List<CursoDto> cursoList) {
        this.cursoList = cursoList;
    }

    public List<CursoDto> getCursoList() {
        return cursoList;
    }
}
