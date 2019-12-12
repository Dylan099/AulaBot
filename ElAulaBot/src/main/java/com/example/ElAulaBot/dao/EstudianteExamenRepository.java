package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.domain.EstudianteHasExamen;
import com.example.ElAulaBot.domain.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteExamenRepository extends JpaRepository<EstudianteHasExamen,Integer> {

    EstudianteHasExamen findEstudianteHasExamenByIdEhe(int estudianteHasExamen);
    EstudianteHasExamen findEstudianteHasExamenByIdEstudianteAndIdExamen(Estudiante estudiante, Examen examen);
    List<EstudianteHasExamen> findAllByIdExamen(Examen examen);
    List<EstudianteHasExamen> findAllByIdEstudiante(Estudiante estudiante);

}
