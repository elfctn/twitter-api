package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.LoginRequestDTO;
import com.workintech.twitterapi.dto.UserCreateDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @Override
    public User register(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setEmail(userCreateDTO.getEmail());
        return userRepository.save(user);
    }


    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        // Spring Security kimlik doğrulama mekanizmasını tetikle -----repodaki şifre ile uyuşuyor mu kontrol eder
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );
        // Kimlik doğrulama başarılıysa TokenServicei kullanarak bir JWT üret.
        return tokenService.generateToken(authentication);
    }
}
