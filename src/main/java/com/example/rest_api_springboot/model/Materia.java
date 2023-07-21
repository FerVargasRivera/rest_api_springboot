package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Materia")

public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MateriaId;

    @Column(nullable = false)
    @NotBlank(message = "El nombre de la materia no puede estar en blanco")
    private String nombre;

//    @ManyToMany(mappedBy = "materia")
//    private List<Estudiante> estudiantes;
}
