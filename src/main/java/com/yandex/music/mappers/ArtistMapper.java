package com.yandex.music.mappers;

import com.yandex.music.dto.ArtistDto;
import com.yandex.music.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(source = "name", target="artistName")
    @Mapping(source = "photoUrl", target = "artistPhotoUrl")
    ArtistDto toDto(Artist artist);

    @Mapping(source = "artistName", target = "name")
    @Mapping(source = "artistPhotoUrl", target = "photoUrl")
    Artist toEntity(ArtistDto artistDto);

    List<ArtistDto> toDtoList(List<Artist> artists);
}
