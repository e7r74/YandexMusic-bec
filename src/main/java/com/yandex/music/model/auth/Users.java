package com.yandex.music.model.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_users")
public class Users extends BaseEntity implements UserDetails {
    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permissions> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }


    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
