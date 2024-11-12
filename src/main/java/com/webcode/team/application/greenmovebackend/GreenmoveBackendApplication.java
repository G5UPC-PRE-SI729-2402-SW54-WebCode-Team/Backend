package com.webcode.team.application.greenmovebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GreenmoveBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenmoveBackendApplication.class, args);
    }

}
