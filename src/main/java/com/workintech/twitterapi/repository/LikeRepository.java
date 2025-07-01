package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByTweetIdAndUserId(Long tweetId, Long userId);
    Optional<Like> findByTweetIdAndUserId(Long tweetId, Long userId);

}








