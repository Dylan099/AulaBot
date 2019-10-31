package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.EstudianteRepository;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.dto.EstudianteDto;
import com.example.ElAulaBot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteBl {

    EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteBl (EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante findEstudianteById(Integer pk){
        Optional<Estudiante> optional =this.estudianteRepository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("Imposible encontrar estudiante con ID: " + pk);
        }
    }

    public List<EstudianteDto> findAllEstudiante(){
        List<EstudianteDto> estudianteDtoList = new ArrayList<>();
        for(Estudiante estudiante: estudianteRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            estudianteDtoList.add(new EstudianteDto(estudiante));
        }
        return estudianteDtoList;
    }


}
