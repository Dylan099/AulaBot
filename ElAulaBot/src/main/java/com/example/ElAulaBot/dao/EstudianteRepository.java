package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {
}
