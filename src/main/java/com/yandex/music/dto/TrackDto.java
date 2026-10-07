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
    private String durationInSeconds;
    private String urlMusic;
    private Long popularTrack;
    private Long artistId;
    private String artistName;
    private List<Long> genreIds;
    private List<String> genreNames;

}
