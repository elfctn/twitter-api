package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-28T01:10:15+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TweetMapperImpl implements TweetMapper {

    @Override
    public TweetResponseDTO tweetToTweetResponseDTO(Tweet tweet) {
        if ( tweet == null ) {
            return null;
        }

        TweetResponseDTO tweetResponseDTO = new TweetResponseDTO();

        tweetResponseDTO.setId( tweet.getId() );
        tweetResponseDTO.setContent( tweet.getContent() );
        tweetResponseDTO.setCreatedAt( tweet.getCreatedAt() );
        tweetResponseDTO.setUser( userToUserSummaryDTO( tweet.getUser() ) );

        return tweetResponseDTO;
    }

    @Override
    public List<TweetResponseDTO> tweetListToTweetResponseDTOList(List<Tweet> tweets) {
        if ( tweets == null ) {
            return null;
        }

        List<TweetResponseDTO> list = new ArrayList<TweetResponseDTO>( tweets.size() );
        for ( Tweet tweet : tweets ) {
            list.add( tweetToTweetResponseDTO( tweet ) );
        }

        return list;
    }

    protected TweetResponseDTO.UserSummaryDTO userToUserSummaryDTO(User user) {
        if ( user == null ) {
            return null;
        }

        TweetResponseDTO.UserSummaryDTO userSummaryDTO = new TweetResponseDTO.UserSummaryDTO();

        userSummaryDTO.setId( user.getId() );
        userSummaryDTO.setUsername( user.getUsername() );

        return userSummaryDTO;
    }
}
