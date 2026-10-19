package com.yandex.music.service.impl;

import com.yandex.music.model.auth.Permissions;
import com.yandex.music.model.auth.Users;
import com.yandex.music.repository.PermissionsRepository;
import com.yandex.music.repository.UsersRepository;
import com.yandex.music.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {
    private final UsersRepository usersRepository;
    private final PermissionsRepository permissionsRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Users user = usersRepository.findByEmail(username);
      if (user!=null){
          return user;
      }
      throw new UsernameNotFoundException("User not found!");
    }

    @Override
    public String singIn(String email, String password) {
        Users user= usersRepository.findByEmail(email);
        if (user==null || !passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Incorrect email or password!");
        }
        return "Successful login! User is authorized.";
    }

    @Override
    public Boolean singUp(String email, String password, String repeatPassword, String fullName) {
       Users user=usersRepository.findByEmail(email);
       if (user!=null){
           return false;
       }
       if (!password.equals(repeatPassword)){
           return false;
       }
       Permissions defaultPermission= permissionsRepository.findByPermission("ROLE_USER");
        List<Permissions> permissionsList= new ArrayList<>();
        permissionsList.add(defaultPermission);

        Users newUser= new Users();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);
        newUser.setPermissions(permissionsList);
        usersRepository.save(newUser);
        return true;
    }

    @Override
    public Boolean updatePassword(String oldPassword, String newPassword, String repeatNewPassword) {
        Users currentUser= getCurrentUser();
        if (currentUser==null){
            return null;
        }
        if (!(passwordEncoder.matches(oldPassword, currentUser.getPassword()))){
            return false;
        }
        if (!(newPassword.equals(repeatNewPassword))){
            return false;
        }
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(currentUser);
        return true;
    }

    @Override
    public Users getCurrentUserService() {
        return getCurrentUser();
    }

    private Users getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }


}
