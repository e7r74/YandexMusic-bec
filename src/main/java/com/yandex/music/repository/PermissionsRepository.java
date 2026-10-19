package com.yandex.music.repository;

import com.yandex.music.model.auth.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    Permissions findByPermission(String permission);
}
