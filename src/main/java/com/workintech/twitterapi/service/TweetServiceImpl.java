package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService; // Diğer servislere bağımlılık

    public TweetServiceImpl(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }

    @Override
    public Tweet save(TweetCreateDTO tweetCreateDTO, String username) {
        // İş Mantığı: Controller'dan alınan kullanıcı adıyla ilgili User nesnesini bul.
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));

        // İş Mantığı: Yeni bir Tweet nesnesi yarat ve verileri set et.
        Tweet tweet = new Tweet();
        tweet.setContent(tweetCreateDTO.getContent());
        tweet.setUser(user);
        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet getById(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if(optionalTweet.isPresent()){
            return optionalTweet.get();
        }
        throw new RuntimeException("Tweet not found with id: " + id);
    }

    @Override
    public List<Tweet> getByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public Tweet update(Long id, TweetCreateDTO tweetCreateDTO) {
        Tweet existingTweet = getById(id);

        // TODO: Güvenlik kontrolü - Bu tweet'i güncellemeye yetkisi var mı?
        // Bu kontrol, Spring Security eklendikten sonra eklenebilir.
        // Örnek: if(!existingTweet.getUser().getUsername().equals(authentication.getName())){...}

        existingTweet.setContent(tweetCreateDTO.getContent());
        return tweetRepository.save(existingTweet);
    }

    @Override
    public void delete(Long id, String username) {
        Tweet tweetToDelete = getById(id);

        // Güvenlik İş Kuralı: Proje isterlerindeki en önemli kurallardan biri.
        // Silinmek istenen tweet'in sahibi, işlemi yapan kullanıcı mı?
        if (!tweetToDelete.getUser().getUsername().equals(username)) {
            // TODO: Burası için özel bir "UnauthorizedAccessException" oluşturulacak.
            throw new RuntimeException("Yetkisiz işlem: Bu tweet'i silme yetkiniz yok.");
        }
        tweetRepository.delete(tweetToDelete);
    }
}
