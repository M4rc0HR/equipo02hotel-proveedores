package com.equipo02hotelproveedores.services;

import com.equipo02hotelproveedores.clients.RecursoClienteRest;
import com.equipo02hotelproveedores.models.entity.Proveedor;
import com.equipo02hotelproveedores.repositories.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @file: ProveedorServiceImp.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:04
 */

@Service
public class ProveedorServiceImp implements ProveedorService{

    @Autowired
    private ProveedorRepository repository;

    @Autowired
    private RecursoClienteRest client;

    @Override

    @Transactional(readOnly = true)
    public List<Proveedor> listar() {
        return (List<Proveedor>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Proveedor> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
        client.eliminarProveedorRecursoPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listarPorIds(Iterable<Long> ids) {
        return (List<Proveedor>) repository.findAllById(ids);
    }

    @Override
    public Optional<Proveedor> porEmail(String email) {
        return repository.porEmail(email);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

}
