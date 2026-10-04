package com.yandex.music.service;

import com.yandex.music.dto.ArtistDto;
import com.yandex.music.model.Artist;

import java.util.List;

public interface ArtistService {
    List<ArtistDto> getAllArtist();
    ArtistDto getArtist(Long id);
    ArtistDto addArtist(ArtistDto artistDto);
    ArtistDto updateArtist(Long id, ArtistDto artistDto);
    boolean deleteArtist(Long id);
}
