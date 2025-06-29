package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Long tweetId, String username, CommentCreateDTO commentCreateDTO);// Yorumu hangi tweete kimin attı - al ve kydet
    List<Comment> getByTweetId(Long tweetId);

    Comment getById(Long id);// Yorumu kimin güncellemeye çalıştı -al ve güncelle
    Comment update(Long id, String username, CommentCreateDTO commentCreateDTO);

    void delete(Long id, String username);// Yorumu kimin silmeye çalıştığı bilgisini al ve sil
}
