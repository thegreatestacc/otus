package org.example.hw_4.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
