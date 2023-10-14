package com.example.services;

import com.example.interfaces.IOService;
import com.example.interfaces.ReadFileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileContent implements IOService {
    private final ReadFileService readFileService;
//    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

//    Resource resource = resourceLoader.getResource("classpath:questions.csv");


    public ReadFileContent(ReadCSVFile readFileService) {
        this.readFileService = readFileService;
    }

    public List<String> readCsvFile() {
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

    public void showRecords(List<String> records) {
        records.forEach(System.out::println);
    }
}
