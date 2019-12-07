package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Anuncio;
import com.example.ElAulaBot.domain.Archivo;

public class ArchivoDto {
    int idArchivo;
    int curso_idCurso;
    String nombreArchivo;
    String tipoMime;
    String tamanioArchivo;
    String urlArchivo;
    String fechaPublicacion;
    int status;

    public ArchivoDto(){}

    public ArchivoDto(Archivo archivo) {
        this.idArchivo = archivo.getIdArchivo();
        this.curso_idCurso = archivo.getIdCurso().getIdCurso();
        this.nombreArchivo = archivo.getNombreAr();
        this.tipoMime = archivo.getTipoMime();
        this.tamanioArchivo = archivo.getTamanioAr();
        this.urlArchivo = archivo.getUrlAr();
        this.fechaPublicacion = archivo.getFechaPublicacionAr().toString();
        this.status = archivo.getStatus();

    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public int getCurso_idCurso() {
        return curso_idCurso;
    }

    public void setCurso_idCurso(int curso_idCurso) {
        this.curso_idCurso = curso_idCurso;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public void setTipoMime(String tipoMime) {
        this.tipoMime = tipoMime;
    }

    public String getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(String tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
