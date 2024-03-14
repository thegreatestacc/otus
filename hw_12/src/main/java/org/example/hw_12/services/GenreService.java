package org.example.hw_12.services;


import org.example.hw_12.dto.genre.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();
}
