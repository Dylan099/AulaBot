package com.example.ElAulaBot.api;

import com.example.ElAulaBot.bl.ProfesorBl;
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

@RestController
@RequestMapping("/v1/profesor")
public class ProfesorController {

    private ProfesorBl profesorBl;

    @Autowired
    public ProfesorController(ProfesorBl profesorBl){
        this.profesorBl = profesorBl;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProfesorDto>all(){
        return profesorBl.findAllProfesor();
    }


}
