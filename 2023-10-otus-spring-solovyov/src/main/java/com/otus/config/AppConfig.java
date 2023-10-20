package com.otus.config;

import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig implements TestFileNameProvider {

    private String testFileName;

    public void setTestFileName(String testFileName) {
        this.testFileName = testFileName;
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
