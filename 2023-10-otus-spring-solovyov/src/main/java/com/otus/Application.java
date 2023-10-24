package com.otus;

import com.otus.utils.UtilConstants;
import com.otus.services.ApplicationManagerImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext(UtilConstants.PATH_TO_CONTEXT);
        var manager = context.getBean(ApplicationManagerImpl.class);
        manager.runApplication();
    }
}
