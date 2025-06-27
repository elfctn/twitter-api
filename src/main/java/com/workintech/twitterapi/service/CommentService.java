package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    List<Comment> getByTweetId(Long tweetId);
    Comment getById(Long id);
    Comment update(Long id, Comment comment);
    void delete(Long id);
}