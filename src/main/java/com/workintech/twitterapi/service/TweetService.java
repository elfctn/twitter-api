package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.TweetCreateDTO;
import com.workintech.twitterapi.entity.Tweet;

import java.util.List;

public interface TweetService {
    // TweetCreateDTO ve kullanıcı adını alıp, yeni bir Tweet oluşturur.
    Tweet save(TweetCreateDTO tweetCreateDTO, String username);

    List<Tweet> getAll();
    Tweet getById(Long id);
    List<Tweet> getByUserId(Long userId);

    // TweetCreateDTO ve güncellenecek tweet'in ID'sini alarak güncelleme yapar.
    Tweet update(Long id, TweetCreateDTO tweetCreateDTO);

    // Silinecek tweet'in ID'sini ve işlemi yapan kullanıcının adını alarak silme işlemi yapar.
    void delete(Long id, String username);
}
