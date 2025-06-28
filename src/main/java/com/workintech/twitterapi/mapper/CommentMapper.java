package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// ÇÖZÜM: 'uses' parametresi ile UserMapper'ı kullanmasını söylüyoruz.
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {

    @Mapping(source = "tweet.id", target = "tweetId")
    CommentResponseDTO commentToCommentResponseDTO(Comment comment);

    List<CommentResponseDTO> commentListToCommentResponseDTOList(List<Comment> comments);
}
