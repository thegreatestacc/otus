package com.example.hw_2.dao;

import com.example.hw_2.domain.Answer;
import com.example.hw_2.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();

    List<Answer> findAllAnswers();
}
