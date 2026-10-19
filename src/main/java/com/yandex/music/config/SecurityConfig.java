package com.yandex.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/auth/signup", "/api/auth/signin").permitAll()
                        .requestMatchers("/track/add").hasAnyAuthority("ROLE_ARTIST", "ROLE_ADMIN")
                        .requestMatchers("/track/delete/**").hasAnyAuthority("ROLE_ARTIST", "ROLE_ADMIN")
                        .requestMatchers(
                                "/artist/add",
                                "/artist/update/**",
                                "/artist/delete/{id}",
                                "/genre/add",
                                "/genre/update/**",
                                "/genre/delete/**"
                        ).hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                ).httpBasic(httpBasic->{});
        return http.build();
    }
}
