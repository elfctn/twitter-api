package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.LikeResponseDTO;
import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-28T01:10:15+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class LikeMapperImpl implements LikeMapper {

    @Override
    public LikeResponseDTO likeToLikeResponseDTO(Like like) {
        if ( like == null ) {
            return null;
        }

        LikeResponseDTO likeResponseDTO = new LikeResponseDTO();

        likeResponseDTO.setLikeId( like.getId() );
        likeResponseDTO.setUserId( likeUserId( like ) );
        likeResponseDTO.setTweetId( likeTweetId( like ) );

        return likeResponseDTO;
    }

    private Long likeUserId(Like like) {
        if ( like == null ) {
            return null;
        }
        User user = like.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long likeTweetId(Like like) {
        if ( like == null ) {
            return null;
        }
        Tweet tweet = like.getTweet();
        if ( tweet == null ) {
            return null;
        }
        Long id = tweet.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
