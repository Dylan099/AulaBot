package com.example.ElAulaBot.api;

import com.example.ElAulaBot.bl.CursoBl;
import com.example.ElAulaBot.bl.ProfesorBl;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.dto.CursoDto;
import com.example.ElAulaBot.dto.ProfesorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/curso")
public class CursoController {

    private CursoBl cursoBl;

    @Autowired
    public CursoController(CursoBl cursoBl) {
        this.cursoBl = cursoBl;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    List<CursoDto>all(){
        return cursoBl.findAllCurso();
    }


}
