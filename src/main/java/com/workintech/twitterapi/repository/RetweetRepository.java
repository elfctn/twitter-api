package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    boolean existsByTweetIdAndUserId(Long tweetId, Long userId);
    Optional<Retweet> findByTweetIdAndUserId(Long tweetId, Long userId);


}


