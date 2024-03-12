/**
 * RecursoClienteRest es una interfaz que define un cliente Feign para interactuar con el servicio de recursos.
 * Esta interfaz define un método para eliminar un proveedor de recursos por su id.
 *
 * @file: RecursoClienteRest.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:02
 */

package com.equipo02hotelproveedores.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Esta anotación define el nombre y la URL del servicio con el que este cliente Feign interactuará.
 */
@FeignClient(name="equipo02Hotel", url = "localhost:8001/api/recursos")
public interface RecursoClienteRest {
    /**
     * Este método define una operación DELETE para eliminar un proveedor de recursos por su id.
     *
     * @param id El id del proveedor de recursos a eliminar.
     */
    @DeleteMapping("/eliminar-proveedor-recurso/{id}")
    void eliminarProveedorRecursoPorId(@PathVariable Long id);

}
