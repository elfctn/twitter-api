package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.entity.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    // MapStruct, bu metot imzasına bakarak Tweet'in içindeki alanları
    // TweetResponseDTO'daki aynı isimli alanlara otomatik olarak eşleştirecektir.
    // Özellikle 'user' gibi iç içe nesneler için de akıllıca davranır.
    TweetResponseDTO tweetToTweetResponseDTO(Tweet tweet);

    // Bir liste dönüşümü için de kolayca bir metot ekleyebiliriz.
    List<TweetResponseDTO> tweetListToTweetResponseDTOList(List<Tweet> tweets);
}
