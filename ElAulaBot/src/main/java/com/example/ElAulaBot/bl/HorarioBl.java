package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.HorarioRepository;
import com.example.ElAulaBot.domain.Horario;
import com.example.ElAulaBot.dto.HorarioDto;
import com.example.ElAulaBot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioBl {
    HorarioRepository horarioRepository;

    @Autowired
    public HorarioBl(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<HorarioDto> findAllHorario(){
        List<HorarioDto> horarioDtos = new ArrayList<>();
        for(Horario horario: horarioRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            horarioDtos.add(new HorarioDto(horario));
        }
        return horarioDtos;
    }


}
