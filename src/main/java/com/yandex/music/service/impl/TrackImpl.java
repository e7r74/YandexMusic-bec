package com.yandex.music.service.impl;

import com.yandex.music.dto.TrackDto;
import com.yandex.music.mappers.TrackMapper;
import com.yandex.music.model.Artist;
import com.yandex.music.model.Genre;
import com.yandex.music.model.Track;
import com.yandex.music.model.auth.Users;
import com.yandex.music.repository.ArtistRepository;
import com.yandex.music.repository.GenreRepository;
import com.yandex.music.repository.TrackRepository;
import com.yandex.music.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrackImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
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
        Users currentUser= getCurrentUser();
        if (currentUser==null){
            throw new RuntimeException("You are not logged in!");
        }
        Artist artist= artistRepository.findByUser(currentUser);
        if (artist==null){
            throw new RuntimeException("The current user does not have an artist profile!");
        }

        List<Genre> genres= new ArrayList<>();
        if (trackDto.getGenreIds()!=null){
            genres= genreRepository.findAllById(trackDto.getGenreIds());
        }
        Track track= Track.builder()
                .title(trackDto.getTitle())
                .photoUrl(trackDto.getTrackPhotoUrl())
                .urlMusic(trackDto.getUrlMusic())
                .artist(artist)
                .popularTrack(0l)
                .genres(genres)
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
    @Override
    public TrackDto incrementPlayCount(Long id) {
        Track track=trackRepository.findById(id).orElse(null);
        if (Objects.isNull(track)){
            return null;
        }else {
            track.setPopularTrack(track.getPopularTrack()+1);
            trackRepository.save(track);
            return trackMapper.toDto(track);
        }
    }

    @Override
    public List<TrackDto> popularTrack() {
        List<Track> tracks=trackRepository.findTop10ByOrderByPopularTrackDesc();
        return trackMapper.tracksDto(tracks);
    }
    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (Users) authentication.getPrincipal();
        }
        return null;
    }


}
