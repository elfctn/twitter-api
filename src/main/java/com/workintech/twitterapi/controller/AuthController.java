package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.LoginRequestDTO;
import com.workintech.twitterapi.dto.LoginResponseDTO;
import com.workintech.twitterapi.dto.RegistrationResponseDTO;
import com.workintech.twitterapi.dto.UserCreateDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User registeredUser = authenticationService.register(userCreateDTO);
        RegistrationResponseDTO response = new RegistrationResponseDTO(
                registeredUser.getId(),
                "Kullanıcı başarıyla kaydedildi."
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Yeni eklenen /login endpoint'i
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authenticationService.login(loginRequestDTO);
        LoginResponseDTO response = new LoginResponseDTO(token);
        return ResponseEntity.ok(response);
    }
}
