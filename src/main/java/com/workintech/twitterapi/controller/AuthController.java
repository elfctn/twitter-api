package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.UserCreateDTO;
import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.mapper.UserMapper; // Mapper'ı import ediyoruz
import com.workintech.twitterapi.service.UserService;
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

    private final UserService userService;
    private final UserMapper userMapper; // Mapper'ı enjekte ediyoruz

    // Constructor'a UserMapper'ı da ekliyoruz. Spring bunu otomatik olarak bulup verecek.
    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User registeredUser = userService.register(userCreateDTO);

        // Artık manuel `convertToDTO` yerine, otomatik `userMapper`'ı kullanıyoruz.
        return new ResponseEntity<>(userMapper.userToUserResponseDTO(registeredUser), HttpStatus.CREATED);
    }

    // Artık bu manuel metoda ihtiyacımız kalmadı! Silebiliriz.
    /*
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
    */
}