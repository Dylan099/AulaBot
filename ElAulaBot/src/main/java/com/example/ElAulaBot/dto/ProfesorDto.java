package com.example.ElAulaBot.dto;


import com.example.ElAulaBot.domain.Profesor;

public class ProfesorDto {

    private Integer idProfesor;
    private int chatId;
    private String celularPr;
    private String primerNombrePr;
    private String primerApellidoPr;
    private String segundoApellidoPr;
    private int status;

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
}
