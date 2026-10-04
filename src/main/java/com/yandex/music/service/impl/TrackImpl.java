package com.yandex.music.service.impl;

import com.yandex.music.dto.TrackDto;
import com.yandex.music.mappers.TrackMapper;
import com.yandex.music.model.Artist;
import com.yandex.music.model.Track;
import com.yandex.music.repository.ArtistRepository;
import com.yandex.music.repository.TrackRepository;
import com.yandex.music.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrackImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final TrackMapper trackMapper;

    @Override
    public List<TrackDto> getAllTracks() {
        List<Track> track=trackRepository.findAll();
        return trackMapper.tracksDto(track);
    }

    @Override
    public TrackDto getTrackById(Long id) {
        Track track=trackRepository.findById(id).orElse(null);
        if (Objects.isNull(track)){
            return null;
        }else {
            return trackMapper.toDto(track);
        }
    }

    @Override
    public List<TrackDto> getTrackByArtist(Long artistId) {
        List<Track> tracks= trackRepository.findByArtistId(artistId);
        return trackMapper.tracksDto(tracks);
    }

    @Override
    public TrackDto addTrack(TrackDto trackDto) {
        Artist artist= artistRepository.findById(trackDto.getArtistId()).orElse(null);
        Track track= Track.builder()
                .id(trackDto.getId())
                .title(trackDto.getTitle())
                .durationInSeconds(trackDto.getDurationInSeconds())
                .urlMusic(trackDto.getUrlMusic())
                .artist(artist)
                .build();
        Track saveTrack= trackRepository.save(track);
        return trackMapper.toDto(saveTrack);
    }

    @Override
    public boolean deleteTrack(Long id) {
        Track track=trackRepository.findById(id).orElse(null);
        if (Objects.isNull(track)){
            return false;
        }else {
            trackRepository.deleteById(id);
            return true;
        }
    }

}
