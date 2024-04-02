package org.example.hw_4.dao;

import org.example.hw_4.domain.Answer;
import org.example.hw_4.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsvQuestionDaoTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    void whenFindAll_thenReturnAllQuestions() {
        List<Question> allQuestions = questionDao.findAll();
        assertEquals(5, allQuestions.size());
    }

    @Test
    void whenFindAllAnswers_theReturnAllAnswers() {
        List<Answer> allAnswers = questionDao.findAllAnswers();
        assertEquals(5, allAnswers.size());
    }
}