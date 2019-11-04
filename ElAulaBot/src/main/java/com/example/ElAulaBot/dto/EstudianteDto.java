package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Estudiante;

public class EstudianteDto {

    private Integer idEstudiante;
    private int chatId;
    private String celularEs;
    private String primerNombreEs;
    private String primerApellidoEs;
    private String segundoApellidoEs;
    private int status;

    public EstudianteDto() {}

    public EstudianteDto(Estudiante estudiante) {
        this.idEstudiante = estudiante.getIdEstudiante();
        this.chatId = estudiante.getChatId();
        this.celularEs = estudiante.getCelularEs();
        this.primerNombreEs = estudiante.getPrimerNombreEs();
        this.primerApellidoEs = estudiante.getPrimerApellidoEs();
        this.segundoApellidoEs = estudiante.getSegundoApellidoEs();
        this.status = estudiante.getStatus();
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public int getChatId() {
        return chatId;
    }

    public String getCelularEs() {
        return celularEs;
    }

    public String getPrimerNombreEs() {
        return primerNombreEs;
    }

    public String getPrimerApellidoEs() {
        return primerApellidoEs;
    }

    public String getSegundoApellidoEs() {
        return segundoApellidoEs;
    }

    public int getStatus() {
        return status;
    }
}
