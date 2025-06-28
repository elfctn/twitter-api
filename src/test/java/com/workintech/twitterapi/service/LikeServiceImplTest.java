package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.LikeNotFoundException;
import com.workintech.twitterapi.exception.TwitterConflictException;
import com.workintech.twitterapi.repository.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceImplTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserService userService;

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private LikeServiceImpl likeService;

    private User user;
    private Tweet tweet;
    private Like like;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("test_user");

        tweet = new Tweet();
        tweet.setId(100L);
        // Tweet'in like listesini başlatıyoruz.
        tweet.setLikes(new ArrayList<>());

        like = new Like();
        like.setId(1000L);
        like.setUser(user);
        like.setTweet(tweet);
    }

    @Test
    @DisplayName("Henüz beğenilmemiş bir tweet'i beğenme senaryosu")
    void testSave_whenNotAlreadyLiked_shouldSaveLike() {
        // ARRANGE
        when(userService.findByUsername("test_user")).thenReturn(Optional.of(user));
        when(tweetService.getById(100L)).thenReturn(tweet);
        when(likeRepository.existsByTweetIdAndUserId(100L, 1L)).thenReturn(false);

        // ACT
        likeService.save("test_user", 100L);

        // ASSERT
        verify(likeRepository, times(1)).save(any(Like.class));
    }

    @Test
    @DisplayName("Daha önce beğenilmiş bir tweet'i tekrar beğenme senaryosu")
    void testSave_whenAlreadyLiked_shouldThrowConflictException() {
        // ARRANGE
        when(userService.findByUsername("test_user")).thenReturn(Optional.of(user));
        when(tweetService.getById(100L)).thenReturn(tweet);
        when(likeRepository.existsByTweetIdAndUserId(100L, 1L)).thenReturn(true);

        // ACT & ASSERT
        assertThrows(TwitterConflictException.class, () -> {
            likeService.save("test_user", 100L);
        });
        verify(likeRepository, never()).save(any(Like.class));
    }

    @Test
    @DisplayName("Mevcut bir beğeniyi geri alma senaryosu")
    void testDelete_whenLikeExists_shouldDeleteLike() {
        // ARRANGE
        when(userService.findByUsername("test_user")).thenReturn(Optional.of(user));
        // Beğeniyi bulması için findBy... metodunu mock'luyoruz.
        when(likeRepository.findByTweetIdAndUserId(100L, 1L)).thenReturn(Optional.of(like));

        // ACT
        likeService.delete("test_user", 100L);

        // ASSERT
        verify(likeRepository, times(1)).delete(like);
    }

    @Test
    @DisplayName("Mevcut olmayan bir beğeniyi geri almaya çalışma senaryosu")
    void testDelete_whenLikeDoesNotExist_shouldThrowLikeNotFoundException() {
        // ARRANGE
        when(userService.findByUsername("test_user")).thenReturn(Optional.of(user));
        // Beğeniyi bulamaması için boş bir Optional döndürüyoruz.
        when(likeRepository.findByTweetIdAndUserId(100L, 1L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(LikeNotFoundException.class, () -> {
            likeService.delete("test_user", 100L);
        });
        verify(likeRepository, never()).delete(any(Like.class));
    }

    @Test
    @DisplayName("Bir tweet'in beğeni sayısını doğru getirme senaryosu")
    void testGetLikeCount_shouldReturnCorrectCount() {
        // ARRANGE
        // Tweet'in beğeni listesine 3 tane sahte beğeni ekliyoruz.
        tweet.getLikes().add(new Like());
        tweet.getLikes().add(new Like());
        tweet.getLikes().add(new Like());
        when(tweetService.getById(100L)).thenReturn(tweet);

        // ACT
        int likeCount = likeService.getLikeCount(100L);

        // ASSERT
        // Dönen sonucun 3 olup olmadığını kontrol ediyoruz.
        assertEquals(3, likeCount);
    }
}
