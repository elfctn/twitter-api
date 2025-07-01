package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.entity.Tweet;
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
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private UserMapper userMapper;

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
        commentResponseDTO.setUser( userMapper.userToUserSummaryDTO( comment.getUser() ) );

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
}
