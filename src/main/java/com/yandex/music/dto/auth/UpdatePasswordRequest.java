package com.yandex.music.dto.auth;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;
}
