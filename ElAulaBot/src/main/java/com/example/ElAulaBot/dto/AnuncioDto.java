package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Anuncio;

public class AnuncioDto {

    int idAnuncio;
    int curso_idCurso;
    String fechaPublicacion;
    String contenido_an;
    int status;

    public AnuncioDto(){

    }

    public AnuncioDto(Anuncio anuncio) {
        this.idAnuncio = anuncio.getIdAnuncio();
        this.curso_idCurso = anuncio.getIdCurso().getIdCurso();
        this.fechaPublicacion = anuncio.getFechaPublicacionAn().toString();
        this.contenido_an = anuncio.getContenidoAn();
        this.status = anuncio.getStatus();
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getCurso_idCurso() {
        return curso_idCurso;
    }

    public void setCurso_idCurso(int curso_idCurso) {
        this.curso_idCurso = curso_idCurso;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getContenido_an() {
        return contenido_an;
    }

    public void setContenido_an(String contenido_an) {
        this.contenido_an = contenido_an;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
