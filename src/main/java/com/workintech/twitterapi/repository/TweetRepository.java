package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUserId(Long userId);
}




// SQL sorgu karşılığı: SELECT * FROM tweet WHERE user_id = ???

///  tweet/findByUserId endpoint'ini karşılayacak