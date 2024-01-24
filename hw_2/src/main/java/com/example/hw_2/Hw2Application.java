package com.example.hw_2;


import com.example.hw_2.services.TestRunnerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Hw2Application {
    public static void main(String[] args) {
        //Создать контекст на основе Annotation/Java конфигурирования
        var context = new AnnotationConfigApplicationContext(Hw2Application.class);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
    }

}
