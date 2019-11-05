package com.example.ElAulaBot.api;

import com.example.ElAulaBot.bl.HorarioBl;
import com.example.ElAulaBot.dto.HorarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/horarios")
public class HorarioController {

    private HorarioBl horarioBl;

    @Autowired
   public HorarioController(HorarioBl horarioBl){
       this.horarioBl = horarioBl;
   }

    @RequestMapping(value = "/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    List<HorarioDto> all(){
        return horarioBl.findAllHorario();
    }

}
