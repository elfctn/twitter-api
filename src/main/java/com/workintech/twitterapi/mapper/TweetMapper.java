package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.entity.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, LikeMapper.class, RetweetMapper.class})
public interface TweetMapper {

    TweetResponseDTO tweetToTweetResponseDTO(Tweet tweet);
    List<TweetResponseDTO> tweetListToTweetResponseDTOList(List<Tweet> tweets);
}
