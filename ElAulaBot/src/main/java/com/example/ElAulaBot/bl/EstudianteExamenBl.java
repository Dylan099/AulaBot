package com.example.ElAulaBot.bl;

import com.example.ElAulaBot.dao.EstudianteExamenRepository;
import com.example.ElAulaBot.dao.EstudianteRepository;
import com.example.ElAulaBot.dao.ExamenRepository;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.domain.EstudianteHasExamen;
import com.example.ElAulaBot.domain.Examen;
import com.example.ElAulaBot.dto.EstudianteExamenDto;
import com.example.ElAulaBot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EstudianteExamenBl {

    EstudianteRepository estudianteRepository;
    EstudianteExamenRepository estudianteExamenRepository;
    ExamenRepository examenRepository;

    @Autowired
    public EstudianteExamenBl(EstudianteRepository estudianteRepository, EstudianteExamenRepository estudianteExamenRepository, ExamenRepository examenRepository) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteExamenRepository = estudianteExamenRepository;
        this.examenRepository = examenRepository;
    }

    public EstudianteHasExamen findEstudianteHasExamenByIdExamenAndIdEstudiante(int var2)
    {
        EstudianteHasExamen estudianteHasExamen = estudianteExamenRepository.findEstudianteHasExamenByIdEhe(var2);
        if(estudianteHasExamen!=null)
        {
            return estudianteHasExamen;
        }else{
            return null;
        }
    }
    public EstudianteHasExamen findEstudianteHasExamenByIdEhe(int waso){
        EstudianteHasExamen estudianteHasExamen = estudianteExamenRepository.findEstudianteHasExamenByIdEhe(waso);
        if(estudianteHasExamen!=null)
        {
            return estudianteHasExamen;
        }else{
            return null;
        }
    }
    public EstudianteHasExamen estudianteExamen(Estudiante estudiante, Examen examen){
        EstudianteHasExamen estudianteHasExamen = new EstudianteHasExamen();
        estudianteHasExamen.setIdEstudiante(estudiante);
        estudianteHasExamen.setIdExamen(examen);
        estudianteHasExamen.setTxuser("admin");
        estudianteHasExamen.setTxhost("localhost");
        estudianteHasExamen.setTxdate(new Date());
        estudianteHasExamen.setNotaExamen(0.0f);
        estudianteHasExamen.setStatus(Status.ACTIVE.getStatus());
        estudianteExamenRepository.save(estudianteHasExamen);
        return estudianteHasExamen;

    }

    public boolean verificarEstudiante(Estudiante estudiante, Examen examen){
        boolean estado = false;
        EstudianteHasExamen estudianteHasExamen = this.estudianteExamenRepository.findEstudianteHasExamenByIdEstudianteAndIdExamen(estudiante,examen);
        if (estudianteHasExamen != null){
            estado = true;
        }
        return estado;
    }

    public float verificarNotaEstudiante(Estudiante estudiante, Examen examen){
        EstudianteHasExamen estudianteHasExamen = this.estudianteExamenRepository.findEstudianteHasExamenByIdEstudianteAndIdExamen(estudiante,examen);
        return estudianteHasExamen.getNotaExamen();
    }

    public List<EstudianteHasExamen> findByIdExamen(Examen examen){
        List<EstudianteHasExamen> estudianteHasExamenList  = this.estudianteExamenRepository.findAllByIdExamen(examen);
        if(!estudianteHasExamenList.isEmpty()){
            return estudianteHasExamenList;
        }
        return null;
    }

    public List<EstudianteHasExamen> listNotas(Estudiante idEst){
        List<EstudianteHasExamen> examenList = this.estudianteExamenRepository.findAllByIdEstudiante(idEst);
        if(!examenList.isEmpty()){
            return examenList;
        }
        return null;
    }

}
