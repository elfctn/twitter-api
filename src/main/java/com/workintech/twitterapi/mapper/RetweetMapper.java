package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.entity.Retweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// UserMapper'ı kullanarak iç içe nesne dönüşümünü sağlıyoruz.
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RetweetMapper {

    // Hedef DTO'da "tweetId" alanı olduğu için bu eşleştirme doğru.
    @Mapping(source = "tweet.id", target = "tweetId")
    RetweetResponseDTO retweetToRetweetResponseDTO(Retweet retweet);
}