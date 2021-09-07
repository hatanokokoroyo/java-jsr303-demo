package com.jsr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ConfigurationPropertiesScan
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
