package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.mapper.CommentMapper; // Mapper'ı import ediyoruz
import com.workintech.twitterapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper; // Mapper'ı enjekte ediyoruz

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    // Bir tweete yeni bir yorum ekler.
    @PostMapping("/{tweetId}")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long tweetId,
                                                            @RequestBody CommentCreateDTO commentDTO,
                                                            Authentication authentication) {
        String username = authentication.getName();
        Comment savedComment = commentService.save(tweetId, username, commentDTO);
        // Artık manuel `convertToDTO` yerine, otomatik `commentMapper`'ı kullanıyoruz.
        return new ResponseEntity<>(commentMapper.commentToCommentResponseDTO(savedComment), HttpStatus.CREATED);
    }

    // Bir tweete ait tüm yorumları listeler.
    @GetMapping("/tweet/{tweetId}")
    public List<CommentResponseDTO> getCommentsByTweetId(@PathVariable Long tweetId) {
        List<Comment> comments = commentService.getByTweetId(tweetId);
        // Liste dönüşümü için de mapper'ımızı kullanıyoruz.
        return commentMapper.commentListToCommentResponseDTOList(comments);
    }

    // Mevcut bir yorumu günceller.
    @PutMapping("/{id}")
    public CommentResponseDTO updateComment(@PathVariable Long id,
                                            @RequestBody CommentCreateDTO commentDTO,
                                            Authentication authentication) {
        String username = authentication.getName();
        Comment updatedComment = commentService.update(id, username, commentDTO);
        return commentMapper.commentToCommentResponseDTO(updatedComment);
    }

    // Bir yorumu siler.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        commentService.delete(id, username);
        return ResponseEntity.noContent().build();
    }
}
