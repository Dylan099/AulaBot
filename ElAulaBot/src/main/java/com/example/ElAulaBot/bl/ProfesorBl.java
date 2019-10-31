package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Profesor;
import com.example.ElAulaBot.dto.ProfesorDto;
import com.example.ElAulaBot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesorBl {
    ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorBl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor findProfesorById(Integer pk){
        Optional<Profesor> optional =this.profesorRepository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("Imposible encontrar el profesor con el ID: "+pk);
        }
    }

    public List<ProfesorDto> findAllProfesor(){
        List<ProfesorDto> profesorList = new ArrayList<>();
        for(Profesor profesor: profesorRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            profesorList.add(new ProfesorDto(profesor));
        }
        return profesorList;
    }
}
