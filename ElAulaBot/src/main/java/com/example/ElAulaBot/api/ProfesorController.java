package com.example.ElAulaBot.api;

import com.example.ElAulaBot.dao.ProfesorRepository;
import com.example.ElAulaBot.domain.Profesor;

import com.example.ElAulaBot.dto.ProfesorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/v1/profesor")
@RestController
public class ProfesorController {

    private ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorController(ProfesorRepository profesorRepository){
        this.profesorRepository = profesorRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProfesorDto>all(){
        List<ProfesorDto> profesorDtoList = new ArrayList<>();
        for (Profesor profesor : profesorRepository.findAll()){
            profesorDtoList.add(new ProfesorDto(profesor));
        }
        return profesorDtoList;
    }


}
