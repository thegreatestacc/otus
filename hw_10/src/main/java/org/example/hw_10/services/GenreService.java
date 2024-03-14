package org.example.hw_10.services;


import org.example.hw_10.dto.genre.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();
}
