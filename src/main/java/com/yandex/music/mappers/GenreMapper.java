package com.yandex.music.mappers;

import com.yandex.music.dto.GenreDto;
import com.yandex.music.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(source = "name", target = "genreName")
    GenreDto toDto(Genre genre);

    @Mapping(source = "genreName", target = "name")
    Genre toEntity(GenreDto genreDto);

    List<GenreDto> genresDto(List<Genre> genres);
}
