package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.mapper.TweetMapper; // Mapper'ı import ediyoruz
import com.workintech.twitterapi.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;
    private final TweetMapper tweetMapper;

    public TweetController(TweetService tweetService, TweetMapper tweetMapper) {
        this.tweetService = tweetService;
        this.tweetMapper = tweetMapper;
    }

    @PostMapping
    public ResponseEntity<TweetResponseDTO> createTweet(@RequestBody TweetCreateDTO tweetCreateDTO, Authentication authentication) {
        String username = authentication.getName();
        Tweet savedTweet = tweetService.save(tweetCreateDTO, username);

        return new ResponseEntity<>(tweetMapper.tweetToTweetResponseDTO(savedTweet), HttpStatus.CREATED);
    }

    @GetMapping
    public List<TweetResponseDTO> getAllTweets() {
        List<Tweet> tweets = tweetService.getAll();
        return tweetMapper.tweetListToTweetResponseDTOList(tweets);
    }

    @GetMapping("/findByUserId")
    public List<TweetResponseDTO> getTweetsByUserId(@RequestParam Long userId) {
        List<Tweet> tweets = tweetService.getByUserId(userId);
        return tweetMapper.tweetListToTweetResponseDTOList(tweets);
    }

    @GetMapping("/{id}")
    public TweetResponseDTO getTweetById(@PathVariable Long id) {
        Tweet tweet = tweetService.getById(id);
        return tweetMapper.tweetToTweetResponseDTO(tweet);
    }

    @PutMapping("/{id}")
    public TweetResponseDTO updateTweet(@PathVariable Long id,
                                        @RequestBody TweetCreateDTO tweetCreateDTO,
                                        Authentication authentication) {
        String username = authentication.getName();
        Tweet updatedTweet = tweetService.update(id, tweetCreateDTO, username);
        return tweetMapper.tweetToTweetResponseDTO(updatedTweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        tweetService.delete(id, username);
        return ResponseEntity.noContent().build();
    }
}
