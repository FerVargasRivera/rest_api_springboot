package com.example.rest_api_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@Table(name = "Estudiante")
@NoArgsConstructor
@AllArgsConstructor

//Definimos la estructura de la tabla con su respectivas validaciones
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EstudianteId;

    //Indicamos que es una columna en nuestra tabla
    @Column(nullable = false, length = 255)
    //Validaciones con JSR 380
    @NotBlank(message = "El nombre del estudiante es requerido")
    @Size(min = 4, max = 255, message = "El nombre del estudiante debe tener al menos 5 caracteres y ser menor a 255")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido paterno del estudiante es requerido")
    @Size(min = 4, max = 255, message = "El apellido paterno del estudiante debe tener al menos 5 caracteres y ser menor a 50")
    private String apellidoP;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El apellido materno del estudiante es requerido")
    @Size(min = 4, max = 255, message = "El apellido materno del estudiante debe tener al menos 5 caracteres y ser menor a 50")
    private String apellidoM;

    @Column(nullable = false)
    @NotNull(message = "La edad del estudiante es requerida")
    @Min(value = 15, message = "El estudiante debe tener al menos 15 años")
    @Max(value = 30, message = "El estudiante no puede tener mas de 30 años")
    private int edad;

    @Email(message = "Debe ingresar una direccion de correo valida")
    private String correoContacto;

    @Column(nullable = false)
    @NotNull(message = "El estatus del estudiante es requerido")
    private int activo = 1;

    //Relacion muchos a uno en este caso un solo grupo
    @ManyToOne
    @JoinColumn(name = "GrupoId", referencedColumnName = "GrupoId")
    private Grupo grupoId;
}
