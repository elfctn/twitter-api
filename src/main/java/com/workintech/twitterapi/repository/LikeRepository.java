package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByTweetIdAndUserId(Long tweetId, Long userId);

    boolean existsByTweetIdAndUserId(Long tweetId, Long userId);
}


// Like-dislike olayları--//NullPointerException riskine karşı optional

//like kaydı eklendi bir de kullanıcı tweete daha önce likelamış mı
// bunun kontrolü için boolean özel sorgu verildi
//service katmanında kontrolü yapacağım



