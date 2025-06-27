package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet save(Tweet tweet);
    List<Tweet> getAll();
    Tweet getById(Long id);
    List<Tweet> getByUserId(Long userId);
    Tweet update(Long id, Tweet tweet);
    void delete(Long id);
}

//sadece metıd imzalarım var gövdeler yok