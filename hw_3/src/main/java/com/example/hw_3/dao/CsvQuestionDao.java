package com.example.hw_3.dao;

import com.example.hw_3.config.TestFileNameProvider;
import com.example.hw_3.domain.Answer;
import com.example.hw_3.domain.Question;
import com.example.hw_3.exception.QuestionReadException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
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
        InputStream resourceAsStream = readResource(fileNameProvider.getTestFileName());
        CSVParser parser = getCsvParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
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

    @Override
    public List<Answer> findAllAnswers() {
        List<String> records = new ArrayList<>();
        InputStream resourceAsStream = readResource(fileNameProvider.getAnswersFileName());
        CSVParser parser = getCsvParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withCSVParser(parser).build()) {

            csvReader.readAll().forEach(arr -> records.addAll(Arrays.asList(arr)));
        } catch (QuestionReadException | IOException | CsvException e) {
            throw new QuestionReadException(e.getMessage(), e);
        }
        return new ArrayList<>(records.stream()
                .map(record -> new Answer(record, true)).toList());
    }

    private static InputStream readResource(String fileName) {
        var classLoader = CsvQuestionDao.class.getClassLoader();
        var resource = classLoader.getResourceAsStream(fileName);
        if (resource == null) {
            throw new QuestionReadException("Can not upload file. Resource is null!");
        } else {
            try {
                return new BufferedInputStream(resource);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static CSVParser getCsvParser() {
        return new CSVParserBuilder()
                .withSeparator('\n')
                .build();
    }
}
