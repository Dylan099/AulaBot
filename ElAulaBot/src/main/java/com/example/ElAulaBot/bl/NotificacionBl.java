package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.domain.CursoHasEstudiante;

import java.util.List;

public interface NotificacionBl {

    public void notificar(List<CursoHasEstudiante> estudiantes, String mensaje);
}
