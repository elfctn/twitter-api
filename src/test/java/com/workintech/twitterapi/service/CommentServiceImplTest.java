package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.CommentNotFoundException;
import com.workintech.twitterapi.exception.TwitterAuthException;
import com.workintech.twitterapi.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserService userService;

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private CommentServiceImpl commentService;

    private User tweetOwner;
    private User commentOwner;
    private User otherUser;
    private Tweet tweet;
    private Comment comment;

    @BeforeEach
    void setUp() {
        // Tweet'in sahibini oluşturuyoruz.
        tweetOwner = new User();
        tweetOwner.setId(1L);
        tweetOwner.setUsername("tweet_owner");

        // Yorumun sahibini oluşturuyoruz.
        commentOwner = new User();
        commentOwner.setId(2L);
        commentOwner.setUsername("comment_owner");

        // Hiçbir şeyin sahibi olmayan üçüncü bir kullanıcı oluşturuyoruz.
        otherUser = new User();
        otherUser.setId(3L);
        otherUser.setUsername("other_user");

        // Test edilecek tweet'i oluşturup sahibini atıyoruz.
        tweet = new Tweet();
        tweet.setId(100L);
        tweet.setUser(tweetOwner);

        // Test edilecek yorumu oluşturup sahibini ve ait olduğu tweet'i atıyoruz.
        comment = new Comment();
        comment.setId(500L);
        comment.setUser(commentOwner);
        comment.setTweet(tweet);
    }

    @Test
    @DisplayName("Yeni bir yorum kaydedildiğinde başarılı olmalı")
    void testSave_shouldCreateAndReturnComment() {
        // ARRANGE
        CommentCreateDTO createDTO = new CommentCreateDTO();
        createDTO.setContent("Yeni yorum içeriği");

        when(userService.findByUsername("comment_owner")).thenReturn(Optional.of(commentOwner));
        when(tweetService.getById(100L)).thenReturn(tweet);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // ACT
        Comment savedComment = commentService.save(100L, "comment_owner", createDTO);

        // ASSERT
        assertNotNull(savedComment);
        assertEquals("comment_owner", savedComment.getUser().getUsername());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }


    @Test
    @DisplayName("Var olan bir yorumu güncellerken başarılı olmalı")
    void testUpdate_whenUserIsOwner_shouldUpdateComment() {
        // ARRANGE
        CommentCreateDTO updateDTO = new CommentCreateDTO();
        updateDTO.setContent("Güncellenmiş içerik");

        when(commentRepository.findById(500L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // ACT
        Comment updatedComment = commentService.update(500L, "comment_owner", updateDTO);

        // ASSERT
        assertNotNull(updatedComment);
        assertEquals("Güncellenmiş içerik", updatedComment.getContent());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    @DisplayName("Yorum sahibi olmayan bir kullanıcı yorumu güncelleyememeli")
    void testUpdate_whenUserIsNotOwner_shouldThrowAuthException() {
        // ARRANGE
        CommentCreateDTO updateDTO = new CommentCreateDTO();
        updateDTO.setContent("Yetkisiz güncelleme denemesi");
        when(commentRepository.findById(500L)).thenReturn(Optional.of(comment));

        // ACT & ASSERT
        assertThrows(TwitterAuthException.class, () -> {
            commentService.update(500L, "other_user", updateDTO);
        });
        verify(commentRepository, never()).save(any(Comment.class));
    }


    @Test
    @DisplayName("Yorum sahibi kendi yorumunu silebilir")
    void testDelete_whenUserIsCommentOwner_shouldDeleteComment() {
        // ARRANGE
        when(commentRepository.findById(500L)).thenReturn(Optional.of(comment));
        when(userService.findByUsername("comment_owner")).thenReturn(Optional.of(commentOwner));

        // ACT
        commentService.delete(500L, "comment_owner");

        // ASSERT
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    @DisplayName("Tweet sahibi başkasının yorumunu silebilir")
    void testDelete_whenUserIsTweetOwner_shouldDeleteComment() {
        // ARRANGE
        when(commentRepository.findById(500L)).thenReturn(Optional.of(comment));
        when(userService.findByUsername("tweet_owner")).thenReturn(Optional.of(tweetOwner));

        // ACT
        commentService.delete(500L, "tweet_owner");

        // ASSERT
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    @DisplayName("Yetkisi olmayan bir kullanıcı yorumu silemez")
    void testDelete_whenUserIsNeitherOwner_shouldThrowAuthException() {
        // ARRANGE
        when(commentRepository.findById(500L)).thenReturn(Optional.of(comment));
        when(userService.findByUsername("other_user")).thenReturn(Optional.of(otherUser));

        // ACT & ASSERT
        assertThrows(TwitterAuthException.class, () -> {
            commentService.delete(500L, "other_user");
        });

        verify(commentRepository, never()).delete(any(Comment.class));
    }
}
