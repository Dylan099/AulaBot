package com.example.ElAulaBot.dto;


import com.example.ElAulaBot.domain.Profesor;

public class ProfesorDto {

    private Integer idProfesor;
    private int chatId;
    private String celular;
    private String nombre;
    private int status;

    public ProfesorDto() {

    }

    public ProfesorDto(Profesor profesor) {
        this.idProfesor = profesor.getIdProfesor();
        this.chatId = profesor.getChatId();
        this.celular = profesor.getCelular();
        this.nombre = profesor.getNombre();
        this.status = profesor.getStatus();
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
