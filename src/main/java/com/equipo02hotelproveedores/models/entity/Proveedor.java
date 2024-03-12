/**
 * Proveedor es una clase que representa la entidad Proveedor en la base de datos.
 * Esta clase define los atributos idProveedor, nombre, email y telefono que corresponden a las columnas en la tabla de proveedores.
 * Los atributos están anotados con validaciones para garantizar que los datos sean correctos antes de ser persistidos en la base de datos.
 *
 * @file: Proveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:03
 */

package com.equipo02hotelproveedores.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name="proveedores")
public class Proveedor {
    /**
     * Este atributo representa el id del proveedor. Es la clave primaria en la tabla de proveedores.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    /**
     * Este atributo representa el nombre del proveedor. No puede estar en blanco.
     */
    @NotBlank
    private String nombre;

    /**
     * Este atributo representa el email del proveedor. Debe ser un email válido y no puede estar vacío.
     * Es único en la tabla de proveedores.
     */
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    /**
     * Este atributo representa el teléfono del proveedor. No puede estar en blanco.
     * Es único en la tabla de proveedores.
     */
    @Column(unique = true)
    @NotBlank
    private String telefono;


}
