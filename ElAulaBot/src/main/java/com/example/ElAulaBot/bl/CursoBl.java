package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.CursoDto;
import com.example.ElAulaBot.dto.ProfesorDto;
import com.example.ElAulaBot.dto.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@Transactional
public class CursoBl {
    CursoRepository cursoRepository;
    ProfesorRepository profesorRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoBl.class);
    @Autowired

    public CursoBl(CursoRepository cursoRepository, ProfesorRepository profesorRepository) {
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    public Curso findCursoByChatId(Integer pk){
        Optional<Curso> optional = this.cursoRepository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("Imposible encontrar el curso con el ID "+pk);
        }
    }

    public List<CursoDto> findAllCurso(){
        List<CursoDto> cursoDtos = new ArrayList<>();
        for(Curso curso: cursoRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            cursoDtos.add(new CursoDto(curso));
        }
        return cursoDtos;
    }

    public void crearCurso (User user, String nombreCurso)
    {
        LOGGER.info("CREANDO CURSO "+nombreCurso);
        Curso newCurso = new Curso();
        Profesor profesor = this.profesorRepository.findProfesorByChatId(user.getId());
        newCurso.setFechaCreacionCu(String.valueOf(new Date()));
        newCurso.setNombreCurso(nombreCurso);
        newCurso.setCodigoCurso(nombreCurso+String.valueOf(new Date())+String.valueOf(user.getId()));
        newCurso.setProfesorIdProfesor(profesor);
        newCurso.setTxhost("localhost");
        newCurso.setTxuser("admin");
        newCurso.setTxdate(new Date());
        cursoRepository.save(newCurso);
        //String codigoCurso = nombreCurso+String.valueOf(new Date())+String.valueOf(user.getId());
    }





}
