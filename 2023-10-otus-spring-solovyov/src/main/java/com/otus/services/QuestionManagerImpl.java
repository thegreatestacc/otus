package com.otus.services;

import com.otus.interfaces.QuestionManager;
import com.otus.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagerImpl implements QuestionManager {

    private final ReadFileContentImpl readFileContentImpl;

    public QuestionManagerImpl(ReadFileContentImpl readFileContentImpl) {
        this.readFileContentImpl = readFileContentImpl;
    }

    @Override
    public List<Question> getAllQuestion() {
        List<Question> result = new ArrayList<>();
        int recordCounter = 1;
        List<String> strings = readFileContentImpl.readContent();
        for (String s : strings) {
            result.add(new Question(recordCounter++, s));
        }
        return result;
    }

    @Override
    public void showAllQuestions(List<Question> questions) {
        questions.forEach(System.out::println);
    }
}
