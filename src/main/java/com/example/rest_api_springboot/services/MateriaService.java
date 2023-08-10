package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.controller.handlers.MateriaNotFoundException;
import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.persistence.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired //Injectar las dependencias necesarias
    MateriaRepository materiaRepository;

    public List<Materia> obtenMaterias(){
        return materiaRepository.findAll();
    }

    public List<Materia> filtroMateria(int status)
    {

        return materiaRepository.findByActivo(status);
    }

    public Optional<Materia> obtenMateria(long id) {
        return materiaRepository.findById(id);

    }

    public Materia guardaMateria(Materia newMateria) {
        return materiaRepository.save(newMateria);
    }

    public Boolean eliminaMateria(long id) {
        if(materiaRepository.findById(id).isPresent())
        {
            materiaRepository.findById(id)
                    .map(materia -> {
                        materia.setActivo(0);
                        return  materiaRepository.save(materia);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Materia actualizaOCreaMateria(Materia newMateria, Long id) {

        return materiaRepository.findById(id)
                .map(materia -> {
                    materia.setNombre(newMateria.getNombre());
                    return materiaRepository.save(materia);
                })
                .orElseGet(() -> {
                    newMateria.setMateriaId(id);
                    return materiaRepository.save(newMateria);
                });
    }
}
