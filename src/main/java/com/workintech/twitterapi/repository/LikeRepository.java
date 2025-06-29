package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByTweetIdAndUserId(Long tweetId, Long userId); //like zaten var mı yok mu -kontrol- hızlı
    Optional<Like> findByTweetIdAndUserId(Long tweetId, Long userId); //twetid ve userid göre like ara

}


//ayrıca boolean;
// Likeı geri almak işlemi için silinecek olan o spesifik Like kaydını
// veritabanından bulmamı da sağlayacak








