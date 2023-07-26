package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.controller.handlers.ProfesorNotFoundException;
import com.example.rest_api_springboot.model.Grupo;
import com.example.rest_api_springboot.model.Profesor;
import com.example.rest_api_springboot.persistence.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    @Autowired //Injectar las dependencias necesarias
    private final ProfesorRepository profesorRepository;

    public List<Profesor> obtenProfesores(){
        return profesorRepository.findAll();
    }

    public List<Profesor> profesoresInactivos()
    {

        return profesorRepository.findByActivo(0);
    }

    public Profesor obtenProfesor(long id) {
        return profesorRepository.findById(id).orElseThrow(() -> new ProfesorNotFoundException(id));
    }

    public Profesor guardaProfesor(Profesor newProfesor) {
        return profesorRepository.save(newProfesor);
    }

    public Boolean eliminaProfesor(long id) {
        if(profesorRepository.findById(id).isPresent())
        {
            profesorRepository.findById(id)
                    .map(profesor -> {
                        profesor.setActivo(0);
                        return  profesorRepository.save(profesor);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Profesor actualizaOCreaProfesor(Profesor newProfesor, Long id) {

        return profesorRepository.findById(id)
                .map(profesor -> {
                    profesor.setNombre(newProfesor.getNombre());
                    profesor.setApellidoP(newProfesor.getApellidoP());
                    profesor.setApellidoM(newProfesor.getApellidoM());
                    profesor.setEdad(newProfesor.getEdad());
                    profesor.setCorreoContacto(newProfesor.getCorreoContacto());
                    profesor.setGrupoId(newProfesor.getGrupoId());
                    return profesorRepository.save(profesor);
                })
                .orElseGet(() -> {
                    newProfesor.setProfesorId(id);
                    return profesorRepository.save(newProfesor);
                });
    }
}
