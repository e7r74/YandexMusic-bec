package com.yandex.music.service.impl;

import com.yandex.music.dto.ArtistDto;
import com.yandex.music.mappers.ArtistMapper;
import com.yandex.music.model.Artist;
import com.yandex.music.model.auth.Users;
import com.yandex.music.repository.ArtistRepository;
import com.yandex.music.repository.UsersRepository;
import com.yandex.music.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final UsersRepository usersRepository;

    @Override
    public List<ArtistDto> getAllArtist() {
        List<Artist> artists= artistRepository.findAll();
        return artistMapper.toDtoList(artists);
    }

    @Override
    public ArtistDto getArtist(Long id) {
        Artist artist=checkArtist(id);
        if (artist!=null){
            return artistMapper.toDto(artist);
        }return null;
    }

    @Override
    public ArtistDto addArtist(ArtistDto artistDto) {
        Users user= null;
        if (artistDto.getUserId()!=null){
            user= usersRepository.findById(artistDto.getUserId()).orElse(null);
        }
        Artist artist=artistMapper.toEntity(artistDto);
        artist.setUser(user);
        artistRepository.save(artist);
        ArtistDto artistDto2=artistMapper.toDto(artist);
        return artistDto2;
    }

    @Override
    public ArtistDto updateArtist(Long id, ArtistDto artistDto) {
        Users user= null;
        if (artistDto.getUserId()!=null){
            user= usersRepository.findById(artistDto.getUserId()).orElse(null);
        }
       Artist artist= checkArtist(id);
       if (artist!=null){
           artist.setName(artistDto.getArtistName());
           artist.setPhotoUrl(artistDto.getArtistPhotoUrl());
           artist.setUser(user);
           artistRepository.save(artist);
           return artistMapper.toDto(artist);
       }
       return null;
    }

    @Override
    public boolean deleteArtist(Long id) {
       Artist artist= checkArtist(id);
       if (artist!=null){
          artistRepository.deleteById(id);
           return true;
       }
       return false;
    }
    private Artist checkArtist(Long id){
        return artistRepository.findById(id).orElse(null);
    }
}
