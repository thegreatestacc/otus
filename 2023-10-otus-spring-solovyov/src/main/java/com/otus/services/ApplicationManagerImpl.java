package com.otus.services;

import com.otus.interfaces.ApplicationManager;
import com.otus.interfaces.IOService;
import com.otus.interfaces.QuestionManager;
import com.otus.model.Question;

import java.util.List;

public class ApplicationManagerImpl implements ApplicationManager {
    private final QuestionManager questionManager;

    private final IOService ioService;

    public ApplicationManagerImpl(QuestionManager questionManager, IOService ioService) {
        this.questionManager = questionManager;
        this.ioService = ioService;
    }

    @Override
    public void runApplication() {
        List<Question> questions = questionManager.getAllQuestion();
        ioService.showAllQuestions(questions);
    }
}
