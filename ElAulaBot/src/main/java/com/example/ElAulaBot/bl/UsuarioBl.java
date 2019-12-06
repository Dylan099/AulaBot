package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.UsuarioRepository;
import com.example.ElAulaBot.domain.Usuario;
import com.example.ElAulaBot.dto.Status;
import com.example.ElAulaBot.dto.UsuarioDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UsuarioBl {
    UsuarioRepository usuarioRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioBl.class);

    @Autowired
    public UsuarioBl(UsuarioRepository usuarioRepository) { this.usuarioRepository = usuarioRepository; }

    public Usuario findUsuarioByChatId(String chatId){
        Usuario usuario = this.usuarioRepository.findUsuarioByChatId(chatId);
        if(usuario != null)
        {
            return usuario;
        }
        else {
            LOGGER.info("El usuario no esta registrado");
            return usuario;
        }
    }

    public List<UsuarioDto> findAllUsuario(){
        List<UsuarioDto> usuarioList = new ArrayList<>();
        for(Usuario usuario: usuarioRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            usuarioList.add(new UsuarioDto(usuario));

        }
        return usuarioList;
    }

    public String processUpdate(Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        Usuario usuario = initUsuario(update);
        String algo = continueChatWithUser(update, usuario);
        setlastMessageSent(update,update.getMessage().getText());
        setlastMessageReceived(update,algo);
        return algo;
    }

    private String continueChatWithUser(Update update,Usuario usuario) {
        // Obtener el ultimo mensaje que envió el usuario
        Usuario lastMessage = usuarioRepository.findLastChatByUserId(usuario.getIdUser());
        System.out.println("AQUIIIIII______________>"+lastMessage.getLastMessageSent()+"  "+lastMessage.getLastMessageReceived());
        LOGGER.info("Primer mensaje del usuario botUserId{}", update.getMessage().getFrom().getId());
        // Preparo la vaiable para retornar la respuesta
        // Si el ultimo mensaje no existe (es la primera conversación)
        String chatResponse=null;

        if (lastMessage == null) {
            // Retornamos 1
            LOGGER.info("Primer mensaje del usuario botUserId{}", usuario.getIdUser());
            chatResponse = "1";
        } else {
            chatResponse = lastMessage.getLastMessageSent();
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), usuario.getIdUser());
        return chatResponse;
    }

    private Usuario initUsuario(Update update) {
            Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(update.getMessage().getFrom().getId()));
            if (usuario == null) {
                Usuario nuevoUsuario =new Usuario();
                nuevoUsuario.setChatId(String.valueOf(update.getMessage().getFrom().getId()));
                nuevoUsuario.setLastMessageSent(update.getMessage().getText());
                nuevoUsuario.setLastMessageReceived(null);
                nuevoUsuario.setTxhost("localhost");
                nuevoUsuario.setTxuser("admin");
                nuevoUsuario.setTxdate(new Date());
                usuarioRepository.save(nuevoUsuario);

            }

        return usuario;
    }
    public String getlastMessageReceived(Update update, User user){
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        return usuario.getLastMessageReceived();
    }

    public String getlastMessageSent(Update update, User user){
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        return usuario.getLastMessageSent();
    }
    public void setlastMessageReceived(Update update, String messageReceived){
        User user = update.getMessage().getFrom();
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageReceived(messageReceived);
    }

    public void setlastMessageSent(Update update, String messageSent){
        User user = update.getMessage().getFrom();
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageSent(messageSent);
    }








}
