package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen,Integer> {
    List<Examen> findAllByStatus(int status);
    Examen findExamenByIdExamen(int pk);
}
