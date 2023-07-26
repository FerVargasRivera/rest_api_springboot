package com.example.rest_api_springboot.persistence;

import com.example.rest_api_springboot.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    List<Grupo> findByActivo(int activo);
}
