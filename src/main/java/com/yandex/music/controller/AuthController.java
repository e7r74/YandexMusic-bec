package com.yandex.music.controller;

import com.yandex.music.dto.auth.SignInRequest;
import com.yandex.music.dto.auth.SignUpRequest;
import com.yandex.music.dto.auth.UpdatePasswordRequest;
import com.yandex.music.model.auth.Users;
import com.yandex.music.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> singIn(@RequestBody SignInRequest singIn){
        try {
            String result= userService.singIn(singIn.getEmail(), singIn.getPassword());
            return ResponseEntity.ok(result);
        }catch (RuntimeException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> singUp(@RequestBody SignUpRequest sign){
        Boolean isRegister= userService.singUp(
                sign.getEmail(),
                sign.getPassword(),
                sign.getRepeatPassword(),
                sign.getFullName()
        );
        if (isRegister == null || !isRegister) {
            return ResponseEntity.badRequest().body("Email already exists or passwords do not match!");
        }
        return ResponseEntity.ok("Registration was successful!");
    }
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest passwordRequest){
        Boolean isUpdate=userService.updatePassword(
                passwordRequest.getOldPassword(),
                passwordRequest.getNewPassword(),
                passwordRequest.getRepeatNewPassword()
        );
        if (isUpdate==null){
            return ResponseEntity.badRequest().body("You are not logged in");
        }
        if (!isUpdate){
            return ResponseEntity.badRequest().body("Incorrect old password or new passwords do not match!");
        }
        return ResponseEntity.ok("Password changed successfully!");
    }
    @GetMapping("/profile")
    public ResponseEntity<?> profileUser(){
        Users profile= userService.getCurrentUserService();
        if (profile==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(profile,HttpStatus.OK);
        }
    }
}
