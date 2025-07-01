package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.LikeResponseDTO;
import com.workintech.twitterapi.dto.RetweetResponseDTO;
import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.dto.UserSummaryDTO;
import com.workintech.twitterapi.entity.Like;
import com.workintech.twitterapi.entity.Retweet;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-01T17:24:11+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TweetMapperImpl implements TweetMapper {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private RetweetMapper retweetMapper;

    @Override
    public TweetResponseDTO tweetToTweetResponseDTO(Tweet tweet) {
        if ( tweet == null ) {
            return null;
        }

        TweetResponseDTO tweetResponseDTO = new TweetResponseDTO();

        tweetResponseDTO.setId( tweet.getId() );
        tweetResponseDTO.setContent( tweet.getContent() );
        tweetResponseDTO.setCreatedAt( tweet.getCreatedAt() );
        tweetResponseDTO.setUpdatedAt( tweet.getUpdatedAt() );
        tweetResponseDTO.setUser( userToUserSummaryDTO( tweet.getUser() ) );
        tweetResponseDTO.setComments( commentMapper.commentListToCommentResponseDTOList( tweet.getComments() ) );
        tweetResponseDTO.setLikes( likeListToLikeResponseDTOList( tweet.getLikes() ) );
        tweetResponseDTO.setRetweets( retweetListToRetweetResponseDTOList( tweet.getRetweets() ) );

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

    protected UserSummaryDTO userToUserSummaryDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserSummaryDTO userSummaryDTO = new UserSummaryDTO();

        userSummaryDTO.setId( user.getId() );
        userSummaryDTO.setUsername( user.getUsername() );

        return userSummaryDTO;
    }

    protected List<LikeResponseDTO> likeListToLikeResponseDTOList(List<Like> list) {
        if ( list == null ) {
            return null;
        }

        List<LikeResponseDTO> list1 = new ArrayList<LikeResponseDTO>( list.size() );
        for ( Like like : list ) {
            list1.add( likeMapper.likeToLikeResponseDTO( like ) );
        }

        return list1;
    }

    protected List<RetweetResponseDTO> retweetListToRetweetResponseDTOList(List<Retweet> list) {
        if ( list == null ) {
            return null;
        }

        List<RetweetResponseDTO> list1 = new ArrayList<RetweetResponseDTO>( list.size() );
        for ( Retweet retweet : list ) {
            list1.add( retweetMapper.retweetToRetweetResponseDTO( retweet ) );
        }

        return list1;
    }
}
