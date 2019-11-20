package com.example.ElAulaBot.api;

import com.example.ElAulaBot.bl.CursoEstudianteBl;
import com.example.ElAulaBot.dto.CursoEstudianteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cursoEstudiante")
public class CursoEstudianteController {

    private CursoEstudianteBl cursoEstudianteBl;

    public CursoEstudianteController(CursoEstudianteBl cursoEstudianteBl) {
        this.cursoEstudianteBl = cursoEstudianteBl;
    }

    @Autowired

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    List<CursoEstudianteDto> all(){
        return cursoEstudianteBl.findAllCursoEstudiante();
    }






}
