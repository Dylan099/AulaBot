package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivoRepository extends JpaRepository <Archivo,Integer> {
    List<Archivo> findAllByStatus(int Status);
}
