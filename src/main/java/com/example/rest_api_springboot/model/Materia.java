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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Materia")

public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MateriaId;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "El nombre de la materia es requerido")
    @Size(min = 4, max = 255, message = "El nombre de la materia debe tener al menos 5 caracteres y ser menor a 255")
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = "El estatus de la materia es requerido")
    private int activo = 1;
}
