package com.yandex.music.dto;

import com.yandex.music.model.Artist;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackDto {
    private Long id;
    private String title;
    private String trackPhotoUrl;
    private String urlMusic;
    private Long popularTrack;
    private Artist artist;
    private List<Long> genreIds;
    private List<String> genreNames;

}
