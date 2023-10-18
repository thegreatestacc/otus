package com.otus.dao.dto;

import com.otus.model.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
