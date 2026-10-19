package com.yandex.music.service;


import com.yandex.music.model.auth.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String singIn(String email,String password);
    Boolean singUp(String email,
                   String password,
                   String repeatPassword,
                   String fullName);
    Boolean updatePassword(String oldPassword,
                           String newPassword,
                           String repeatNewPassword);
    Users getCurrentUserService();
}
