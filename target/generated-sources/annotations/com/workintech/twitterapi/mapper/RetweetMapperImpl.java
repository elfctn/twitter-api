package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-28T19:02:43+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class RetweetMapperImpl implements RetweetMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RetweetResponseDTO retweetToRetweetResponseDTO(Retweet retweet) {
        if ( retweet == null ) {
            return null;
        }

        RetweetResponseDTO retweetResponseDTO = new RetweetResponseDTO();

        retweetResponseDTO.setTweetId( retweetTweetId( retweet ) );
        retweetResponseDTO.setId( retweet.getId() );
        retweetResponseDTO.setUser( userMapper.userToUserSummaryDTO( retweet.getUser() ) );
        retweetResponseDTO.setCreatedAt( retweet.getCreatedAt() );

        return retweetResponseDTO;
    }

    private Long retweetTweetId(Retweet retweet) {
        if ( retweet == null ) {
            return null;
        }
        Tweet tweet = retweet.getTweet();
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
