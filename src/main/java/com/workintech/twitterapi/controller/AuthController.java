package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.UserCreateDTO;
import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Proje isterlerindeki /register endpoint'i.
    @PostMapping("/register")
    // @Valid: Spring'e, UserCreateDTO üzerindeki doğrulama kurallarını
    // (@NotNull, @Size, @Email) kontrol etmesini söyler.
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User registeredUser = userService.register(userCreateDTO);
        // Yeni bir kaynak oluşturulduğu için HTTP 201 Created durumu döndürmek daha doğru
        return new ResponseEntity<>(convertToDTO(registeredUser), HttpStatus.CREATED);
    }

    // Entity'yi DTO'ya çeviren yardımcı metot. UserController'dan kopyaladım.
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        if (user.getTweets() != null) {
            dto.setTweets(user.getTweets().stream()
                    .map(tweet -> new UserResponseDTO.TweetSummaryDTO(
                            tweet.getId(),
                            tweet.getContent()
                    ))
                    .collect(Collectors.toList()));
        } else {
            dto.setTweets(new ArrayList<>());
        }
        return dto;
    }
}
