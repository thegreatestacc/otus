package com.otus.services;

import com.otus.dao.dto.CsvQuestionDao;
import com.otus.model.Question;

import java.util.List;

public class ApplicationManagerImpl implements ApplicationManager {

    private final CsvQuestionDao csvQuestionDao;

    private final IOService ioService;

    public ApplicationManagerImpl(CsvQuestionDao csvQuestionDao, IOService ioService) {
        this.csvQuestionDao = csvQuestionDao;
        this.ioService = ioService;
    }

    @Override
    public void runApplication() {
        List<Question> questions = csvQuestionDao.findAll();
        questions.forEach(question -> ioService.printLine(question.text()));
    }
}
