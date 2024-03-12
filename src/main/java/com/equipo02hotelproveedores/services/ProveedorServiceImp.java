/**
 * ProveedorServiceImp es una clase que implementa la interfaz ProveedorService.
 * Esta clase define los métodos de servicio para la entidad Proveedor.
 * Los métodos incluyen listar todos los proveedores, obtener un proveedor por su id, guardar un proveedor, eliminar un proveedor por su id, listar proveedores por sus ids, obtener un proveedor por su email y verificar si existe un proveedor con un email específico.
 * Esta clase también define un método para eliminar un proveedor y sus recursos asociados.
 *
 * @file: ProveedorServiceImp.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:04
 */
package com.equipo02hotelproveedores.services;

import com.equipo02hotelproveedores.clients.RecursoClienteRest;
import com.equipo02hotelproveedores.models.entity.Proveedor;
import com.equipo02hotelproveedores.repositories.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImp implements ProveedorService{

    @Autowired
    private ProveedorRepository repository;

    @Autowired
    private RecursoClienteRest client;

    /**
     * Este método lista todos los proveedores.
     *
     * @return Una lista de proveedores.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listar() {
        return (List<Proveedor>) repository.findAll();
    }

    /**
     * Este método obtiene un proveedor por su id.
     *
     * @param id El id del proveedor a obtener.
     * @return Un Optional que puede contener el proveedor si se encuentra.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Proveedor> porId(Long id) {
        return repository.findById(id);
    }

    /**
     * Este método guarda un proveedor.
     *
     * @param proveedor El proveedor a guardar.
     * @return El proveedor guardado.
     */
    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    /**
     * Este método elimina un proveedor por su id y también elimina sus recursos asociados.
     *
     * @param id El id del proveedor a eliminar.
     */
    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
        client.eliminarProveedorRecursoPorId(id);
    }

    /**
     * Este método lista proveedores por sus ids.
     *
     * @param ids Los ids de los proveedores a listar.
     * @return Una lista de proveedores.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listarPorIds(Iterable<Long> ids) {
        return (List<Proveedor>) repository.findAllById(ids);
    }

    /**
     * Este método obtiene un proveedor por su email.
     *
     * @param email El email del proveedor a obtener.
     * @return Un Optional que puede contener el proveedor si se encuentra.
     */
    @Override
    public Optional<Proveedor> porEmail(String email) {
        return repository.porEmail(email);
    }

    /**
     * Este método verifica si existe un proveedor con un email específico.
     *
     * @param email El email del proveedor a verificar.
     * @return Un booleano que es verdadero si el proveedor existe y falso si no existe.
     */
    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

}
