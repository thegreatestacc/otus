package org.example.hw_12.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hw_12.dto.genre.GenreDto;
import org.example.hw_12.services.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.findAll();
    }
}
