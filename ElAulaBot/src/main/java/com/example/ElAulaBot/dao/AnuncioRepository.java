package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio,Integer> {
    List<Anuncio> findAllByStatus(int Status);

}
