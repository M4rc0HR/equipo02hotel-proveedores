package com.equipo02hotelproveedores.repositories;

import com.equipo02hotelproveedores.models.entity.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @file: ProveedorRepository.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:04
 */
public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {

    Optional<Proveedor> findByEmail(String email);

    @Query("select u from Proveedor u where u.email=?1")
    Optional<Proveedor> porEmail(String email);

    boolean existsByEmail(String email);

}
