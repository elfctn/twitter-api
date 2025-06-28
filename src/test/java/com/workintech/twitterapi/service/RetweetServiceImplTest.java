package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.RetweetNotFoundException;
import com.workintech.twitterapi.exception.TwitterAuthException;
import com.workintech.twitterapi.exception.TwitterConflictException;
import com.workintech.twitterapi.repository.RetweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetweetServiceImplTest {

    @Mock
    private RetweetRepository retweetRepository;

    @Mock
    private UserService userService;

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private RetweetServiceImpl retweetService;

    private User user;
    private User otherUser;
    private Tweet tweet;
    private Retweet retweet;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("test_user");

        otherUser = new User();
        otherUser.setId(2L);
        otherUser.setUsername("other_user");

        tweet = new Tweet();
        tweet.setId(100L);

        retweet = new Retweet();
        retweet.setId(1000L);
        retweet.setUser(user); // Bu retweet'in sahibi "test_user"
        retweet.setTweet(tweet);
    }

    @Test
    @DisplayName("Henüz retweetlenmemiş bir tweet'i retweetleme senaryosu")
    void testSave_whenNotAlreadyRetweeted_shouldSaveRetweet() {
        // ARRANGE
        when(userService.findByUsername("test_user")).thenReturn(Optional.of(user));
        when(tweetService.getById(100L)).thenReturn(tweet);
        when(retweetRepository.existsByTweetIdAndUserId(100L, 1L)).thenReturn(false);

        // ACT
        retweetService.save("test_user", 100L);

        // ASSERT
        verify(retweetRepository, times(1)).save(any(Retweet.class));
    }

    @Test
    @DisplayName("Daha önce retweetlenmiş bir tweet'i tekrar retweetleme senaryosu")
    void testSave_whenAlreadyRetweeted_shouldThrowConflictException() {
        // ARRANGE
        when(userService.findByUsername("test_user")).thenReturn(Optional.of(user));
        when(tweetService.getById(100L)).thenReturn(tweet);
        when(retweetRepository.existsByTweetIdAndUserId(100L, 1L)).thenReturn(true);

        // ACT & ASSERT
        assertThrows(TwitterConflictException.class, () -> {
            retweetService.save("test_user", 100L);
        });
        verify(retweetRepository, never()).save(any(Retweet.class));
    }

    @Test
    @DisplayName("Kullanıcı kendi retweet'ini silebilir")
    void testDelete_whenUserIsOwner_shouldDeleteRetweet() {
        // ARRANGE
        when(retweetRepository.findById(1000L)).thenReturn(Optional.of(retweet));

        // ACT
        retweetService.delete(1000L, "test_user");

        // ASSERT
        verify(retweetRepository, times(1)).delete(retweet);
    }

    @Test
    @DisplayName("Kullanıcı başkasının retweet'ini silemez")
    void testDelete_whenUserIsNotOwner_shouldThrowAuthException() {
        // ARRANGE
        when(retweetRepository.findById(1000L)).thenReturn(Optional.of(retweet));

        // ACT & ASSERT
        assertThrows(TwitterAuthException.class, () -> {
            // "other_user", "test_user"ın retweet'ini silmeye çalışıyor.
            retweetService.delete(1000L, "other_user");
        });
        verify(retweetRepository, never()).delete(any(Retweet.class));
    }

    @Test
    @DisplayName("Mevcut olmayan bir retweet'i silmeye çalışma senaryosu")
    void testDelete_whenRetweetDoesNotExist_shouldThrowRetweetNotFoundException() {
        // ARRANGE
        when(retweetRepository.findById(999L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(RetweetNotFoundException.class, () -> {
            retweetService.delete(999L, "test_user");
        });
    }
}
