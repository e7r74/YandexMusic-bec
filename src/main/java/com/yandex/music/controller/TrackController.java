package com.yandex.music.controller;

import com.yandex.music.dto.TrackDto;
import com.yandex.music.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/track")
@CrossOrigin(origins = "http://localhost:5173")
public class TrackController {
    private final TrackService trackService;

    @GetMapping
    public ResponseEntity<?> getAllTrack(){
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable Long id){
        TrackDto trackDto=trackService.getTrackById(id);
        if (Objects.isNull(trackDto)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(trackDto,HttpStatus.OK);
        }
    }
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<?> getTrackByArtist(@PathVariable Long artistId){
        List<TrackDto> trackDtos=trackService.getTrackByArtist(artistId);
        if (Objects.isNull(trackDtos)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(trackDtos,HttpStatus.FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addTrack(@RequestBody TrackDto trackDto){
        TrackDto createTrack= trackService.addTrack(trackDto);
        if (Objects.isNull(createTrack)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(createTrack,HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable Long id){
        boolean deleteTrack= trackService.deleteTrack(id);
        if (deleteTrack){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<?> playCount(@PathVariable Long id){
        TrackDto trackDtoCount = trackService.incrementPlayCount(id);
       return new ResponseEntity<>(trackDtoCount, HttpStatus.OK);
    }
    @GetMapping("/popular")
    public List<TrackDto> popularTrack(){
        return trackService.popularTrack();
    }

}
