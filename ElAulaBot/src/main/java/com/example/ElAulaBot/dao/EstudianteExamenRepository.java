package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.EstudianteHasExamen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteExamenRepository extends JpaRepository<EstudianteHasExamen,Integer> {

}
