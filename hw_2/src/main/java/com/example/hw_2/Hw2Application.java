package com.example.hw_2;


import com.example.hw_2.services.TestRunnerService;
import org.springframework.context.ApplicationContext;

public class Hw2Application {

	public static void main(String[] args) {

		//Создать контекст на основе Annotation/Java конфигурирования
		ApplicationContext context = null;
		var testRunnerService = context.getBean(TestRunnerService.class);
		testRunnerService.run();

	}

}
