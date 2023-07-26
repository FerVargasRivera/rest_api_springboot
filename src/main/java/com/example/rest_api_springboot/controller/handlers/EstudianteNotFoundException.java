package com.example.rest_api_springboot.controller.handlers;

public class EstudianteNotFoundException extends RuntimeException{

    private Long id;

    public EstudianteNotFoundException(long id){
        super("No se encontro el estudiante con el id " + id);
    }

}
