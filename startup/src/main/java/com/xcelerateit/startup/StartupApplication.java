package com.xcelerateit.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.xcelerateit")
public class StartupApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
    }
}