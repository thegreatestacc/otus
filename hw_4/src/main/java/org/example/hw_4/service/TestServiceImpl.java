package org.example.hw_4.service;

import lombok.RequiredArgsConstructor;
import org.example.hw_4.dao.QuestionDao;
import org.example.hw_4.domain.Student;
import org.example.hw_4.domain.TestResult;
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
