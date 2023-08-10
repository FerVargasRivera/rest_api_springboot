package com.example.rest_api_springboot.services;

import com.example.rest_api_springboot.model.Grupo;
import com.example.rest_api_springboot.persistence.GrupoRepository;
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
public class GrupoServiceTest {

    @InjectMocks
    private GrupoService grupoService;
    @Mock
    private GrupoRepository grupoRepository;
    private Grupo grupo1, grupo2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        grupo1 = new Grupo(1L,"IT-001",1,null);
        grupo2 = new Grupo(2L,"IT-002",1,null);
    }

    @Test
    void obtenGrupos() {
        List<Grupo> grupoList = new ArrayList<>();

        grupoList.add(grupo1);
        grupoList.add(grupo2);
        when(grupoRepository.findAll()).thenReturn(grupoList);
        List<Grupo> result = grupoService.obtenGrupos();

        assertEquals(2, result.size());
        assertEquals("IT-001", result.get(0).getNombre());
        assertEquals("IT-002", result.get(1).getNombre());
    }

    @Test
    void obtenGrupo() {
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo1));
        Grupo result = grupoService.obtenGrupo(1L).get();
        assertEquals("IT-001", result.getNombre());
    }

    @Test
    void guardaGrupo() {
        when(grupoRepository.save(any(Grupo.class))).thenReturn(grupo1);
        Grupo result = grupoService.guardaGrupo(grupo1);

        assertNotNull(result);
        assertEquals(result.getNombre(), "IT-001");
        assertEquals(result.getGrupoId(), 1L);
    }

    @Test
    void eliminaGrupo() {
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo1));
        grupoService.eliminaGrupo(1L);
        Grupo result = grupoService.obtenGrupo(1L).get();
        assertEquals(0,result.getActivo());
    }

    @Test
    void actualizaOCreaGrupo() {
        Grupo grupo3 = new Grupo();
        grupo3.setNombre("IT-003");
        when(grupoRepository.findById(1L)).thenReturn(Optional.of(grupo3));
        grupoService.actualizaOCreaGrupo(grupo3, 1L);
        Grupo result = grupoService.obtenGrupo(1L).get();
        assertEquals("IT-003",result.getNombre());
    }
}