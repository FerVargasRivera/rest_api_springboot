package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Profesor")
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProfesorId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidoP;

    @Column(nullable = false)
    private String apellidoM;

    @Column(nullable = false)
    private Integer edad;

    @OneToOne
    @JoinColumn(name = "GrupoId", referencedColumnName = "GrupoId")
    private Grupo grupo;
}
