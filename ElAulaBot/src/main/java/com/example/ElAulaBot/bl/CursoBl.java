package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.CursoDto;
import com.example.ElAulaBot.dto.ProfesorDto;
import com.example.ElAulaBot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoBl {
    CursoRepository cursoRepository;

    @Autowired

    public CursoBl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso findCursoById(Integer pk){
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





}
