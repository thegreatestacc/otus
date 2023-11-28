package com.example.hw_3.service;

import com.example.hw_3.config.LocaleConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private final LocaleConfig localeConfig;

    // Доделать
    @Override
    public String getMessage(String code, Object... args) {
        return null;
    }
}
