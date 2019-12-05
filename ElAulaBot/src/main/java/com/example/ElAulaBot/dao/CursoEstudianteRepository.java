package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.CursoHasEstudiante;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.dto.CursoEstudianteDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoEstudianteRepository extends JpaRepository<CursoHasEstudiante,Integer> {
    List<CursoHasEstudiante> findAllByStatus(int Status);
    CursoEstudianteDto findCursoHasEstudianteByIdEstudiante(Integer idEstudiante);
    List<CursoHasEstudiante> findAllByIdEstudianteAndStatus(Estudiante estudiante, Integer status);
    CursoHasEstudiante findCursoHasEstudianteByIdEstudianteAndIdCurso(Estudiante estudiante, Curso curso);
}
