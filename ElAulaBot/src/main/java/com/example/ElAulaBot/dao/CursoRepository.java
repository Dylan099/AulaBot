package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso,Integer> {
    List<Curso> findAllByStatus(int status);
    List<Curso> findAllByIdProfesorAndStatus(Profesor idProf, Integer status);
    List<Curso> findAllByIdCursoAndStatus(Curso curso, Integer status);
    Curso findCursoByIdCurso(int idCurso);
    Curso findCursoByCodigoCurso(String codigoCurso);
    Curso findCursoByNombreCursoAndIdProfesor(String nombre, Integer idProfesor);

}
