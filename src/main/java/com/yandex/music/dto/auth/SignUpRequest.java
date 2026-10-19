package com.yandex.music.dto.auth;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String repeatPassword;
    private String fullName;
}
