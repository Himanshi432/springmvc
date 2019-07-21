package com.rest.app;

import com.rest.config.Swagger2Config;
import com.rest.controller.HealthController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackageClasses = HealthController.class)
@ComponentScan(basePackageClasses = Swagger2Config.class)
@EnableSwagger2
public class Boot {
    public static void main(String args[]) {
        SpringApplication.run(Boot.class, args);
    }
}
