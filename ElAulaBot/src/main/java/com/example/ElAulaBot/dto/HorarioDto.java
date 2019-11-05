package com.example.ElAulaBot.dto;

import com.example.ElAulaBot.domain.Horario;

public class HorarioDto {

    int idHorario;
    String horaEntrada;
    String horaSalida;
    String dia;
    int status;

    public HorarioDto(Horario horario){}

    public HorarioDto(int idHorario, String horaEntrada, String horaSalida, String dia, int status) {
        this.idHorario = idHorario;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.dia = dia;
        this.status = status;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
