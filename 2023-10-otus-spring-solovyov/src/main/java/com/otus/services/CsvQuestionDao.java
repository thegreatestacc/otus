package com.otus.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.otus.interfaces.QuestionDao;
import com.otus.interfaces.ReadFileService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final ReadFileService readFileService;

    public CsvQuestionDao(ReadFileServiceCSVImpl readFileService) {
        this.readFileService = readFileService;
    }

    public List<String> readAll() {
        List<String> records = new ArrayList<>();
        File file = readFileService.readFile();
        CSVParser parser = new CSVParserBuilder()
                .withSeparator('\n')
                .build();

        try (Reader reader = new BufferedReader(new FileReader(file));
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withCSVParser(parser).build()) {

            csvReader.readAll().forEach(arr -> records.addAll(Arrays.asList(arr)));
        } catch (IOException | CsvException e) {
            throw new RuntimeException();
        }
        return records;
    }
}