package org.example.hw_13.services;


import org.example.hw_13.dto.genre.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();
}
