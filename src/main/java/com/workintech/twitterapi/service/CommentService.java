package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Long tweetId, String username, CommentCreateDTO commentCreateDTO);
    List<Comment> getByTweetId(Long tweetId);
    Comment getById(Long id);
    Comment update(Long id, String username, CommentCreateDTO commentCreateDTO);
    void delete(Long id, String username);
}
