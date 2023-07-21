package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Estudiante")
@NoArgsConstructor
@AllArgsConstructor

public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EstudianteId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidoP;

    @Column(nullable = false)
    private String apellidoM;

    @Column(nullable = false)
    private Integer edad;

    @ManyToOne
    private Grupo grupo;

    @JoinTable(
            name = "rel_estudiantes_materias",
            joinColumns = @JoinColumn(name = "FK_ESTUDIANTE", nullable = false),
            inverseJoinColumns = @JoinColumn(name="FK_MATERIA", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Materia> materia;
}
