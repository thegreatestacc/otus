package com.example.hw_7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.hw_7.models")
public class Hw7Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw7Application.class, args);
    }

}
