package com.rest.app;

import com.rest.controller.HealthController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = HealthController.class)
public class Boot {
    public static void main(String args[]) {
        SpringApplication.run(Boot.class, args);
    }
}