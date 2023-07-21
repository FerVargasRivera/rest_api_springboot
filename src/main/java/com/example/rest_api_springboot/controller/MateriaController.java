package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.persistence.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class MateriaController {
    @Autowired
    MateriaRepository materiaRepository;

    @GetMapping("/Materia")
    List<Materia> all() {
        return materiaRepository.findAll();
    }

    @GetMapping("/Materia/{id}")
    Materia getById(@PathVariable Long id) {

        return materiaRepository.findById(id).get();
    }

    @PostMapping("/Materia")
    Materia createNew(@RequestBody Materia newMateria) {
        return materiaRepository.save(newMateria);
    }

    @DeleteMapping("/Materia/{id}")
    void delete(@PathVariable Long id) {
        materiaRepository.deleteById(id);
    }

    @PutMapping("/Materia/{id}")
    Materia updateOrCreate(@RequestBody Materia newMateria, @PathVariable Long MateriaId) {

        return materiaRepository.findById(MateriaId)
                .map(materia -> {
                    materia.setNombre(newMateria.getNombre());
                    return materiaRepository.save(materia);
                })
                .orElseGet(() -> {
                    newMateria.setMateriaId(MateriaId);
                    return materiaRepository.save(newMateria);
                });
    }
}
