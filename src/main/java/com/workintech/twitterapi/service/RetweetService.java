package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Retweet;

public interface RetweetService {

    Retweet save(String username, Long tweetId);// İşlemi yapan kullanıcı adı ve retweetlenecek tweet ID'sini alarak kaydeder.
    void delete(Long retweetId, String username); // Silinecek retweet'in ID'sini ve işlemi yapan kullanıcının adını alarak siler.
    int getRetweetCount(Long tweetId);// Bir tweet'in retweet sayısını döndürür.
}
