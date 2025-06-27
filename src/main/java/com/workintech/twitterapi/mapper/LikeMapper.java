package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.LikeResponseDTO;
import com.workintech.twitterapi.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    // MapStruct'a, entity'deki alanları DTO'daki alanlara nasıl
    // eşleştireceğini @Mapping anotasyonları ile söylüyoruz.
    @Mapping(source = "id", target = "likeId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "tweet.id", target = "tweetId")
    LikeResponseDTO likeToLikeResponseDTO(Like like);
}
