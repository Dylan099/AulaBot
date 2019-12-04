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

    public List<String> processUpdate(User user,Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        List<String> chatResponse = new ArrayList<>();
        Usuario usuario = initUsuario(user,update);
        continueChatWithUser(update, user, chatResponse, usuario);
        return chatResponse;
    }

    private void continueChatWithUser(Update update, User user, List<String> chatResponse,Usuario usuario) {
        // Obtener el ultimo mensaje que envió el usuario
        Usuario lastMessage = usuarioRepository.findLastChatByUserId(usuario.getIdUser());
        LOGGER.info("Primer mensaje del usuario botUserId{}", user.getId());
        // Preparo la vaiable para retornar la respuesta
        String response = null;
        // Si el ultimo mensaje no existe (es la primera conversación)
        if (lastMessage == null) {
            // Retornamos 1
            LOGGER.info("Primer mensaje del usuario botUserId{}", usuario.getIdUser());
            response = "1";
        } else {
            // Si existe convesasción previa iniciamos la variable del ultimo mensaje en 1
            int lastMessageInt = 0;
            try {
                // Intenemos obtener el ultimo mensaje retornado y lo convertimos a entero.
                // Si la coversin falla en el catch retornamos 1
                lastMessageInt = Integer.parseInt(lastMessage.getLastMessageSent());
                response = "" + (lastMessageInt + 1);
            } catch (NumberFormatException nfe) {
                response = "1";
            }
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), usuario.getIdUser());

        chatResponse.add(response);
    }

    private Usuario initUsuario(User user, Update update) {
            Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
            if (usuario == null) {
                Usuario nuevoUsuario =new Usuario();
                nuevoUsuario.setChatId(String.valueOf(user.getId()));
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
    public void setlastMessageReceived(Update update, User user,String messageReceived){
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageReceived(messageReceived);
    }

    public void setlastMessageSent(Update update, User user,String messageSent){
        Usuario usuario = usuarioRepository.findUsuarioByChatId(String.valueOf(user.getId()));
        usuario.setLastMessageSent(messageSent);
    }








}
