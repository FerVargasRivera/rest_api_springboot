package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.services.MateriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Materia Controller", description = "CRUD para la administracion de las materias")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @GetMapping("/Materia")
    @ApiOperation("Obtener todas las materias")
    public List<Materia> getMaterias() {
        return materiaService.obtenMaterias();
    }

    @GetMapping(value="/Materia/Filtrar/{status}")
    @ApiOperation("Obtener todas las materias filtradas por activas o inactivas")
    List<Materia> filtrar(@PathVariable int status)
    {
        return materiaService.filtroMateria(status);
    }

    @GetMapping("/Materia/{id}")
    @ApiOperation("Obtener una materia por medio de su id")
    Materia getById(@PathVariable Long id) {
        return materiaService.obtenMateria(id);
    }

    @PostMapping("/Materia")
    @ApiOperation("Agregar una nueva materia")
    Materia createNew(@Valid @RequestBody Materia newMateria) {
        return materiaService.guardaMateria(newMateria);
    }

    @DeleteMapping("/Materia/{id}")
    @ApiOperation("Eliminar una materia")
    String delete(@PathVariable Long id) {
        boolean res = this.materiaService.eliminaMateria(id);
        if(res){
            return "Se eliminó correctamente la materia con el id " + id;
        }else{
            return "No se pudo eliminar la materia con el id " + id;
        }
    }

    @PutMapping("/Materia/{id}")
    @ApiOperation("Actualizar una materia si es que existe o crearla en caso de que no exista un registro previo")
    Materia updateOrCreate(@Valid @RequestBody Materia newMateria, @PathVariable Long id) {
        return materiaService.actualizaOCreaMateria(newMateria, id);
    }
}
