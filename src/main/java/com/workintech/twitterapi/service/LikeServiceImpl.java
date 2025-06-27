package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final TweetService tweetService;

    // LikeService artık hem kendi deposuna hem de diğer servislere bağımlı.
    // Spring, @Autowired sayesinde bunların hepsini otomatik olarak bize verecek.
    public LikeServiceImpl(LikeRepository likeRepository, UserService userService, TweetService tweetService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @Override
    public Like save(Long userId, Long tweetId) {
        // Görev: Bir kullanıcının bir tweet'i beğenmesini sağlar.

        // Önce bu kullanıcı ve tweet daha önce eşleşmiş mi diye kontrol et
        // Bu bir kullanıcının aynı tweet'i tekrar beğenmesini engeller.
        if (likeRepository.existsByTweetIdAndUserId(tweetId, userId)) {
            // TODO: Burası için özel bir "AlreadyLikedException" oluşturulacak.
            throw new RuntimeException("Bu tweet zaten bu kullanıcı tarafından beğenilmiş.");
        }

        // Like nesnesini oluşturabilmek için ilgili User ve Tweet nesnelerine ihtiyacımız var.
        // Diğer servisleri kullanarak bu nesneleri ID'leri ile buluyoruz.
        User user = userService.getById(userId); // UserService'i kullan
        Tweet tweet = tweetService.getById(tweetId); // TweetService'i kullan

        // Yeni Like nesnesini oluşturuyoruz.
        Like newLike = new Like();
        newLike.setUser(user);
        newLike.setTweet(tweet);

        // Ve son olarak veritabanına kaydediyoruz.
        return likeRepository.save(newLike);
    }

    @Override
    public void delete(Long userId, Long tweetId) {
        // Görev: Bir kullanıcının bir tweet'e attığı beğeniyi geri almasını sağlar.

        // Önce silinecek olan Like kaydını, kullanıcı ve tweet ID'lerine göre bul.
        // Repository'den Optional<Like> döndüğü için, null kontrolü daha güvenli yapılır.
        Optional<Like> optionalLike = likeRepository.findByTweetIdAndUserId(tweetId, userId);

        // Eğer böyle bir beğeni kaydı varsa, onu sil.
        if (optionalLike.isPresent()) {
            likeRepository.delete(optionalLike.get());
        } else {
            // Eğer böyle bir beğeni yoksa, hata fırlat.
            // TODO: Burası için "LikeNotFoundException" oluşturulacak.
            throw new RuntimeException("Beğeni kaydı bulunamadı.");
        }
    }
}