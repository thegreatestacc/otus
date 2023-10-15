package com.otus.services;

import com.otus.interfaces.QuestionManager;
import com.otus.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagerImpl implements QuestionManager {

    private final CsvQuestionDao csvQuestionDao;

    public QuestionManagerImpl(CsvQuestionDao csvQuestionDao) {
        this.csvQuestionDao = csvQuestionDao;
    }

    @Override
    public List<Question> getAllQuestion() {
        List<Question> result = new ArrayList<>();
        int recordCounter = 1;
        List<String> strings = csvQuestionDao.readAll();
        for (String s : strings) {
            result.add(new Question(recordCounter++, s));
        }
        return result;
    }
}
