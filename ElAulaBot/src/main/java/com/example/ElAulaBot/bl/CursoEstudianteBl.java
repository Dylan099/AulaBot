package com.example.ElAulaBot.bl;


import com.example.ElAulaBot.dao.CursoEstudianteRepository;
import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.EstudianteRepository;
import com.example.ElAulaBot.domain.CursoHasEstudiante;
import com.example.ElAulaBot.dto.CursoEstudianteDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CursoEstudianteBl {
    CursoEstudianteRepository cursoEstudianteRepository;
    CursoRepository cursoRepository;
    EstudianteRepository estudianteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoEstudianteBl.class);

    @Autowired
    public CursoEstudianteBl(CursoEstudianteRepository cursoEstudianteRepository, CursoRepository cursoRepository, EstudianteRepository estudianteRepository) {
        this.cursoEstudianteRepository = cursoEstudianteRepository;
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public List<CursoEstudianteDto> findAllCursoEstudiante(){
        List<CursoEstudianteDto> cursoEstudianteDtos = new ArrayList<>();
        for(CursoHasEstudiante cursoHasEstudiante: cursoEstudianteRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            cursoEstudianteDtos.add(new CursoEstudianteDto(cursoHasEstudiante));
        }
        return cursoEstudianteDtos;
    }




}
