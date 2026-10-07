package com.yandex.music.service;

import com.yandex.music.dto.TrackDto;

import java.util.List;

public interface TrackService {
    List<TrackDto> getAllTracks();
    TrackDto getTrackById(Long id);
    List<TrackDto> getTrackByArtist(Long artistId);
    TrackDto addTrack(TrackDto trackDto);
    boolean deleteTrack(Long id);
    TrackDto incrementPlayCount(Long id);
    List<TrackDto> popularTrack();
}
