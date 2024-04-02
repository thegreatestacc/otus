package org.example.hw_4.dao;


import org.example.hw_4.domain.Answer;
import org.example.hw_4.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();

    List<Answer> findAllAnswers();
}
