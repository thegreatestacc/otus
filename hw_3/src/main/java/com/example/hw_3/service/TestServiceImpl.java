package com.example.hw_3.service;

import com.example.hw_3.dao.QuestionDao;
import com.example.hw_3.domain.Student;
import com.example.hw_3.domain.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (int i = 0; i < questions.size(); i++) {
            var isAnswerValid = false;
            var question = questions.get(i);
            var correctAnswers = question.answers();
            ioService.printLine(question.text());
            var answer = ioService.readStringWithPrompt("get answer for this question:");

            //todo не понял комментарий в ПРе к этой части кода ("в ответ вводится номер варианта, предлагаю поправить")
            if (answer.equalsIgnoreCase(correctAnswers.get(i).text())) isAnswerValid = true;
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
