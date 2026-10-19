package com.yandex.music.repository;

import com.yandex.music.model.Artist;
import com.yandex.music.model.auth.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByUser(Users user);
}
