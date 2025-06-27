package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTweetId(Long tweetId);
}




//SQL sorgusu karşılığı: SELECT * FROM comment WHERE tweet_id = ?


//yazılan metod temel save, findById, delete gibi işlemleri
// jpa dan getiriyo direkt olarak
//tüm comment CRUD (Oluşturma, Güncelleme, Silme) endpointleri hepsi halloluyor
//hatta dolaylı yoldan tweet/findbyid(get) bile oluyor
// çünkü tüm bilgileri getirmek isteniyor