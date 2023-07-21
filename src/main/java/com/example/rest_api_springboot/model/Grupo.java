package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Grupo")
@NoArgsConstructor
@AllArgsConstructor

public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GrupoId;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "grupo")
    private List<Estudiante> estudiantes;

    @OneToOne(mappedBy = "grupo")
    private Profesor profesor;

}
