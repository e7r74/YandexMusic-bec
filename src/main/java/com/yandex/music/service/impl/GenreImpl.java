package com.yandex.music.service.impl;

import com.yandex.music.dto.GenreDto;
import com.yandex.music.mappers.GenreMapper;
import com.yandex.music.model.Genre;
import com.yandex.music.repository.GenreRepository;
import com.yandex.music.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GenreImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;


    @Override
    public List<GenreDto> getAllGenre() {
        List<Genre> genres= genreRepository.findAll();
        return genreMapper.genresDto(genres);
    }

    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre=genreRepository.findById(id).orElseThrow(()->new RuntimeException("Genre not found with id: "+id));
        return genreMapper.toDto(genre);
    }

    @Override
    public GenreDto addGenre(GenreDto genreDto) {
        Genre genre= genreMapper.toEntity(genreDto);
        genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        Genre genre=genreRepository.findById(id).orElse(null);
        if (Objects.isNull(genre)){
            return null;
        }else {
            genre.setName(genre.getName());
            return genreMapper.toDto(genreRepository.save(genre));
        }
    }

    @Override
    public boolean deleteGenre(Long id) {
        Genre genre=genreRepository.findById(id).orElse(null);
        if (Objects.isNull(genre)){
            return false;
        }else {
            genreRepository.deleteById(id);
            return true;
        }

    }
}
