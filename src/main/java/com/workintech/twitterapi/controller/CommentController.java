package com.workintech.twitterapi.controller;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.dto.CommentResponseDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Bir tweete yeni bir yorum ekler.
    @PostMapping("/{tweetId}")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long tweetId,
                                                            @RequestBody CommentCreateDTO commentDTO,
                                                            Authentication authentication) {
        String username = authentication.getName();
        Comment savedComment = commentService.save(tweetId, username, commentDTO);
        return new ResponseEntity<>(convertToDTO(savedComment), HttpStatus.CREATED);
    }

    // Bir tweete ait tüm yorumları listeler.
    @GetMapping("/tweet/{tweetId}")
    public List<CommentResponseDTO> getCommentsByTweetId(@PathVariable Long tweetId) {
        List<Comment> comments = commentService.getByTweetId(tweetId);
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Mevcut bir yorumu günceller.
    @PutMapping("/{id}")
    public CommentResponseDTO updateComment(@PathVariable Long id,
                                            @RequestBody CommentCreateDTO commentDTO,
                                            Authentication authentication) {
        String username = authentication.getName();
        Comment updatedComment = commentService.update(id, username, commentDTO);
        return convertToDTO(updatedComment);
    }

    // Bir yorumu siler.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        commentService.delete(id, username);
        return ResponseEntity.noContent().build();
    }


    // Entity'yi Response DTO'ya çeviren yardımcı metot.
    private CommentResponseDTO convertToDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setTweetId(comment.getTweet().getId());
        dto.setUser(new CommentResponseDTO.UserSummaryDTO(
                comment.getUser().getId(),
                comment.getUser().getUsername())
        );
        return dto;
    }
}
