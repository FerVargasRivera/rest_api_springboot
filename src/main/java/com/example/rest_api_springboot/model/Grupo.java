package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "Grupo")
@NoArgsConstructor
@AllArgsConstructor

public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GrupoId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre del grupo es requerido")
    @Size(min = 4, max = 100, message = "El nombre del grupo debe tener al menos 5 caracteres y ser menor a 100")
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = "El estatus del grupo es requerido")
    private int activo = 1;

    @ManyToOne
    @JoinColumn(name = "MateriaId", referencedColumnName = "MateriaId")
    private Materia materiaId;

}
