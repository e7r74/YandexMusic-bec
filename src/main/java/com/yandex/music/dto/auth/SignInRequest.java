package com.yandex.music.dto.auth;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
