package com.example.ElAulaBot.bl;


import com.example.ElAulaBot.dao.CursoEstudianteRepository;
import com.example.ElAulaBot.dao.CursoRepository;
import com.example.ElAulaBot.dao.EstudianteRepository;
import com.example.ElAulaBot.domain.Curso;
import com.example.ElAulaBot.domain.CursoHasEstudiante;
import com.example.ElAulaBot.domain.Estudiante;
import com.example.ElAulaBot.dto.CursoEstudianteDto;
import com.example.ElAulaBot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CursoEstudianteBl {
    CursoEstudianteRepository cursoEstudianteRepository;
    CursoRepository cursoRepository;
    EstudianteRepository estudianteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoEstudianteBl.class);

    @Autowired
    public CursoEstudianteBl(CursoEstudianteRepository cursoEstudianteRepository, CursoRepository cursoRepository, EstudianteRepository estudianteRepository) {
        this.cursoEstudianteRepository = cursoEstudianteRepository;
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public List<CursoEstudianteDto> findAllCursoEstudiante(){
        List<CursoEstudianteDto> cursoEstudianteDtos = new ArrayList<>();
        for(CursoHasEstudiante cursoHasEstudiante: cursoEstudianteRepository.findAllByStatus(Status.ACTIVE.getStatus())){
            cursoEstudianteDtos.add(new CursoEstudianteDto(cursoHasEstudiante));
        }
        return cursoEstudianteDtos;
    }

    public String incribirCurso (User user, String codigoCurso){
        CursoHasEstudiante cursoHasEstudiante = new CursoHasEstudiante();
        Estudiante estudiante = this.estudianteRepository.findEstudianteByChatId(user.getId());
        LOGGER.info("Verificando a "+ estudiante.getPrimerNombreEs()+" con codigo "+codigoCurso);
        Curso curso = this.cursoRepository.findCursoByCodigoCurso(codigoCurso);
        if (curso != null){
            LOGGER.info(curso.getNombreCurso());
            cursoHasEstudiante.setIdEstudiante(estudiante);
            cursoHasEstudiante.setIdCurso(curso);
            cursoHasEstudiante.setTxhost("localhost");
            cursoHasEstudiante.setTxuser("admin");
            cursoHasEstudiante.setTxdate(new Date());
            cursoHasEstudiante.setStatus(Status.ACTIVE.getStatus());
            cursoEstudianteRepository.save(cursoHasEstudiante);
            LOGGER.info("Estudiante "+ estudiante.getPrimerNombreEs()+" registrado en "+curso.getNombreCurso());
            return curso.getNombreCurso();
        }else{
            LOGGER.info("Curso no existente");
            return "";
        }
    }

    public List<CursoHasEstudiante> cursosPorEstudiante(User user){
        Estudiante estudiante=this.estudianteRepository.findEstudianteByChatId(user.getId());
        LOGGER.info("Buscando cursos activos para el estudiante "+estudiante.getPrimerNombreEs());
        List<CursoHasEstudiante> resultado = this.cursoEstudianteRepository.findAllByIdEstudianteAndStatus(estudiante,1);
        return resultado;
    }


}
