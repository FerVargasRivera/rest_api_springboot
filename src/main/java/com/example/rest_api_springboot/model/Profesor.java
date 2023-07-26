package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "Profesor")
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProfesorId;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "El nombre del profesor es requerido")
    @Size(min = 4, max = 255, message = "El nombre del profesor debe tener al menos 5 caracteres y ser menor a 255")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido paterno del profesor es requerido")
    @Size(min = 4, max = 255, message = "El apellido paterno del profesor debe tener al menos 5 caracteres y ser menor a 50")
    private String apellidoP;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido materno del profesor es requerido")
    @Size(min = 4, max = 255, message = "El apellido materno del profesor debe tener al menos 5 caracteres y ser menor a 50")
    private String apellidoM;

    @Column(nullable = false)
    @NotNull(message = "La edad del profesor es requerida")
    @Min(value = 25, message = "El profesor debe tener al menos 25 años")
    @Max(value = 60, message = "El profesor no puede tener mas de 60 años")
    private int edad;

    @Email(message = "Debe ingresar una direccion de correo valida")
    private String correoContacto;

    @Column(nullable = false)
    @NotNull(message = "El estatus del profesor es requerido")
    private int activo = 1;

    @OneToOne
    @JoinColumn(name = "GrupoId", referencedColumnName = "GrupoId")
    private Grupo grupoId;
}
