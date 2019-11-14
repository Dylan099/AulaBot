package com.example.ElAulaBot.dao;


import com.example.ElAulaBot.domain.Botuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Botuser,Integer> {

    Botuser findByBotUserId(String botUserId);

}
