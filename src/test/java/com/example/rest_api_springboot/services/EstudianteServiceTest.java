package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.model.Estudiante;
import com.example.rest_api_springboot.persistence.EstudianteRepository;
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
public class EstudianteServiceTest {

    @InjectMocks
    private EstudianteService estudianteService;
    @Mock
    private EstudianteRepository estudianteRepository;
    private Estudiante estudiante1, estudiante2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        estudiante1 = new Estudiante(1L,"Andres","Rocha","Mendez",18,"andy_rocha@gmail.com",1,null);
        estudiante2 = new Estudiante(2L,"Jose Julian","Ortega","Hernandez",17,"jose_ortega@gmail.com",1,null);
    }

    @Test
    void obtenEstudiantes() {
        List<Estudiante> grupoList = new ArrayList<>();

        grupoList.add(estudiante1);
        grupoList.add(estudiante2);
        when(estudianteRepository.findAll()).thenReturn(grupoList);
        List<Estudiante> result = estudianteService.obtenEstudiantes();

        assertEquals(2, result.size());
        assertEquals("Andres", result.get(0).getNombre());
        assertEquals("Jose Julian", result.get(1).getNombre());
    }

    @Test
    void obtenEstudiante() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante1));
        Estudiante result = estudianteService.obtenEstudiante(1L).get();
        assertEquals("Andres", result.getNombre());
    }

    @Test
    void guardaEstudiante() {
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante1);
        Estudiante result = estudianteService.guardaEstudiante(estudiante1);

        assertNotNull(result);
        assertEquals(result.getNombre(), "Andres");
        assertEquals(result.getEstudianteId(), 1L);
    }

    @Test
    void eliminaEstudiante() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante1));
        estudianteService.eliminaEstudiante(1L);
        Estudiante result = estudianteService.obtenEstudiante(1L).get();
        assertEquals(0,result.getActivo());
    }

    @Test
    void actualizaOCreaEstudiante() {
        Estudiante estudiante3 = new Estudiante();
        estudiante3.setNombre("Nicolas");
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante3));
        estudianteService.actualizaOCreaEstudiante(estudiante3, 1L);
        Estudiante result = estudianteService.obtenEstudiante(1L).get();
        assertEquals("Nicolas",result.getNombre());
    }
}