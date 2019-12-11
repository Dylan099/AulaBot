package com.example.ElAulaBot.bl;


import com.example.ElAulaBot.app.ElAulaBot;
import com.example.ElAulaBot.dao.ArchivoRepository;
import com.example.ElAulaBot.domain.Archivo;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.CursoHasEstudiante;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArchivoBl {
    ArchivoRepository archivoRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoBl.class);

    @Autowired
    public ArchivoBl(ArchivoRepository archivoRepository){
        this.archivoRepository = archivoRepository;
    }

    public Archivo crearArchivo(Curso curso, Update update, List<CursoHasEstudiante> estudiantesNotificar1){
        LOGGER.info("CREANDO Archivo "+ update);
        Archivo newArchivo = new Archivo();
        newArchivo.setIdCurso(curso);
        newArchivo.setNombreAr(update.getMessage().getDocument().getFileName());
        newArchivo.setTamanioAr(update.getMessage().getDocument().getFileSize().toString());
        newArchivo.setUrlAr(update.getMessage().getDocument().getFileId().toString());
        newArchivo.setFechaPublicacionAr(new Date());
        newArchivo.setTipoMime(update.getMessage().getDocument().getMimeType());
        newArchivo.setTxhost("localhost");
        newArchivo.setTxuser("admin");
        newArchivo.setTxdate(new Date());
        newArchivo.setStatus(Status.ACTIVE.getStatus());
        archivoRepository.save(newArchivo);

        ElAulaBot elAulaBot = new ElAulaBot();
        elAulaBot.notificarDocument(estudiantesNotificar1, update);

        return newArchivo;
    }
}
