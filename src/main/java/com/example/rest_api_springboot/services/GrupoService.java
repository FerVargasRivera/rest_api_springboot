package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.controller.handlers.GrupoNotFoundException;
import com.example.rest_api_springboot.model.Estudiante;
import com.example.rest_api_springboot.model.Grupo;
import com.example.rest_api_springboot.persistence.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {
    @Autowired //Injectar las dependencias necesarias
    private final GrupoRepository grupoRepository;

    public List<Grupo> obtenGrupos(){
        return grupoRepository.findAll();
    }

    public List<Grupo> gruposInactivos()
    {

        return grupoRepository.findByActivo(0);
    }

    public Grupo obtenGrupo(long id) {
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNotFoundException(id));
    }

    public Grupo guardaGrupo(Grupo newGrupo) {
        return grupoRepository.save(newGrupo);
    }

    public Boolean eliminaGrupo(long id) {
        if(grupoRepository.findById(id).isPresent())
        {
            grupoRepository.findById(id)
                    .map(grupo -> {
                        grupo.setActivo(0);
                        return  grupoRepository.save(grupo);
                    });
            return true;
        }
        else
        {
            return false;
        }
    }

    public Grupo actualizaOCreaGrupo(Grupo newGrupo, Long id) {

        return grupoRepository.findById(id)
                .map(grupo -> {
                    grupo.setNombre(newGrupo.getNombre());
                    return grupoRepository.save(grupo);
                })
                .orElseGet(() -> {
                    newGrupo.setGrupoId(id);
                    return grupoRepository.save(newGrupo);
                });
    }
}
