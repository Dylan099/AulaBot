package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {
    List<Estudiante> findAllByStatus(int status);

}