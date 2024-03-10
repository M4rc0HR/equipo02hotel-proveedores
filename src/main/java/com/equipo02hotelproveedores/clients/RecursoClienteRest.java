/**
 * @file: RecursoClienteRest.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:02
 */
package com.equipo02hotelproveedores.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="equipo02Hotel", url = "localhost:8001/api/recursos")
public interface RecursoClienteRest {

    @DeleteMapping("/eliminar-proveedor-recurso/{id}")
    void eliminarProveedorRecursoPorId(@PathVariable Long id);

}
