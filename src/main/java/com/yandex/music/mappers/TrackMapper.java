package com.yandex.music.mappers;

import com.yandex.music.dto.TrackDto;
import com.yandex.music.model.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {
    @Mapping(source = "artist.id", target = "artistId")
    @Mapping(source = "artist.name", target = "artistName")
    @Mapping(target = "genreIds", expression = "java(track.getGenres() != null ? track.getGenres().stream().map(g -> g.getId()).toList() : null)")
    @Mapping(target = "genreNames", expression = "java(track.getGenres() != null ? track.getGenres().stream().map(g -> g.getName()).toList() : null)")
    TrackDto toDto(Track track);

    @Mapping(source = "artistId", target = "artist.id")
    @Mapping(source = "artistName", target = "artist.name")
    @Mapping(target = "genres", ignore = true)
    Track toTrack(TrackDto trackDto);

    List<TrackDto> tracksDto(List<Track> tracks);
}
