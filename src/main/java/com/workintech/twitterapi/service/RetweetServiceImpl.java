package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.RetweetRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final UserService userService;
    private final TweetService tweetService;

    @Override
    public Retweet save(String username, Long tweetId) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));
        Tweet tweet = tweetService.getById(tweetId);

        if (retweetRepository.existsByTweetIdAndUserId(tweetId, user.getId())) {
            throw new RuntimeException("Bu tweet zaten bu kullanıcı tarafından retweet edilmiş.");
        }

        Retweet newRetweet = new Retweet();
        newRetweet.setUser(user);
        newRetweet.setTweet(tweet);
        return retweetRepository.save(newRetweet);
    }

    @Override
    public void delete(Long retweetId, String username) {
        Retweet retweetToDelete = retweetRepository.findById(retweetId)
                .orElseThrow(() -> new RuntimeException("Retweet kaydı bulunamadı."));

        // Güvenlik Kuralı: Retweet'i sadece onu oluşturan kullanıcı silebilir.
        if (!retweetToDelete.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Yetkisiz işlem: Bu retweet'i silme yetkiniz yok.");
        }

        retweetRepository.delete(retweetToDelete);
    }

    @Override
    public int getRetweetCount(Long tweetId) {
        // Bu metot, tweet'in retweet listesinin boyutunu döndürmek yerine,
        // direkt repository üzerinden sayım yaparak daha verimli hale getirilebilir.
        // Şimdilik entity üzerinden sayım yapıyoruz.
        Tweet tweet = tweetService.getById(tweetId);
        return tweet.getRetweets().size();
    }
}
