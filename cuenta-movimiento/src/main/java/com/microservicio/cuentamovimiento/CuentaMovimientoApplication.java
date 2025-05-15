package com.microservicio.cuentamovimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.microservicio.cuentamovimiento")
public class CuentaMovimientoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuentaMovimientoApplication.class, args);
    }

}
