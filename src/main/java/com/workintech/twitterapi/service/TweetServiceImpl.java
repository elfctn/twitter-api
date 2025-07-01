package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.TweetNotFoundException;
import com.workintech.twitterapi.exception.TwitterAuthException;
import com.workintech.twitterapi.exception.UserNotFoundException;
import com.workintech.twitterapi.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;

    public TweetServiceImpl(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }



    @Override
    public Tweet save(TweetCreateDTO tweetCreateDTO, String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));
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
        return tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + id));
    }




    @Override
    public List<Tweet> getByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }



    @Override
    public Tweet update(Long id, TweetCreateDTO tweetCreateDTO, String username) {
        Tweet existingTweet = getById(id);
        // Tweeti sadece sahibi güncelleyebilir.
        if(!existingTweet.getUser().getUsername().equals(username)){
            throw new TwitterAuthException("Yetkisiz işlem: Bu tweet'i güncelleme yetkiniz yok.");
        }
        existingTweet.setContent(tweetCreateDTO.getContent());
        return tweetRepository.save(existingTweet);
    }




    @Override
    public void delete(Long id, String username) {
        Tweet tweetToDelete = getById(id);
        //  Tweeti sadece sahibi silebilir.
        if (!tweetToDelete.getUser().getUsername().equals(username)) {
            throw new TwitterAuthException("Yetkisiz işlem: Bu tweet'i silme yetkiniz yok.");
        }
        tweetRepository.delete(tweetToDelete);
    }

}
