package com.example.rest_api_springboot.controller.handlers;

public class ProfesorNotFoundException extends RuntimeException{

    private Long id;

    public ProfesorNotFoundException(long id){
        super("No se encontro el profesor con el id " + id);
    }
}
