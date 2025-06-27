package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.CommentCreateDTO;
import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.entity.Tweet;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TweetService tweetService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, TweetService tweetService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @Override
    public Comment save(Long tweetId, String username, CommentCreateDTO commentCreateDTO) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));
        Tweet tweet = tweetService.getById(tweetId);

        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        comment.setUser(user);
        comment.setTweet(tweet);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getByTweetId(Long tweetId) {
        return commentRepository.findByTweetId(tweetId);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yorum bulunamadı: " + id));
    }

    @Override
    public Comment update(Long id, String username, CommentCreateDTO commentCreateDTO) {
        Comment commentToUpdate = getById(id);
        if (!commentToUpdate.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Yetkisiz işlem: Bu yorumu güncelleme yetkiniz yok.");
        }
        commentToUpdate.setContent(commentCreateDTO.getContent());
        return commentRepository.save(commentToUpdate);
    }

    @Override
    public void delete(Long id, String username) {
        Comment commentToDelete = getById(id);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + username));

        // Proje İsteri: "Yorumu sadece tweet sahibi veya yorum sahibi silebilmelidir."
        boolean isCommentOwner = commentToDelete.getUser().getUsername().equals(username);
        boolean isTweetOwner = commentToDelete.getTweet().getUser().getUsername().equals(username);

        if (!isCommentOwner && !isTweetOwner) {
            throw new RuntimeException("Yetkisiz işlem: Bu yorumu silme yetkiniz yok.");
        }

        commentRepository.delete(commentToDelete);
    }
}
