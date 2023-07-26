package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @GetMapping("/Materia")
    public List<Materia> getMaterias() {
        return materiaService.obtenMaterias();
    }

    @GetMapping(value="/Materia/Inactivas")
    List<Materia> allInactivas()
    {
        return materiaService.materiasInactivas();
    }

    @GetMapping("/Materia/{id}")
    Materia getById(@PathVariable Long id) {
        return materiaService.obtenMateria(id);
    }

    @PostMapping("/Materia")
    Materia createNew(@Valid @RequestBody Materia newMateria) {
        return materiaService.guardaMateria(newMateria);
    }

    @DeleteMapping("/Materia/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.materiaService.eliminaMateria(id);
        if(res){
            return "Se eliminó correctamente la materia con el id " + id;
        }else{
            return "No se pudo eliminar la materia con el id " + id;
        }
    }

    @PutMapping("/Materia/{id}")
    Materia updateOrCreate(@Valid @RequestBody Materia newMateria, @PathVariable Long id) {
        return materiaService.actualizaOCreaMateria(newMateria, id);
    }
}
