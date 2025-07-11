package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.LikeNotFoundException;
import com.workintech.twitterapi.exception.TwitterConflictException;
import com.workintech.twitterapi.exception.UserNotFoundException;
import com.workintech.twitterapi.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final TweetService tweetService;


    public LikeServiceImpl(LikeRepository likeRepository, UserService userService, TweetService tweetService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.tweetService = tweetService;
    }



    @Override
    public Like save(String username, Long tweetId) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));
        Tweet tweet = tweetService.getById(tweetId);
        // Bir kullanıcı aynı tweeti tekrar beğenemez.
        if (likeRepository.existsByTweetIdAndUserId(tweetId, user.getId())) {
            throw new TwitterConflictException("Bu tweet zaten bu kullanıcı tarafından beğenilmiş.");
        }
        Like newLike = new Like();
        newLike.setUser(user);
        newLike.setTweet(tweet);
        return likeRepository.save(newLike);
    }



    @Override
    public void delete(String username, Long tweetId) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + username));
        Like likeToDelete = likeRepository.findByTweetIdAndUserId(tweetId, user.getId())
                .orElseThrow(() -> new LikeNotFoundException("Beğeni kaydı bulunamadı."));
        likeRepository.delete(likeToDelete);
    }



    @Override
    public int getLikeCount(Long tweetId) {
        Tweet tweet = tweetService.getById(tweetId);
        return tweet.getLikes().size();
    }
}
