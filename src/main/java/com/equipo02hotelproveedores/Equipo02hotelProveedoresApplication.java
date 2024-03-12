/**
 * Equipo02hotelProveedoresApplication es la clase principal que inicia la aplicación Spring Boot.
 * Esta clase está anotada con @SpringBootApplication, lo que indica que es una aplicación Spring Boot.
 * También está anotada con @EnableFeignClients, lo que habilita el uso de clientes Feign para la comunicación entre microservicios.
 *
 * @file: ProveedorServiceImp.java
 * @author: (c)2024 MARCO
 * @created: 9/3/2024 14:04
 */

package com.equipo02hotelproveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Equipo02hotelProveedoresApplication {

    /**
     * Este es el método principal que inicia la aplicación Spring Boot.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(Equipo02hotelProveedoresApplication.class, args);
    }

}
