package com.example.hw_2.services;

import com.example.hw_2.dao.QuestionDao;
import com.example.hw_2.domain.Answer;
import com.example.hw_2.domain.Student;
import com.example.hw_2.domain.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        List<Answer> correctAnswers = questionDao.findAllAnswers();
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
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
