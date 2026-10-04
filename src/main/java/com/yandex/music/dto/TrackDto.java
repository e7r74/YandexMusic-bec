package com.yandex.music.dto;

import com.yandex.music.model.Artist;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackDto {
    private Long id;
    private String title;
    private String durationInSeconds;
    private String urlMusic;
    private Long artistId;
    private String artistName;
}
