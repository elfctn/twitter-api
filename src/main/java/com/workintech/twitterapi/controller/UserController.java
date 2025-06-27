package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.UserNotFoundException;
import com.workintech.twitterapi.mapper.UserMapper;
import com.workintech.twitterapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // RESTful standartları için çoğul isim kullanmak daha yaygındır.
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper; // UserMapper'ı enjekte ediyoruz.

    // Bağımlılığı constructor üzerinden alıyoruz.
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{username}")
    public UserResponseDTO getUserByUsername(@PathVariable String username) {
        // Servisimiz Optional<User> döndürdüğü için, sonucu orElseThrow ile güvenle açıyoruz.
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));

        // Manuel `convertToDTO` yerine, `userMapper`'ı kullanıyoruz.
        return userMapper.userToUserResponseDTO(user);
    }
}
