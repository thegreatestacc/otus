package com.example.hw_3.dao;

import com.example.hw_3.config.TestFileNameProvider;
import com.example.hw_3.domain.Question;
import com.example.hw_3.exception.QuestionReadException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        List<String> records = new ArrayList<>();
        File file = readFile(fileNameProvider.getTestFileName());
        CSVParser parser = getCsvParser();
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

    private static File readFile(String fileName) {
        try {
            Resource resource = new ClassPathResource(fileName);
            return resource.getFile();
        } catch (Exception e) {
            throw new QuestionReadException("Can not upload file", e);
        }
    }

    private static CSVParser getCsvParser() {
        return new CSVParserBuilder()
                .withSeparator('\n')
                .build();
    }
}
