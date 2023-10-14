package com.example.interfaces;

import com.example.model.Question;

import java.util.List;

public interface QuestionManager {
    List<Question> getAllQuestion();

    void showAllQuestions(List<Question> questions);
}
