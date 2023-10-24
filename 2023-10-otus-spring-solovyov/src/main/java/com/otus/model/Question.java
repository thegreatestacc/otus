package com.otus.model;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
