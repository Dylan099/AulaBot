package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Respuesta;

public class RespuestaDto {
    int idRespuesta;
    int idPregunta;
    String enunciadoRes;
    boolean correcta;
    int status;

    public RespuestaDto() {
    }

    public RespuestaDto(Respuesta respuesta) {
        this.idRespuesta = respuesta.getIdRespuesta();
        this.idPregunta = respuesta.getIdPregunta().getIdPregunta();
        this.enunciadoRes = respuesta.getEnunciadoRe();
        this.correcta = respuesta.getCorrecto();
        this.status = respuesta.getStatus();
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEnunciadoRes() {
        return enunciadoRes;
    }

    public void setEnunciadoRes(String enunciadoRes) {
        this.enunciadoRes = enunciadoRes;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
