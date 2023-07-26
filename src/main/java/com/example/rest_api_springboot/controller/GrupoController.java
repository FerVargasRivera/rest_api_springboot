package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Grupo;
import com.example.rest_api_springboot.services.GrupoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Grupo Controller", description = "CRUD para la administracion de los grupos")
public class GrupoController {
    @Autowired
    GrupoService grupoService;

    @GetMapping("/Grupo")
    @ApiOperation("Obtener todos los grupos")
    public List<Grupo> getGrupos() {
        return grupoService.obtenGrupos();
    }

    @GetMapping(value="/Grupo/Inactivos")
    @ApiOperation("Obtener todos los grupos inactivos")
    List<Grupo> allInactivos()
    {
        return grupoService.gruposInactivos();
    }

    @GetMapping("/Grupo/{id}")
    @ApiOperation("Obtener un grupo por medio de su id")
    Grupo getById(@PathVariable Long id) {
        return grupoService.obtenGrupo(id);
    }

    @PostMapping("/Grupo")
    @ApiOperation("Agregar un nuevo grupo")
    Grupo createNew(@Valid @RequestBody Grupo newGrupo) {
        return grupoService.guardaGrupo(newGrupo);
    }

    @DeleteMapping("/Grupo/{id}")
    @ApiOperation("Eliminar un grupo")
    String delete(@PathVariable Long id) {
        boolean res = this.grupoService.eliminaGrupo(id);
        if(res){
            return "Se eliminó correctamente el grupo con el id " + id;
        }else{
            return "No se pudo eliminar el grupo con el id " + id;
        }
    }

    @PutMapping("/Grupo/{id}")
    @ApiOperation("Actualizar un grupo si es que existe o crearlo en caso de que no exista un registro previo")
    Grupo updateOrCreate(@Valid @RequestBody Grupo newGrupo, @PathVariable Long id) {
        return grupoService.actualizaOCreaGrupo(newGrupo, id);
    }
}
