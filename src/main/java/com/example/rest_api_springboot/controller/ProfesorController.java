package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Profesor;
import com.example.rest_api_springboot.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/Profesor")
    public List<Profesor> getProfesores() {
        return profesorService.obtenProfesores();
    }

    @GetMapping(value="/Profesor/Inactivos")
    List<Profesor> allInactivos()
    {
        return profesorService.profesoresInactivos();
    }

    @GetMapping("/Profesor/{id}")
    Profesor getById(@PathVariable Long id) {
        return profesorService.obtenProfesor(id);
    }

    @PostMapping("/Profesor")
    Profesor createNew(@Valid @RequestBody Profesor newProfesor) {
        return profesorService.guardaProfesor(newProfesor);
    }

    @DeleteMapping("/Profesor/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.profesorService.eliminaProfesor(id);
        if(res){
            return "Se eliminó correctamente el profesor con el id " + id;
        }else{
            return "No se pudo eliminar el profesor con el id " + id;
        }
    }

    @PutMapping("/Profesor/{id}")
    Profesor updateOrCreate(@Valid @RequestBody Profesor newProfesor, @PathVariable Long id) {
        return profesorService.actualizaOCreaProfesor(newProfesor, id);
    }
}
