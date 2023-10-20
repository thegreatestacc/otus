package com.otus.config;

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
