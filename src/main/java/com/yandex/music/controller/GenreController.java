package com.yandex.music.controller;

import com.yandex.music.dto.GenreDto;
import com.yandex.music.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<?> getAllGenre(){
        List<GenreDto> genreDtos= genreService.getAllGenre();
        if (Objects.isNull(genreDtos)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(genreDtos,HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        GenreDto genreDto=genreService.getGenreById(id);
        if (Objects.isNull(genreDto)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(genreDto, HttpStatus.FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addGenre(@RequestBody GenreDto genreDto){
        GenreDto createGenre=genreService.addGenre(genreDto);
       if (Objects.isNull(createGenre)) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }else {
           return new ResponseEntity<>(createGenre,HttpStatus.CREATED);
       }
   }

   @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable Long id,
                                         @RequestBody GenreDto genreDto){
        GenreDto updateGenre=genreService.updateGenre(id, genreDto);
        if (Objects.isNull(updateGenre)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(updateGenre, HttpStatus.CREATED);
        }
   }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id){
        boolean genre= genreService.deleteGenre(id);
        if (genre){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
   }
}
