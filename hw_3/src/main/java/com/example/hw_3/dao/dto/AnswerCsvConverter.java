package com.example.hw_3.dao.dto;

import com.example.hw_3.domain.Answer;
import com.opencsv.bean.AbstractCsvConverter;

public class AnswerCsvConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        var valueArr = value.split("%");
        return new Answer(valueArr[0], Boolean.parseBoolean(valueArr[1]));
    }
}
