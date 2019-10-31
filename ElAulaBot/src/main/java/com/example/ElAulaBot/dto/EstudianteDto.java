package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Estudiante;

public class EstudianteDto {

    private Integer idProfesor;
    private int chatId;
    private String celular;
    private String nombre;
    private int status;

    public EstudianteDto(Estudiante estudiante) {}

    public EstudianteDto(Integer idProfesor, int chatId, String celular, String nombre, int status) {
        this.idProfesor = idProfesor;
        this.chatId = chatId;
        this.celular = celular;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
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
