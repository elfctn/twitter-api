package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    // Constructor Injection: Servisleri constructor üzerinden alıyoruz.
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<TweetResponseDTO> createTweet(@RequestBody TweetCreateDTO tweetCreateDTO, Authentication authentication) {
        // Controller'ın görevi: Gelen isteği ve kimlik bilgisini doğrulamak ve servise paslamak.
        String username = authentication.getName();
        Tweet savedTweet = tweetService.save(tweetCreateDTO, username);
        // Yeni bir kaynak oluşturulduğunda 201 Created durumu döndürmek daha doğrudur.
        return new ResponseEntity<>(convertToDTO(savedTweet), HttpStatus.CREATED);
    }

    @GetMapping
    public List<TweetResponseDTO> getAllTweets() {
        List<Tweet> tweets = tweetService.getAll();
        return tweets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Proje isterlerindeki endpoint'e sadık kalıyoruz.
    @GetMapping("/findByUserId")
    public List<TweetResponseDTO> getTweetsByUserId(@RequestParam Long userId) {
        List<Tweet> tweets = tweetService.getByUserId(userId);
        return tweets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TweetResponseDTO getTweetById(@PathVariable Long id) {
        Tweet tweet = tweetService.getById(id);
        return convertToDTO(tweet);
    }

    @PutMapping("/{id}")
    public TweetResponseDTO updateTweet(@PathVariable Long id,
                                        @RequestBody TweetCreateDTO tweetCreateDTO,
                                        Authentication authentication) { // 1. Authentication eklendi
        // 2. İşlemi yapan kullanıcının adı alındı
        String username = authentication.getName();
        // 3. Servis metodu artık 3 parametre ile doğru şekilde çağırılıyor
        Tweet updatedTweet = tweetService.update(id, tweetCreateDTO, username);

        return convertToDTO(updatedTweet);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, Authentication authentication) {
        // Controller, silme yetkisi kontrolü için kullanıcı adını da servise gönderir.
        String username = authentication.getName();
        tweetService.delete(id, username);
        // Silme işlemi başarılı olduğunda 204 No Content döndürmek standarttır.
        return ResponseEntity.noContent().build();
    }

    // Helper metot: Entity'yi DTO'ya çevirir.
    private TweetResponseDTO convertToDTO(Tweet tweet) {
        TweetResponseDTO.UserSummaryDTO userDTO = new TweetResponseDTO.UserSummaryDTO(
                tweet.getUser().getId(),
                tweet.getUser().getUsername()
        );
        return new TweetResponseDTO(
                tweet.getId(),
                tweet.getContent(),
                tweet.getCreatedAt(),
                tweet.getUpdatedAt(),
                userDTO
        );
    }
}

