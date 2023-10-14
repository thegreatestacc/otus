package com.example.services;

import com.example.interfaces.ApplicationManager;
import com.example.interfaces.QuestionManager;
import com.example.model.Question;

import java.util.List;
import java.util.Map;

public class ApplicationManagerImpl implements ApplicationManager {
    private final QuestionManager questionManager;

    public ApplicationManagerImpl(QuestionManager questionManager) {
        this.questionManager = questionManager;
    }

    @Override
    public void runApplication() {
        List<Question> questions = questionManager.getAllQuestion();
        questionManager.showAllQuestions(questions);
    }
}
