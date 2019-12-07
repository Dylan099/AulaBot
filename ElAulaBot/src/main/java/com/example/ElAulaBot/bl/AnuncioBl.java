package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.AnuncioRepository;
import com.example.ElAulaBot.domain.Anuncio;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AnuncioBl {

    AnuncioRepository anuncioRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AnuncioBl.class);

    @Autowired
    public AnuncioBl(AnuncioRepository anuncioRepository){
        this.anuncioRepository = anuncioRepository;
    }

    public List<Anuncio> findAllByCursoId(Curso curso){
        List<Anuncio> anuncios = anuncioRepository.findAllByIdCurso(curso);
        if(!anuncios.isEmpty()){
            return anuncios;
        }else
            LOGGER.info("Sin anuncios");
        return null;
    }

    public Anuncio crearAnuncio(Curso curso, String mensaje){
        LOGGER.info("CREANDO ANUNCIO "+ mensaje);
        Anuncio anuncio = new Anuncio();
        anuncio.setContenidoAn(mensaje);
        anuncio.setIdCurso(curso);
        anuncio.setFechaPublicacionAn(new Date());
        anuncio.setTxhost("localhost");
        anuncio.setTxuser("admin");
        anuncio.setTxdate(new Date());
        anuncio.setStatus(Status.ACTIVE.getStatus());
        anuncioRepository.save(anuncio);
        return anuncio;
    }
}
