package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Anuncio;
import com.example.ElAulaBot.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio,Integer> {
    List<Anuncio> findAllByStatus(int Status);
    List<Anuncio> findAllByIdCurso(Curso curso);

}
