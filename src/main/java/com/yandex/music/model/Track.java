package com.yandex.music.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "duration")
    private String durationInSeconds;
    @Column(name = "url_music", nullable = false)
    private String urlMusic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="artist_id", nullable = false)
    private Artist artist;

}
