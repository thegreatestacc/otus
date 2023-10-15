package com.otus.interfaces;

import com.otus.model.Question;

import java.util.List;

public interface IOService {
    void showAllQuestions(List<Question> questions);
}
