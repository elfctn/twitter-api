package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;

public interface LikeService {

    Like save(String username, Long tweetId); // İşlemi yapan kullanıcı adı ve beğenilecek tweet ID'sini alarak kaydeder.
    void delete(String username, Long tweetId); // İşlemi yapan kullanıcı adı ve beğenisi geri alınacak tweet ID'sini alarak siler.
    int getLikeCount(Long tweetId);// Bir tweet'in beğeni sayısını döndürür.
}
