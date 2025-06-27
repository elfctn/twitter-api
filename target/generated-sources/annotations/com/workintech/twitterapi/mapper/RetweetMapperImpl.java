package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.entity.Retweet;
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
public class RetweetMapperImpl implements RetweetMapper {

    @Override
    public RetweetResponseDTO retweetToRetweetResponseDTO(Retweet retweet) {
        if ( retweet == null ) {
            return null;
        }

        RetweetResponseDTO retweetResponseDTO = new RetweetResponseDTO();

        retweetResponseDTO.setUserId( retweetUserId( retweet ) );
        retweetResponseDTO.setTweetId( retweetTweetId( retweet ) );
        retweetResponseDTO.setId( retweet.getId() );
        retweetResponseDTO.setCreatedAt( retweet.getCreatedAt() );

        return retweetResponseDTO;
    }

    private Long retweetUserId(Retweet retweet) {
        if ( retweet == null ) {
            return null;
        }
        User user = retweet.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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
