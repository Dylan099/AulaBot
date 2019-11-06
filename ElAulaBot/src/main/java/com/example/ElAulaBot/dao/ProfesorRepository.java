package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor,Integer> {
    List<Profesor> findAllByStatus(int status);
    Profesor findProfesorByChatId(int chatId);
}
