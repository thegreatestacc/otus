package com.example.hw_2.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class AppProperties implements TestConfig, TestFileNameProvider {

    // внедрить свойство из application.properties
    @Value("${test.rightAnswersCountToPass}")
    private String rightAnswersCountToPass;

    // внедрить свойство из application.properties
    @Value("${test.fileName}")
    private String testFileName;

    @Override
    public int getRightAnswersCountToPass() {
        return Integer.parseInt(rightAnswersCountToPass);
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
