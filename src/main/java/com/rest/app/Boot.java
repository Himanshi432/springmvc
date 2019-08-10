package com.rest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {
        "com.rest.manager"
        ,"com.rest.clients"
        , "com.rest.controller"
        , "com.mongodb"
        ,"com.rest.config"
        ,"com.rest.aspects"})
@EnableSwagger2
public class Boot {
    public static void main(String args[]) {
        SpringApplication.run(Boot.class, args);
    }
}
