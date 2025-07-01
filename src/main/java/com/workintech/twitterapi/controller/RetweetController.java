package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.RetweetCountResponseDTO;
import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.mapper.RetweetMapper; // Mapper'ı import ediyoruz
import com.workintech.twitterapi.service.RetweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;
    private final RetweetMapper retweetMapper;

    public RetweetController(RetweetService retweetService, RetweetMapper retweetMapper) {
        this.retweetService = retweetService;
        this.retweetMapper = retweetMapper;
    }


    @PostMapping("/{tweetId}")
    public ResponseEntity<RetweetResponseDTO> retweet(@PathVariable Long tweetId, Authentication authentication) {
        String username = authentication.getName();
        Retweet savedRetweet = retweetService.save(username, tweetId);
        return new ResponseEntity<>(retweetMapper.retweetToRetweetResponseDTO(savedRetweet), HttpStatus.CREATED);
    }


    @DeleteMapping("/{retweetId}")
    public ResponseEntity<Void> deleteRetweet(@PathVariable Long retweetId, Authentication authentication) {
        String username = authentication.getName();
        retweetService.delete(retweetId, username);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/count/{tweetId}")
    public RetweetCountResponseDTO getRetweetCount(@PathVariable Long tweetId) {
        int count = retweetService.getRetweetCount(tweetId);
        return new RetweetCountResponseDTO(tweetId, count);
    }
}
