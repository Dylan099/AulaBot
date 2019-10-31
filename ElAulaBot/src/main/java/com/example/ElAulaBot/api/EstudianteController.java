package com.example.ElAulaBot.api;


import com.example.ElAulaBot.bl.EstudianteBl;
import com.example.ElAulaBot.dto.EstudianteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/estudiante")
public class EstudianteController {

    private EstudianteBl estudianteBl;

    @Autowired
    public EstudianteController(EstudianteBl estudianteBl){
        this.estudianteBl = estudianteBl;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    List<EstudianteDto> all(){
        return estudianteBl.findAllEstudiante();
    }





}
