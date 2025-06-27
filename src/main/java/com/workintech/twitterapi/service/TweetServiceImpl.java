package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bu sınıfın bir Spring Bean'i olduğunu belirtir.
public class TweetServiceImpl implements TweetService { // TweetService arayüzünü uygular

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override // Bu metodun arayüzden geldiğini belirtir.
    public Tweet save(Tweet tweet) {
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
        // TODO: Global Exception Handling
        throw new RuntimeException("Tweet not found with id: " + id);
    }

    @Override
    public List<Tweet> getByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public Tweet update(Long id, Tweet tweet) {
        Tweet existingTweet = getById(id);
        existingTweet.setContent(tweet.getContent());
        return tweetRepository.save(existingTweet);
    }

    @Override
    public void delete(Long id) {
        // TODO: Add security check
        tweetRepository.deleteById(id);
    }
}