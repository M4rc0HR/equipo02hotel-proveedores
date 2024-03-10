package com.equipo02hotelproveedores.services;

import com.equipo02hotelproveedores.models.entity.Proveedor;

import java.util.List;
import java.util.Optional;

/**
 * @file: ProveedorService.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:05
 */
public interface ProveedorService {
    List<Proveedor> listar();
    Optional<Proveedor> porId(Long id);
    Proveedor guardar(Proveedor proveedor);
    void eliminar(Long id);
    List<Proveedor> listarPorIds(Iterable<Long> ids);
    Optional<Proveedor> porEmail(String email);
    boolean existePorEmail(String email);
}
