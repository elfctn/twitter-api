package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    // MapStruct'a, Comment entity'sindeki 'tweet.id' alanını,
    // CommentResponseDTO'daki 'tweetId' alanına eşlemesini söylüyoruz.
    @Mapping(source = "tweet.id", target = "tweetId")
    CommentResponseDTO commentToCommentResponseDTO(Comment comment);

    List<CommentResponseDTO> commentListToCommentResponseDTOList(List<Comment> comments);
}
