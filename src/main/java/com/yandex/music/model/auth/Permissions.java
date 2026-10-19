package com.yandex.music.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_permissions")
public class Permissions extends BaseEntity implements GrantedAuthority {
    private String permission;

    @Override
    public @Nullable String getAuthority() {
        return permission;
    }
}
