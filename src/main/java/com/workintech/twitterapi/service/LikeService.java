package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;

public interface LikeService {

    Like save(String username, Long tweetId);
    void delete(String username, Long tweetId);
    int getLikeCount(Long tweetId);
}
