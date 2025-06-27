package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;

public interface RetweetService {
    // İşlemi yapan kullanıcı adı ve retweetlenecek tweet ID'sini alarak kaydeder.
    Retweet save(String username, Long tweetId);

    // Silinecek retweet'in ID'sini ve işlemi yapan kullanıcının adını alarak siler.
    void delete(Long retweetId, String username);

    // Bir tweet'in retweet sayısını döndürür.
    int getRetweetCount(Long tweetId);
}
