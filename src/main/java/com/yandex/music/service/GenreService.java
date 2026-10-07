package com.yandex.music.service;

import com.yandex.music.dto.GenreDto;
import com.yandex.music.model.Genre;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenre();
    GenreDto getGenreById(Long id);
    GenreDto addGenre(GenreDto genreDto);
    GenreDto updateGenre(Long id, GenreDto genreDto);
    boolean deleteGenre(Long id);
}
