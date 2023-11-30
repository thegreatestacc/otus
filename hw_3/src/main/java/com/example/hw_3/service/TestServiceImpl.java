package com.example.hw_3.service;

import com.example.hw_3.dao.QuestionDao;
import com.example.hw_3.domain.Answer;
import com.example.hw_3.domain.Student;
import com.example.hw_3.domain.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        List<Answer> correctAnswers = questionDao.findAllAnswers();
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (int i = 0; i < questions.size(); i++) {
            var isAnswerValid = false; // Задать вопрос, получить ответ
            ioService.printLine(questions.get(i).text());
            var answer = ioService.readStringWithPrompt("get answer for this question:");
            if (answer.equalsIgnoreCase(correctAnswers.get(i).text())) isAnswerValid = true;
            testResult.applyAnswer(questions.get(i), isAnswerValid);
        }

        return testResult;
    }

}
