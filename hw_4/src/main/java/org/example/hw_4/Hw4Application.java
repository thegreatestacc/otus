package org.example.hw_4;

import org.example.hw_4.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Hw4Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw4Application.class, args);
    }

}
