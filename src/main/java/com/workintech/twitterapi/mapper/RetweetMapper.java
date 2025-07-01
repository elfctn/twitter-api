package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.entity.Retweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RetweetMapper {

    @Mapping(source = "tweet.id", target = "tweetId")
    RetweetResponseDTO retweetToRetweetResponseDTO(Retweet retweet);
}