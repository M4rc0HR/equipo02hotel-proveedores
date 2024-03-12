/**
 * ProveedorService es una interfaz que define los métodos de servicio para la entidad Proveedor.
 * Esta interfaz define métodos para listar todos los proveedores, obtener un proveedor por su id, guardar un proveedor, eliminar un proveedor por su id, listar proveedores por sus ids, obtener un proveedor por su email y verificar si existe un proveedor con un email específico.
 *
 * @file: ProveedorService.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:05
 */
package com.equipo02hotelproveedores.services;

import com.equipo02hotelproveedores.models.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    /**
     * Este método lista todos los proveedores.
     *
     * @return Una lista de proveedores.
     */
    List<Proveedor> listar();

    /**
     * Este método obtiene un proveedor por su id.
     *
     * @param id El id del proveedor a obtener.
     * @return Un Optional que puede contener el proveedor si se encuentra.
     */
    Optional<Proveedor> porId(Long id);

    /**
     * Este método guarda un proveedor.
     *
     * @param proveedor El proveedor a guardar.
     * @return El proveedor guardado.
     */
    Proveedor guardar(Proveedor proveedor);

    /**
     * Este método elimina un proveedor por su id.
     *
     * @param id El id del proveedor a eliminar.
     */
    void eliminar(Long id);

    /**
     * Este método lista proveedores por sus ids.
     *
     * @param ids Los ids de los proveedores a listar.
     * @return Una lista de proveedores.
     */
    List<Proveedor> listarPorIds(Iterable<Long> ids);

    /**
     * Este método obtiene un proveedor por su email.
     *
     * @param email El email del proveedor a obtener.
     * @return Un Optional que puede contener el proveedor si se encuentra.
     */
    Optional<Proveedor> porEmail(String email);

    /**
     * Este método verifica si existe un proveedor con un email específico.
     *
     * @param email El email del proveedor a verificar.
     * @return Un booleano que es verdadero si el proveedor existe y falso si no existe.
     */
    boolean existePorEmail(String email);
}
