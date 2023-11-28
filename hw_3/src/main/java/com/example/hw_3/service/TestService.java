package com.example.hw_3.service;

import com.example.hw_3.domain.Student;
import com.example.hw_3.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
