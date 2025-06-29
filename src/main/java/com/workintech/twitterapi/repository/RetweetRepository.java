package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    boolean existsByTweetIdAndUserId(Long tweetId, Long userId);//daha önce rt yapılmış mı  tweetid ve userid ye göre kontrol et
    Optional<Retweet> findByTweetIdAndUserId(Long tweetId, Long userId);


}

//ayrıca boolean;
// Retweeti geri almak işlemi için silinecek olan o spesifik Retweet kaydını
// veritabanından bulmamı da sağlayacak