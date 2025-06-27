package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet save(TweetCreateDTO tweetCreateDTO, String username);
    List<Tweet> getAll();
    Tweet getById(Long id);
    List<Tweet> getByUserId(Long userId);

    // Güncelleme işlemi için 'username' parametresi eklendi.
    Tweet update(Long id, TweetCreateDTO tweetCreateDTO, String username);

    void delete(Long id, String username);
}
