package com.otus.services;

import com.otus.interfaces.IOService;
import com.otus.model.Question;

import java.util.List;

public class IOServiceImpl implements IOService {
    @Override
    public void showAllQuestions(List<Question> questions) {
        questions.forEach(System.out::println);
    }
}
