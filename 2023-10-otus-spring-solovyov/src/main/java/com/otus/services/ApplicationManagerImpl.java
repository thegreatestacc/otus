package com.otus.services;

import com.otus.interfaces.ApplicationManager;
import com.otus.interfaces.QuestionManager;
import com.otus.model.Question;

import java.util.List;

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
