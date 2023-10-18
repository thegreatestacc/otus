package com.otus.services;

import com.otus.utils.UtilConstants;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

public class ReadFileServiceCSVImpl implements ReadFileService {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final Resource resource = resourceLoader.getResource(UtilConstants.PATH_TO_FILE);

    @Override
    public File readFile() {
        try {
            return resource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
