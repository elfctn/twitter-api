package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.UserResponseDTO;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO userToUserResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setUsername( user.getUsername() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setTweets( tweetListToTweetSummaryDTOList( user.getTweets() ) );

        return userResponseDTO;
    }

    protected UserResponseDTO.TweetSummaryDTO tweetToTweetSummaryDTO(Tweet tweet) {
        if ( tweet == null ) {
            return null;
        }

        UserResponseDTO.TweetSummaryDTO tweetSummaryDTO = new UserResponseDTO.TweetSummaryDTO();

        tweetSummaryDTO.setId( tweet.getId() );
        tweetSummaryDTO.setContent( tweet.getContent() );

        return tweetSummaryDTO;
    }

    protected List<UserResponseDTO.TweetSummaryDTO> tweetListToTweetSummaryDTOList(List<Tweet> list) {
        if ( list == null ) {
            return null;
        }

        List<UserResponseDTO.TweetSummaryDTO> list1 = new ArrayList<UserResponseDTO.TweetSummaryDTO>( list.size() );
        for ( Tweet tweet : list ) {
            list1.add( tweetToTweetSummaryDTO( tweet ) );
        }

        return list1;
    }
}
