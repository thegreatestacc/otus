package com.example.hw_3.dao;

import com.example.hw_3.domain.Answer;
import com.example.hw_3.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();

    List<Answer> findAllAnswers();
}
