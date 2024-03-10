package com.equipo02hotelproveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Equipo02hotelProveedoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(Equipo02hotelProveedoresApplication.class, args);
    }

}
