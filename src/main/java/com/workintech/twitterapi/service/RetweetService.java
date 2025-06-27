package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;

public interface RetweetService {
    Retweet save(Long userId, Long tweetId);
    void delete(Long retweetId);
    int getRetweetCount(Long tweetId);
}