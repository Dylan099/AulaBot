package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.ExamenRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Examen;
import com.example.ElAulaBot.dto.ExamenDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExamenBl {
    ExamenRepository examenRepository;
    CursoRepository cursoRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamenBl.class);

    @Autowired
    public ExamenBl(ExamenRepository examenRepository, CursoRepository cursoRepository) {
        this.examenRepository = examenRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<ExamenDto> findAllExamen(){
        List<ExamenDto> examenDtos = new ArrayList<>();
        for(Examen examen: examenRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            examenDtos.add(new ExamenDto(examen));
        }
        return examenDtos;
    }
    public Examen findExamenByExamenId(Integer pk){
        Examen examen = this.examenRepository.findExamenByIdExamen(pk);
        if(examen!=null)
            return examen;
        else
            System.out.println("Examem Vacios");
        return null;
    }

    public Examen crearExamen (Curso curso, String nombreEx)
    {
        LOGGER.info("CREANDO CURSO "+nombreEx);
        Examen examen = new Examen();
        examen.setFechaPublicacionEx(new Date());
        examen.setTitulo(nombreEx);
        examen.setIdCurso(curso);
        examen.setTxhost("localhost");
        examen.setTxuser("admin");
        examen.setTxdate(new Date());
        examen.setStatus(Status.ACTIVE.getStatus());
        examenRepository.save(examen);
        return examen;
    }

}
