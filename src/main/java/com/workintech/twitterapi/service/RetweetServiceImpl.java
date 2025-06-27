package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.RetweetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final UserService userService;
    private final TweetService tweetService;

    // RetweetService'in de çalışmak için kendi deposuna ve diğer servislere ihtiyacı var.
    // Constructor Injection ile Spring bu bağımlılıkları bizim için otomatik olarak bağlıyor.
    public RetweetServiceImpl(RetweetRepository retweetRepository, UserService userService, TweetService tweetService) {
        this.retweetRepository = retweetRepository;
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @Override
    public Retweet save(Long userId, Long tweetId) {
        // Görev: Bir kullanıcının bir tweet'i retweetlemesini sağlar.

        // Kural: Bir kullanıcı aynı tweet'i tekrar retweetleyemez.
        if (retweetRepository.existsByTweetIdAndUserId(tweetId, userId)) {
            // TODO: Burası için özel bir "AlreadyRetweetedException" oluşturulaca
            throw new RuntimeException("Bu tweet zaten bu kullanıcı tarafından retweet edilmiş.");
        }

        // Retweet nesnesini oluşturmak için ilgili User ve Tweet nesnelerini buluyoruz.
        User user = userService.getById(userId);
        Tweet tweet = tweetService.getById(tweetId);

        // Yeni Retweet nesnesini oluşturup kaydet.
        Retweet newRetweet = new Retweet();
        newRetweet.setUser(user);
        newRetweet.setTweet(tweet);
        return retweetRepository.save(newRetweet);
    }

    @Override
    public void delete(Long retweetId) {
        // Görev: IDsi verilen bir retweet kaydını sil.
        // Bu metot, proje isterlerindeki DELETE /retweet/:id endpointkarşılar.
        // TODO: Güvenlik kontrolü (sadece retweet sahibi silebilir) eklenecek.
        retweetRepository.deleteById(retweetId);
    }

    @Override
    public int getRetweetCount(Long tweetId) {
        // Görev: Bir tweet'in toplam retweet sayısını döndürür.
        Tweet tweet = tweetService.getById(tweetId);
        // JPA'nın ilişki yönetimi sayesinde, tweet nesnesinin içindeki retweets listesinin
        // boyutunu alarak bu sayıyı kolayca bulabiliriz.
        return tweet.getRetweets().size();
    }
}