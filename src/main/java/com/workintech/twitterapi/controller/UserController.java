package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users") // RESTful standartları için çoğul isim kullanmak daha yaygındır.
public class UserController {

    private final UserService userService;

    // Bağımlılığı constructor üzerinden alıyoruz.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserResponseDTO getUserByUsername(@PathVariable String username) {
        // Servisimiz Optional<User> döndürdüğü için, sonucu orElseThrow ile güvenle açıyoruz.
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username)); // TODO: Custom exception
        return convertToDTO(user);
    }

    // Entity'yi DTO'ya çeviren yardımcı metot
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        // Null kontrolü ile kullanıcının tweet listesi boşsa bile hata almayı engelliyoruz.
        if (user.getTweets() != null) {
            dto.setTweets(user.getTweets().stream()
                    .map(tweet -> new UserResponseDTO.TweetSummaryDTO(
                            tweet.getId(),
                            tweet.getContent()
                    ))
                    .collect(Collectors.toList()));
        } else {
            dto.setTweets(new ArrayList<>()); // Tweet'leri yoksa boş bir liste ata.
        }

        return dto;
    }
}
