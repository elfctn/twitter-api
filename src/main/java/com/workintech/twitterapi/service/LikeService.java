package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Like;

public interface LikeService {
    // İşlemi yapan kullanıcı adı ve beğenilecek tweet ID'sini alarak kaydeder.
    Like save(String username, Long tweetId);

    // İşlemi yapan kullanıcı adı ve beğenisi geri alınacak tweet ID'sini alarak siler.
    void delete(String username, Long tweetId);

    // Bir tweet'in beğeni sayısını döndürür.
    int getLikeCount(Long tweetId);
}
