package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.controller.handlers.EstudianteNotFoundException;
import com.example.rest_api_springboot.model.Estudiante;
import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.persistence.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    @Autowired //Injectar las dependencias necesarias
    private final EstudianteRepository estudianteRepository;

    //Metodos necesarios para consultar, editar y eliminar los registros
    public List<Estudiante> obtenEstudiantes(){
        return estudianteRepository.findAll();
    }

    public List<Estudiante> filtroEstudiante(int status)
    {
        return estudianteRepository.findByActivo(status);
    }

    public Estudiante obtenEstudiante(long id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    public Estudiante guardaEstudiante(Estudiante newEstudiante) {
        return estudianteRepository.save(newEstudiante);
    }

    public Boolean eliminaEstudiante(long id) {
        if(estudianteRepository.findById(id).isPresent())
        {
            estudianteRepository.findById(id)
                    .map(estudiante -> {
                        estudiante.setActivo(0);
                        return  estudianteRepository.save(estudiante);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Estudiante actualizaOCreaEstudiante(Estudiante newEstudiante, Long id) {

        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(newEstudiante.getNombre());
                    estudiante.setApellidoP(newEstudiante.getApellidoP());
                    estudiante.setApellidoM(newEstudiante.getApellidoM());
                    estudiante.setEdad(newEstudiante.getEdad());
                    estudiante.setCorreoContacto(newEstudiante.getCorreoContacto());
                    estudiante.setGrupoId(newEstudiante.getGrupoId());
                    return estudianteRepository.save(estudiante);
                })
                .orElseGet(() -> {
                    newEstudiante.setEstudianteId(id);
                    return estudianteRepository.save(newEstudiante);
                });
    }
}
