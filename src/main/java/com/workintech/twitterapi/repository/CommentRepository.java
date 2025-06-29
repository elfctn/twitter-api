package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTweetId(Long tweetId); // tweetid ye göre ara yorumları listele
}








//SQL sorgusu karşılığı: SELECT * FROM comment WHERE tweet_id = ?


//hatta dolaylı yoldan tweet/findbyid(get) endpointini bile karşılıyor
