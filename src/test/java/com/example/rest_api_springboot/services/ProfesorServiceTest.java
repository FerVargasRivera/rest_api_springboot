package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.model.Profesor;
import com.example.rest_api_springboot.persistence.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {

    @InjectMocks
    private ProfesorService profesorService;
    @Mock
    private ProfesorRepository profesorRepository;
    private Profesor profesor1, profesor2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        profesor1 = new Profesor(1L,"Luis","Perez","Campos",28,"luis_perez@gmail.com",1,null);
        profesor2 = new Profesor(2L,"Omar","Rangel","Rios",29,"omar_rangel@gmail.com",1,null);
    }

    @Test
    void obtenProfesores() {
        List<Profesor> grupoList = new ArrayList<>();

        grupoList.add(profesor1);
        grupoList.add(profesor2);
        when(profesorRepository.findAll()).thenReturn(grupoList);
        List<Profesor> result = profesorService.obtenProfesores();

        assertEquals(2, result.size());
        assertEquals("Luis", result.get(0).getNombre());
        assertEquals("Omar", result.get(1).getNombre());
    }

    @Test
    void obtenProfesor() {
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor1));
        Profesor result = profesorService.obtenProfesor(1L).get();
        assertEquals("Luis", result.getNombre());
    }

    @Test
    void guardaProfesor() {
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor1);
        Profesor result = profesorService.guardaProfesor(profesor1);

        assertNotNull(result);
        assertEquals(result.getNombre(), "Luis");
        assertEquals(result.getProfesorId(), 1L);
    }

    @Test
    void eliminaProfesor() {
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor1));
        profesorService.eliminaProfesor(1L);
        Profesor result = profesorService.obtenProfesor(1L).get();
        assertEquals(0,result.getActivo());
    }

    @Test
    void actualizaOCreaProfesor() {
        Profesor estudiante3 = new Profesor();
        estudiante3.setNombre("Nicolas");
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(estudiante3));
        profesorService.actualizaOCreaProfesor(estudiante3, 1L);
        Profesor result = profesorService.obtenProfesor(1L).get();
        assertEquals("Nicolas",result.getNombre());
    }
}