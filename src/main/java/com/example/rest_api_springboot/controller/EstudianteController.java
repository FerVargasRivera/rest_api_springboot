package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.controller.handlers.EstudianteNotFoundException;
import com.example.rest_api_springboot.model.Estudiante;
import com.example.rest_api_springboot.services.EstudianteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Estudiante Controller", description = "CRUD para la administracion de los estudiantes")
//Definimos los metodos para llamar a los metodos de nuestros servicios
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping("/Estudiante")
    @ApiOperation("Obtener todos los estudiantes")
    public List<Estudiante> getEstudiantes() {
        return estudianteService.obtenEstudiantes();
    }

    @GetMapping(value="/Estudiante/Filtrar/{status}")
    @ApiOperation("Obtener todos los estudiantes filtrados por activos o inactivos")
    List<Estudiante> filtrar(@PathVariable int status)
    {
        return estudianteService.filtroEstudiante(status);
    }

    @GetMapping("/Estudiante/{id}")
    @ApiOperation("Obtener un estudiante por medio de su id")
    Estudiante getById(@PathVariable Long id) {
        return estudianteService.obtenEstudiante(id).orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    @PostMapping("/Estudiante")
    @ApiOperation("Agregar un nuevo estudiante")
    Estudiante createNew(@Valid @RequestBody Estudiante newEstudiante) {
        return estudianteService.guardaEstudiante(newEstudiante);
    }

    @DeleteMapping("/Estudiante/{id}")
    @ApiOperation("Eliminar un estudiante")
    String delete(@PathVariable Long id) {
        boolean res = this.estudianteService.eliminaEstudiante(id);
        if(res){
            return "Se eliminó correctamente el estudiante con el id " + id;
        }else{
            return "No se pudo eliminar el estudiante con el id " + id;
        }
    }

    @PutMapping("/Estudiante/{id}")
    @ApiOperation("Actualizar un estudiante si es que existe o crearlo en caso de que no exista un registro previo")
    Estudiante updateOrCreate(@Valid @RequestBody Estudiante newEstudiante, @PathVariable Long id) {
        return estudianteService.actualizaOCreaEstudiante(newEstudiante, id);
    }
}
