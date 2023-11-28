package com.example.hw_3.dao;

import com.example.hw_3.domain.Question;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsvQuestionDaoTest {

    @Mock
    CsvQuestionDao csvQuestionDao;

    @Test
    void findAll() {
        List<Question> all = csvQuestionDao.findAll();
        assertEquals(5, all.size());
    }
}