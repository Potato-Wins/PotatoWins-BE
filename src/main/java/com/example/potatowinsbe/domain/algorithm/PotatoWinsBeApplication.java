package com.example.potatowinsbe.domain.algorithm;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ConfigurationProperties("elasticsearch")
@Getter
@SpringBootApplication
public class PotatoWinsBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PotatoWinsBeApplication.class, args);
        System.out.println("Application started successfully!");
    }
}
