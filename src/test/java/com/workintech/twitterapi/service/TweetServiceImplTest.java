package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.TwitterAuthException;
import com.workintech.twitterapi.repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testDelete_whenUserIsOwner_shouldDeleteTweet() {
        // ARRANGE (Hazırlık): Testin koşullarını ayarlıyoruz.
        // Kural 1: tweetRepository'nin findById(100L) metodu çağrıldığında,
        // içi dolu bir Optional<Tweet> döndürmesini SAĞLA.
        when(tweetRepository.findById(100L)).thenReturn(Optional.of(tweet));

        // ACT (Eylem): Asıl test edeceğimiz metodu çağırıyoruz.
        // "owner_user" olarak, ID'si 100 olan tweet'i silmeyi deniyoruz.
        tweetService.delete(100L, "owner_user");

        // ASSERT (Doğrulama): Sonucun beklediğimiz gibi olup olmadığını kontrol ediyoruz.
        // Kural 2: tweetRepository'nin delete(tweet) metodunun
        // tam olarak 1 KEZ çağrıldığını DOĞRULA.
        verify(tweetRepository, times(1)).delete(tweet);
    }

    @Test
    void testDelete_whenUserIsNotOwner_shouldThrowAuthException() {
        // ARRANGE (Hazırlık):
        // tweetRepository'nin findById(100L) metodu çağrıldığında,
        // yine içi dolu bir Optional<Tweet> döndürmesini sağlıyoruz.
        when(tweetRepository.findById(100L)).thenReturn(Optional.of(tweet));

        // ACT & ASSERT (Eylem ve Doğrulama bir arada):
        // "non_owner_user" olarak, ID'si 100 olan tweet'i silmeye çalıştığımızda,
        // TwitterAuthException TİPİNDE bir hata fırlatılacağını BEKLE.
        assertThrows(TwitterAuthException.class, () -> {
            tweetService.delete(100L, "non_owner_user");
        });

        // Ek Doğrulama: Bu senaryoda, delete metodunun HİÇBİR ZAMAN
        // çağrılmadığını DOĞRULA. Çünkü yetkisi olmadığı için işlem engellenmeli.
        verify(tweetRepository, never()).delete(any(Tweet.class));
    }
}
