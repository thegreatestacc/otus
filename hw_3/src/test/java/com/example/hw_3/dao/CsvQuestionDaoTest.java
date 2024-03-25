package com.example.hw_3.dao;

import com.example.hw_3.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CsvQuestionDaoTest {

    @Autowired
    CsvQuestionDao csvQuestionDao;

    @Test
    void findAll() {
        List<Question> all = csvQuestionDao.findAll();
        assertEquals(5, all.size());
    }
}