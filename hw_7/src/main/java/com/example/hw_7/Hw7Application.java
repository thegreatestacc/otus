package com.example.hw_7;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.SQLException;

@SpringBootApplication
@EntityScan("com.example.hw_7.models")
public class Hw7Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw7Application.class, args);

        try {
            Console.main(args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
