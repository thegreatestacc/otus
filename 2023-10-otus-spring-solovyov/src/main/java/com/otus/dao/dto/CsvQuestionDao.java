package com.otus.dao.dto;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.otus.exception.QuestionReadException;
import com.otus.services.ReadFileService;
import com.otus.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvQuestionDao implements QuestionDao {
    private final ReadFileService readFileService;

    public CsvQuestionDao(ReadFileService readFileService) {
        this.readFileService = readFileService;
    }

    @Override
    public List<Question> findAll() {
        List<String> records = new ArrayList<>();
        File file = readFileService.readFile();
        CSVParser parser = new CSVParserBuilder()
                .withSeparator('\n')
                .build();

        try (Reader reader = new BufferedReader(new FileReader(file));
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withCSVParser(parser).build()) {

            csvReader.readAll().forEach(arr -> records.addAll(Arrays.asList(arr)));

        } catch (QuestionReadException | IOException | CsvException e) {
            throw new QuestionReadException(e.getMessage(), e);
        }

        return new ArrayList<>(records.stream()
                .map(record -> new Question(record, List.of())).toList()
        );
    }
}
