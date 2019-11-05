package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario,Integer> {
    List<Horario> findAllByStatus(int status);
}
