package com.otus.interfaces;

import com.otus.model.Question;

import java.util.List;

public interface QuestionManager {
    List<Question> getAllQuestion();

    void showAllQuestions(List<Question> questions);
}
