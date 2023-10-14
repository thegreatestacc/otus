package com.example;

import com.example.services.ApplicationManagerImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    private static final String PATH_TO_CONTEXT = "spring-context.xml";
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext(PATH_TO_CONTEXT);
        var manager = context.getBean(ApplicationManagerImpl.class);
        manager.runApplication();
    }
}
