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

    private boolean initUsuario(User user, Update update) {
        boolean result = false;
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
        return result;
    }





    public List<String> processUpdate(User user,Update update) {
        LOGGER.info("Recibiendo solicitud {} ", user);
        List<String> result = new ArrayList<>();

        if (initUsuario(user,update)) {
            LOGGER.info("Primer inicio de sesion para: {} ",user );
            result.add("Registrado con exito");
        } else { // Mostrar el menu de opciones
            LOGGER.info("Dando bienvenida a: {} ",user );
            result.add("/start");
        }

        //continueChatWihtUser(CpUser, CpChat)

        return result;
    }

}
