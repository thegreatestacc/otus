package com.example.hw_2.services;

import com.example.hw_2.domain.Student;
import com.example.hw_2.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
