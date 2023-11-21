package com.example.hw_2.dao;

import com.example.hw_2.config.TestFileNameProvider;
import com.example.hw_2.domain.Question;
import com.example.hw_2.exception.QuestionReadException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    @SneakyThrows
    public List<Question> findAll() {
        // Использовать CsvToBean
        // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
        // Использовать QuestionReadException
        // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/
        List<String> records = new ArrayList<>();
        Resource resource = new ClassPathResource(fileNameProvider.getTestFileName());
        File file = resource.getFile();
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
