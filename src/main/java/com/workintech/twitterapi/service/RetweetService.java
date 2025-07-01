package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;

public interface RetweetService {

    Retweet save(String username, Long tweetId);
    void delete(Long retweetId, String username);
    int getRetweetCount(Long tweetId);
}
