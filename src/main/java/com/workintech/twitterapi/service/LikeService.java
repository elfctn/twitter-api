package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;

public interface LikeService {
    Like save(Long userId, Long tweetId);
    void delete(Long userId, Long tweetId);
}