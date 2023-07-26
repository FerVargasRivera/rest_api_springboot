package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Profesor;
import com.example.rest_api_springboot.services.ProfesorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Profesor Controller", description = "CRUD para la administracion de los profesores")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/Profesor")
    @ApiOperation("Obtener todos los profesores")
    public List<Profesor> getProfesores() {
        return profesorService.obtenProfesores();
    }

    @GetMapping(value="/Profesor/Inactivos")
    @ApiOperation("Obtener todos los profesores inactivos")
    List<Profesor> allInactivos()
    {
        return profesorService.profesoresInactivos();
    }

    @GetMapping("/Profesor/{id}")
    @ApiOperation("Obtener un profesor por medio de su id")
    Profesor getById(@PathVariable Long id) {
        return profesorService.obtenProfesor(id);
    }

    @PostMapping("/Profesor")
    @ApiOperation("Agregar un nuevo profesor")
    Profesor createNew(@Valid @RequestBody Profesor newProfesor) {
        return profesorService.guardaProfesor(newProfesor);
    }

    @DeleteMapping("/Profesor/{id}")
    @ApiOperation("Eliminar un profesor")
    String delete(@PathVariable Long id) {
        boolean res = this.profesorService.eliminaProfesor(id);
        if(res){
            return "Se eliminó correctamente el profesor con el id " + id;
        }else{
            return "No se pudo eliminar el profesor con el id " + id;
        }
    }

    @PutMapping("/Profesor/{id}")
    @ApiOperation("Actualizar un profesor si es que existe o crearlo en caso de que no exista un registro previo")
    Profesor updateOrCreate(@Valid @RequestBody Profesor newProfesor, @PathVariable Long id) {
        return profesorService.actualizaOCreaProfesor(newProfesor, id);
    }
}
