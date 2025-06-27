package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.LikeCountResponseDTO;
import com.workintech.twitterapi.dto.LikeResponseDTO;
import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Bir tweet'i beğenir. Proje isterlerindeki POST /like endpoint'i.
    @PostMapping("/{tweetId}")
    public ResponseEntity<LikeResponseDTO> like(@PathVariable Long tweetId, Authentication authentication) {
        String username = authentication.getName();
        Like savedLike = likeService.save(username, tweetId);
        return new ResponseEntity<>(convertToDTO(savedLike), HttpStatus.CREATED);
    }

    // Bir tweet'e atılan beğeniyi geri alır. Proje isterlerindeki POST /dislike endpoint'i için DELETE metodu daha uygundur.
    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> dislike(@PathVariable Long tweetId, Authentication authentication) {
        String username = authentication.getName();
        likeService.delete(username, tweetId);
        return ResponseEntity.noContent().build();
    }

    // Bir tweet'in beğeni sayısını döndürür. (Ekstra, kullanışlı bir endpoint)
    @GetMapping("/count/{tweetId}")
    public LikeCountResponseDTO getLikeCount(@PathVariable Long tweetId) {
        int count = likeService.getLikeCount(tweetId);
        return new LikeCountResponseDTO(tweetId, count);
    }

    private LikeResponseDTO convertToDTO(Like like) {
        return new LikeResponseDTO(like.getId(), like.getUser().getId(), like.getTweet().getId());
    }
}
