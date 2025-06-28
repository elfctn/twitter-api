package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.TwitterAuthException;
import com.workintech.twitterapi.repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class): Bu sınıfta Mockito'nun kullanılacağını JUnit 5'e söyler.
@ExtendWith(MockitoExtension.class)
class TweetServiceImplTest {

    // @Mock: Mockito'ya, bu alan için sahte (taklit) bir nesne yaratmasını söyler.
    // Bu sahte nesneler, gerçek veritabanına veya servise gitmez.
    @Mock
    private TweetRepository tweetRepository;

    @Mock
    private UserService userService;

    // @InjectMocks: Test edeceğimiz asıl sınıf budur. Mockito, yukarıda @Mock ile
    // oluşturulan sahte nesneleri otomatik olarak bu sınıfın constructor'ına enjekte eder.
    @InjectMocks
    private TweetServiceImpl tweetService;

    private User owner;
    private User nonOwner;
    private Tweet tweet;

    // @BeforeEach: Bu metot, bu sınıftaki HER BİR test metodundan önce otomatik olarak çalıştırılır.
    // Testlerimiz için gerekli olan standart verileri burada oluştururuz.
    @BeforeEach
    void setUp() {
        // Testler için bir "sahip" kullanıcı oluşturuyoruz.
        owner = new User();
        owner.setId(1L);
        owner.setUsername("owner_user");

        // Testler için "sahip olmayan" bir kullanıcı oluşturuyoruz.
        nonOwner = new User();
        nonOwner.setId(2L);
        nonOwner.setUsername("non_owner_user");

        // Test edilecek tweet'i oluşturuyoruz ve sahibini "owner" olarak ayarlıyoruz.
        tweet = new Tweet();
        tweet.setId(100L);
        tweet.setUser(owner);
    }

    @Test
    @DisplayName("Yeni bir tweet kaydedildiğinde başarılı olmalı")
    void testSave_shouldSaveAndReturnTweet() {
        // ARRANGE
        TweetCreateDTO createDTO = new TweetCreateDTO();
        createDTO.setContent("Yeni tweet!");
        when(userService.findByUsername("owner_user")).thenReturn(Optional.of(owner));
        // save metodu çağrıldığında, içeriği set edilmiş bir tweet döndürmesini simüle ediyoruz.
        when(tweetRepository.save(any(Tweet.class))).thenAnswer(invocation -> {
            Tweet savedTweet = invocation.getArgument(0);
            savedTweet.setId(101L); // Simüle edilmiş bir ID atıyoruz.
            return savedTweet;
        });

        // ACT
        Tweet savedTweet = tweetService.save(createDTO, "owner_user");

        // ASSERT
        assertEquals("Yeni tweet!", savedTweet.getContent());
        assertEquals("owner_user", savedTweet.getUser().getUsername());
        verify(tweetRepository, times(1)).save(any(Tweet.class));
    }


    @Test
    @DisplayName("Tweet sahibi kendi tweet'ini güncelleyebilir")
    void testUpdate_whenUserIsOwner_shouldUpdateTweet() {
        // ARRANGE
        TweetCreateDTO updateDTO = new TweetCreateDTO();
        updateDTO.setContent("Güncellenmiş içerik");
        when(tweetRepository.findById(100L)).thenReturn(Optional.of(tweet));
        when(tweetRepository.save(any(Tweet.class))).thenReturn(tweet);

        // ACT
        tweetService.update(100L, updateDTO, "owner_user");

        // ASSERT
        verify(tweetRepository, times(1)).save(tweet);
        assertEquals("Güncellenmiş içerik", tweet.getContent());
    }

    @Test
    @DisplayName("Tweet sahibi olmayan bir kullanıcı tweet'i güncelleyemez")
    void testUpdate_whenUserIsNotOwner_shouldThrowAuthException() {
        // ARRANGE
        TweetCreateDTO updateDTO = new TweetCreateDTO();
        updateDTO.setContent("Yetkisiz güncelleme denemesi");
        when(tweetRepository.findById(100L)).thenReturn(Optional.of(tweet));

        // ACT & ASSERT
        assertThrows(TwitterAuthException.class, () -> {
            tweetService.update(100L, updateDTO, "non_owner_user");
        });
        verify(tweetRepository, never()).save(any(Tweet.class));
    }


    @Test
    @DisplayName("Tweet sahibi kendi tweet'ini silebilir")
    void testDelete_whenUserIsOwner_shouldDeleteTweet() {
        // ARRANGE
        when(tweetRepository.findById(100L)).thenReturn(Optional.of(tweet));

        // ACT
        tweetService.delete(100L, "owner_user");

        // ASSERT
        verify(tweetRepository, times(1)).delete(tweet);
    }

    @Test
    @DisplayName("Tweet sahibi olmayan bir kullanıcı tweet'i silemez")
    void testDelete_whenUserIsNotOwner_shouldThrowAuthException() {
        // ARRANGE
        when(tweetRepository.findById(100L)).thenReturn(Optional.of(tweet));

        // ACT & ASSERT
        assertThrows(TwitterAuthException.class, () -> {
            tweetService.delete(100L, "non_owner_user");
        });

        verify(tweetRepository, never()).delete(any(Tweet.class));
    }
}
