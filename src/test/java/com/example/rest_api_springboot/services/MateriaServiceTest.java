package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.model.Materia;
import com.example.rest_api_springboot.persistence.MateriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

    @InjectMocks
    private MateriaService materiaService;
    @Mock
    private MateriaRepository materiaRepository;
    private Materia materia1, materia2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        materia1 = new Materia(1L,"Matematicas",1);
        materia2 = new Materia(2L,"Geografia",1);
    }

    @Test
    void obtenMaterias() {
        List<Materia> materiaList = new ArrayList<>();

        materiaList.add(materia1);
        materiaList.add(materia2);
        when(materiaRepository.findAll()).thenReturn(materiaList);
        List<Materia> result = materiaService.obtenMaterias();

        assertEquals(2, result.size());
        assertEquals("Matematicas", result.get(0).getNombre());
        assertEquals("Geografia", result.get(1).getNombre());
    }

    @Test
    void obtenMateria() {
        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materia1));
        Materia result = materiaService.obtenMateria(1L).get();
        assertEquals("Matematicas", result.getNombre());
    }

    @Test
    void guardaMateria() {
        when(materiaRepository.save(any(Materia.class))).thenReturn(materia1);
        Materia result = materiaService.guardaMateria(materia1);

        assertNotNull(result);
        assertEquals(result.getNombre(), "Matematicas");
        assertEquals(result.getMateriaId(), 1L);
    }

    @Test
    void eliminaMateria() {
        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materia1));
        materiaService.eliminaMateria(1L);
        Materia result = materiaService.obtenMateria(1L).get();
        assertEquals(0,result.getActivo());
    }

    @Test
    void actualizaOCreaMateria() {
        Materia materia3 = new Materia();
        materia3.setNombre("Historia");
        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materia3));
        materiaService.actualizaOCreaMateria(materia3, 1L);
        Materia result = materiaService.obtenMateria(1L).get();
        assertEquals("Historia",result.getNombre());
    }
}