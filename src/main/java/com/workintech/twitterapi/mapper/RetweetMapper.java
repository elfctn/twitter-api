package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.entity.Retweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RetweetMapper {

    // MapStruct'a, entity'deki alanları DTO'daki alanlara nasıl
    // eşleştireceğini @Mapping anotasyonları ile söylüyoruz.
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "tweet.id", target = "tweetId")
    RetweetResponseDTO retweetToRetweetResponseDTO(Retweet retweet);
}
