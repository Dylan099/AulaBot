package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findAllByStatus(int status);
    Usuario findUsuarioByChatId(String chatId);


}
