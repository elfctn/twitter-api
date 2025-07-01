package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.RetweetNotFoundException;
import com.workintech.twitterapi.exception.TwitterAuthException;
import com.workintech.twitterapi.exception.TwitterConflictException;
import com.workintech.twitterapi.exception.UserNotFoundException;
import com.workintech.twitterapi.repository.RetweetRepository;
import org.springframework.stereotype.Service;

@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final UserService userService;
    private final TweetService tweetService;

    public RetweetServiceImpl(RetweetRepository retweetRepository, UserService userService, TweetService tweetService) {
        this.retweetRepository = retweetRepository;
        this.userService = userService;
        this.tweetService = tweetService;
    }



    @Override
    public Retweet save(String username, Long tweetId) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));
        Tweet tweet = tweetService.getById(tweetId);
        //   kullanıcı aynı tweeti tekrar retweetleyemez.
        if (retweetRepository.existsByTweetIdAndUserId(tweetId, user.getId())) {
            throw new TwitterConflictException("Bu tweet zaten bu kullanıcı tarafından retweet edilmiş.");
        }
        Retweet newRetweet = new Retweet();
        newRetweet.setUser(user);
        newRetweet.setTweet(tweet);
        return retweetRepository.save(newRetweet);
    }



    
    @Override
    public void delete(Long retweetId, String username) {
        Retweet retweetToDelete = retweetRepository.findById(retweetId)
                .orElseThrow(() -> new RetweetNotFoundException("Retweet kaydı bulunamadı."));
        //  Retweeti sadece onu oluşturan kullanıcı silebilir.
        if (!retweetToDelete.getUser().getUsername().equals(username)) {
            throw new TwitterAuthException("Yetkisiz işlem: Bu retweet'i silme yetkiniz yok.");
        }
        retweetRepository.delete(retweetToDelete);
    }





    @Override
    public int getRetweetCount(Long tweetId) {
        Tweet tweet = tweetService.getById(tweetId);
        return tweet.getRetweets().size();
    }
}
