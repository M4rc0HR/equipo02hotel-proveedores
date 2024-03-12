/**
 * ProveedorController es una clase que define los endpoints de la API para interactuar con los proveedores.
 * Esta clase define métodos para listar, obtener detalles, crear, editar y eliminar proveedores.
 * También incluye un método para obtener proveedores por recursos.
 *
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

    /**
     * Este método define un endpoint GET para listar todos los proveedores.
     *
     * @return Una lista de proveedores.
     */
    @GetMapping
    public List<Proveedor> listar() {
        return service.listar();
    }

    /**
     * Este método define un endpoint GET para obtener los detalles de un proveedor por su id.
     *
     * @param id El id del proveedor.
     * @return Una respuesta con los detalles del proveedor si se encuentra, o un error si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = service.porId(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Este método define un endpoint POST para crear un nuevo proveedor.
     *
     * @param proveedor El proveedor a crear.
     * @param result El resultado de la validación del proveedor.
     * @return Una respuesta con el proveedor creado si la validación es exitosa, o un error si la validación falla.
     */
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

    /**
     * Este método define un endpoint PUT para editar un proveedor existente.
     *
     * @param proveedor El proveedor con los datos actualizados.
     * @param result El resultado de la validación del proveedor.
     * @param id El id del proveedor a editar.
     * @return Una respuesta con el proveedor editado si la validación es exitosa y el proveedor existe, o un error si la validación falla o el proveedor no existe.
     */
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

    /**
     * Este método define un endpoint DELETE para eliminar un proveedor existente.
     *
     * @param id El id del proveedor a eliminar.
     * @return Una respuesta vacía si el proveedor se elimina con éxito, o un error si el proveedor no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Proveedor> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Este método define un endpoint GET para obtener proveedores por recursos.
     *
     * @param ids Los ids de los recursos.
     * @return Una respuesta con los proveedores que corresponden a los recursos.
     */
    @GetMapping("/proveedor-por-recurso")
    public ResponseEntity<?> obtenerProveedorPorRecurso(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listarPorIds(ids));
    }

    /**
     * Este método valida los errores de un proveedor.
     *
     * @param result El resultado de la validación del proveedor.
     * @return Una respuesta con los errores si hay errores, o null si no hay errores.
     */
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
