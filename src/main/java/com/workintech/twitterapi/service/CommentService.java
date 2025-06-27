package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.entity.Comment;

import java.util.List;

public interface CommentService {
    // Yorumu hangi tweete ve kimin attığı bilgisini alarak kaydeder.
    Comment save(Long tweetId, String username, CommentCreateDTO commentCreateDTO);
    List<Comment> getByTweetId(Long tweetId);
    Comment getById(Long id);
    // Yorumu kimin güncellemeye çalıştığı bilgisini alarak güncelleme yapar.
    Comment update(Long id, String username, CommentCreateDTO commentCreateDTO);
    // Yorumu kimin silmeye çalıştığı bilgisini alarak silme işlemi yapar.
    void delete(Long id, String username);
}
