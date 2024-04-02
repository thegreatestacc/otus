package org.example.hw_4.service;


import org.example.hw_4.domain.Student;
import org.example.hw_4.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
