package com.xcelerateit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {
        "com.xcelerateit.startup",
        "com.xcelerateit.repository",
        "com.xcelerateit.domain",
        "com.xcelerateit.service",
        "com.xcelerateit.endpoints.rest"  // ✅ ensures Swagger sees your controllers
         })
@EnableJpaRepositories(basePackages = "com.xcelerateit.repository")
@EntityScan(basePackages = "com.xcelerateit.domain")
@EnableConfigurationProperties
@EnableJpaAuditing
@ComponentScan(basePackages = "com.xcelerateit")


public class JobBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobBoardApplication.class, args);
    }
}