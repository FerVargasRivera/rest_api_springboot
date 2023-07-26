package com.example.rest_api_springboot.controller.handlers;

public class GrupoNotFoundException extends RuntimeException{

    private Long id;

    public GrupoNotFoundException(long id){
        super("No se encontro el grupo con el id " + id);
    }
}
