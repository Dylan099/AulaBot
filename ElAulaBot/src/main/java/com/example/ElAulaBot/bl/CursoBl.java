package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.CursoDto;
import com.example.ElAulaBot.dto.ProfesorDto;
import com.example.ElAulaBot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoBl {
    CursoRepository cursoRepository;

    @Autowired

    public CursoBl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<CursoDto> findAllCurso(){
        List<CursoDto> cursoDtos = new ArrayList<>();
        for(Curso curso: cursoRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            cursoDtos.add(new CursoDto(curso));
        }
        return cursoDtos;
    }
}
