package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Estudiante;

public class EstudianteDto {

    private Integer idEstudiante;
    private int chatId;
    private String celular;
    private String nombre;
    private int status;

    public EstudianteDto(Estudiante estudiante) {}

    public EstudianteDto(Integer idEstudiante, int chatId, String celular, String nombre, int status) {
        this.idEstudiante = idEstudiante;
        this.chatId = chatId;
        this.celular = celular;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
