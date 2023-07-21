package com.example.rest_api_springboot.errors;

public class MateriaNotFoundException extends RuntimeException{
    private Long id;

    public MateriaNotFoundException(Long id) {
        super("No se encontro la Materia con el id " + id);
    }
}
