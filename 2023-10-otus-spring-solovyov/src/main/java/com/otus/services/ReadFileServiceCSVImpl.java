package com.otus.services;

import com.otus.config.TestFileNameProvider;
import com.otus.exception.QuestionReadException;
import com.otus.utils.UtilConstants;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.io.IOException;

public class ReadFileServiceCSVImpl implements ReadFileService {

    private final TestFileNameProvider testFileNameProvider;

    public ReadFileServiceCSVImpl(TestFileNameProvider testFileNameProvider) {
        this.testFileNameProvider = testFileNameProvider;
    }

    @Override
    public File readFile() {
        var resourceLoader = new DefaultResourceLoader();
        var resource = resourceLoader.getResource(testFileNameProvider.getTestFileName());

        try {
            return resource.getFile();
        } catch (QuestionReadException | IOException e) {
            throw new QuestionReadException(UtilConstants.FILE_DOES_NOT_EXIST, e);
        }
    }
}
