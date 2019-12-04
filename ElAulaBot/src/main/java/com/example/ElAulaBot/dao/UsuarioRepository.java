package com.example.ElAulaBot.dao;

import com.example.ElAulaBot.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findAllByStatus(int status);
    Usuario findUsuarioByChatId(String chatId);
    @Query(value = "SELECT * FROM usuario where  id_user = ? ORDER BY chat_id DESC LIMIT 1", nativeQuery = true)
    Usuario findLastChatByUserId(Integer userId);

}
