package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Estudiante;
import com.example.rest_api_springboot.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//Definimos los metodos para llamar a los metodos de nuestros servicios
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping("/Estudiante")
    public List<Estudiante> getEstudiantes() {
        return estudianteService.obtenEstudiantes();
    }

    @GetMapping(value="/Estudiante/Inactivos")
    List<Estudiante> allInactivos()
    {
        return estudianteService.estudiantesInactivos();
    }

    @GetMapping("/Estudiante/{id}")
    Estudiante getById(@PathVariable Long id) {
        return estudianteService.obtenEstudiante(id);
    }

    @PostMapping("/Estudiante")
    Estudiante createNew(@Valid @RequestBody Estudiante newEstudiante) {
        return estudianteService.guardaEstudiante(newEstudiante);
    }

    @DeleteMapping("/Estudiante/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.estudianteService.eliminaEstudiante(id);
        if(res){
            return "Se eliminó correctamente el estudiante con el id " + id;
        }else{
            return "No se pudo eliminar el estudiante con el id " + id;
        }
    }

    @PutMapping("/Estudiante/{id}")
    Estudiante updateOrCreate(@Valid @RequestBody Estudiante newEstudiante, @PathVariable Long id) {
        return estudianteService.actualizaOCreaEstudiante(newEstudiante, id);
    }
}
