package com.example.ElAulaBot.bl;


import com.example.ElAulaBot.dao.ArchivoRepository;
import com.example.ElAulaBot.domain.Archivo;
import com.example.ElAulaBot.domain.Curso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Service
@Transactional
public class ArchivoBl {
    ArchivoRepository archivoRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoBl.class);

    @Autowired
    public ArchivoBl(ArchivoRepository archivoRepository){
        this.archivoRepository = archivoRepository;
    }

    public Archivo crearArchivo(Curso curso, Update update){
        LOGGER.info("CREANDO Archivo "+ update);
        Archivo newArchivo = new Archivo();
        newArchivo.setIdCurso(curso);
        newArchivo.setNombreAr(update.getMessage().getDocument().getFileName());
        newArchivo.setTamanioAr(update.getMessage().getDocument().getFileSize().toString());
        newArchivo.setUrlAr("D:\\dylan\\");
        newArchivo.setFechaPublicacionAr(new Date());
        newArchivo.setTipoMime(update.getMessage().getDocument().getMimeType());
        newArchivo.setTxhost("localhost");
        newArchivo.setTxuser("admin");
        newArchivo.setTxdate(new Date());
        archivoRepository.save(newArchivo);
        return newArchivo;
    }
}
