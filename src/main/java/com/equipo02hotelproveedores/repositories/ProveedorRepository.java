/**
 * ProveedorRepository es una interfaz que extiende CrudRepository para proporcionar operaciones de base de datos para la entidad Proveedor.
 * Esta interfaz define métodos para buscar un proveedor por email, verificar si existe un proveedor con un email específico y una consulta personalizada para buscar un proveedor por email.
 *
 * @file: ProveedorRepository.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:04
 */

package com.equipo02hotelproveedores.repositories;

import com.equipo02hotelproveedores.models.entity.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {
    /**
     * Este método busca un proveedor por su email.
     *
     * @param email El email del proveedor a buscar.
     * @return Un Optional que puede contener el proveedor si se encuentra.
     */
    Optional<Proveedor> findByEmail(String email);

    /**
     * Este método busca un proveedor por su email.
     *
     * @param email El email del proveedor a buscar.
     * @return Un Optional que puede contener el proveedor si se encuentra.
     */
    @Query("select u from Proveedor u where u.email=?1")
    Optional<Proveedor> porEmail(String email);

    /**
     * Este método verifica si existe un proveedor con un email específico.
     *
     * @param email El email del proveedor a buscar.
     * @return Un booleano que indica si existe un proveedor con el email especificado.
     */
    boolean existsByEmail(String email);

}
