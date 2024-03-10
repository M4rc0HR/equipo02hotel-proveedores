
/**
 * @file: ProveedorController.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:02
 */

package com.equipo02hotelproveedores.controllers;

import com.equipo02hotelproveedores.models.entity.Proveedor;
import com.equipo02hotelproveedores.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @GetMapping
    public List<Proveedor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = service.porId(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Proveedor proveedor, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (!proveedor.getEmail().isEmpty() && service.existePorEmail(proveedor.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Proveedor proveedor, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Proveedor> o = service.porId(id);
        if (o.isPresent()) {
            Proveedor proveedorDb = o.get();
            if (!proveedor.getEmail().isEmpty() &&
                    !proveedor.getEmail().equalsIgnoreCase(proveedorDb.getEmail()) &&
                    service.porEmail(proveedor.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
            }

            proveedorDb.setNombre(proveedor.getNombre());
            proveedorDb.setEmail(proveedor.getEmail());
            proveedorDb.setTelefono(proveedor.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(proveedorDb));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Proveedor> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/proveedor-por-recurso")
    public ResponseEntity<?> obtenerProveedorPorRecurso(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listarPorIds(ids));
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
