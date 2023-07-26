package com.example.rest_api_springboot.persistence;

import com.example.rest_api_springboot.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    //Creamos el filtro para consultar los inactivos en nuestro servicio
    List<Estudiante> findByActivo(int activo);
}
