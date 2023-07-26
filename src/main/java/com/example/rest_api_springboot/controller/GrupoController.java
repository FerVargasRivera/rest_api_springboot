package com.example.rest_api_springboot.controller;

import com.example.rest_api_springboot.model.Grupo;
import com.example.rest_api_springboot.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GrupoController {
    @Autowired
    GrupoService grupoService;

    @GetMapping("/Grupo")
    public List<Grupo> getGrupos() {
        return grupoService.obtenGrupos();
    }

    @GetMapping(value="/Grupo/Inactivos")
    List<Grupo> allInactivos()
    {
        return grupoService.gruposInactivos();
    }

    @GetMapping("/Grupo/{id}")
    Grupo getById(@PathVariable Long id) {
        return grupoService.obtenGrupo(id);
    }

    @PostMapping("/Grupo")
    Grupo createNew(@Valid @RequestBody Grupo newGrupo) {
        return grupoService.guardaGrupo(newGrupo);
    }

    @DeleteMapping("/Grupo/{id}")
    String delete(@PathVariable Long id) {
        boolean res = this.grupoService.eliminaGrupo(id);
        if(res){
            return "Se eliminó correctamente el grupo con el id " + id;
        }else{
            return "No se pudo eliminar el grupo con el id " + id;
        }
    }

    @PutMapping("/Grupo/{id}")
    Grupo updateOrCreate(@Valid @RequestBody Grupo newGrupo, @PathVariable Long id) {
        return grupoService.actualizaOCreaGrupo(newGrupo, id);
    }
}
