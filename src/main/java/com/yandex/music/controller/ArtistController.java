package com.yandex.music.controller;

import com.yandex.music.dto.ArtistDto;
import com.yandex.music.model.Artist;
import com.yandex.music.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
@CrossOrigin(origins = "http://localhost:5173")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<?> getAllArtist(){
        List<ArtistDto> artists= artistService.getAllArtist();
        if (Objects.isNull(artists)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else return new ResponseEntity<>(artists,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        ArtistDto artistDto= artistService.getArtist(id);
        if (Objects.isNull(artistDto)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(artistDto,HttpStatus.OK);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addArtist(@RequestBody ArtistDto artistDto){
        ArtistDto artistDto2= artistService.addArtist(artistDto);
       return new ResponseEntity<>(artistDto2,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable("id") Long id,
                                          @RequestBody ArtistDto artistDto){
        ArtistDto newArtist=artistService.updateArtist(id,artistDto);
        if (Objects.isNull(newArtist)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(newArtist, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteArtist(@PathVariable("id") Long id){
       boolean artist= artistService.deleteArtist(id);
       if (artist){
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
}
