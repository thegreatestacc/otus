package com.example.hw_2.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
