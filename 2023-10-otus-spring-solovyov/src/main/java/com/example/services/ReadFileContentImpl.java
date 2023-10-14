package com.example.services;

import com.example.interfaces.ReadFileContent;
import com.example.interfaces.ReadFileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileContentImpl implements ReadFileContent {
    private final ReadFileService readFileService;

    public ReadFileContentImpl(ReadFileServiceCSVImpl readFileService) {
        this.readFileService = readFileService;
    }

    public List<String> readContent() {
        List<String> records = new ArrayList<>();
        File file = readFileService.readFile();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
