package com.yandex.music.model;

import com.yandex.music.model.auth.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="photo_url")
    private String photoUrl;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Users user;
}
