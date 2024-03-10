package com.equipo02hotelproveedores.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @file: Proveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:03
 */

@Data
@Entity
@Table(name="proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @NotBlank
    private String nombre;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @NotBlank
    private String telefono;


}
