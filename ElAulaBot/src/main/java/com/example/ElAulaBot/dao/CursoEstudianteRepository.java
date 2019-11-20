package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.CursoHasEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoEstudianteRepository extends JpaRepository<CursoHasEstudiante,Integer> {
    List<CursoHasEstudiante> findAllByStatus(int Status);
}
