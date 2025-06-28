package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-28T17:11:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentResponseDTO commentToCommentResponseDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();

        commentResponseDTO.setTweetId( commentTweetId( comment ) );
        commentResponseDTO.setId( comment.getId() );
        commentResponseDTO.setContent( comment.getContent() );
        commentResponseDTO.setCreatedAt( comment.getCreatedAt() );
        commentResponseDTO.setUser( userToUserSummaryDTO( comment.getUser() ) );

        return commentResponseDTO;
    }

    @Override
    public List<CommentResponseDTO> commentListToCommentResponseDTOList(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponseDTO> list = new ArrayList<CommentResponseDTO>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentResponseDTO( comment ) );
        }

        return list;
    }

    private Long commentTweetId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Tweet tweet = comment.getTweet();
        if ( tweet == null ) {
            return null;
        }
        Long id = tweet.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected CommentResponseDTO.UserSummaryDTO userToUserSummaryDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;

        id = user.getId();
        username = user.getUsername();

        CommentResponseDTO.UserSummaryDTO userSummaryDTO = new CommentResponseDTO.UserSummaryDTO( id, username );

        return userSummaryDTO;
    }
}
