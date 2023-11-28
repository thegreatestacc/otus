package com.example.hw_3;

import com.example.hw_3.service.TestRunnerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Hw3Application {

	public static void main(String[] args) {

		//Создать контекст Spring Boot приложения
		ApplicationContext context = new AnnotationConfigApplicationContext(Hw3Application.class);
		var testRunnerService = context.getBean(TestRunnerService.class);
		testRunnerService.run();

	}

}
