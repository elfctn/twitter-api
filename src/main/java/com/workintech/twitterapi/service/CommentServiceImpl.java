package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.Comment;
import com.workintech.twitterapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;//final: servis bir kere oluştuktan sonra deposunun değişmemesi gerekir (Güvenlik).


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        // Görev: Gelen yorum nesnesini veritabanına kaydet.
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getByTweetId(Long tweetId) {
        // Görev: Belirli bir tweet'e ait tüm yorumları liste olarak getir.
        return commentRepository.findByTweetId(tweetId);
    }

    @Override
    public Comment getById(Long id) {
        // Görev: Verilen ID'ye göre tek bir yorum ara.
        Optional<Comment> optionalComment = commentRepository.findById(id);
        // Görev: Eğer yorum bulunduysa, yorumu döndür.
        if(optionalComment.isPresent()){
            return optionalComment.get();
        }
        // Görev: Bulunamazsa, programın çökmemesi için bir hata fırlat.
        throw new RuntimeException("Yorum bulunamadı: " + id);
    }

    @Override
    public Comment update(Long id, Comment comment) {
        // Görev: Önce güncellenecek yorumu ID'si ile bul.
        Comment existingComment = getById(id);
        // Görev: Bulunan yorumun içeriğini dışarıdan gelen yeni içerik ile değiştir.
        existingComment.setContent(comment.getContent());
        // Görev: Güncellenmiş yorumu veritabanına kaydet.
        return commentRepository.save(existingComment);
    }

    @Override
    public void delete(Long id) {
        // Görev: Verilen IDye sahip yorumu veritabanından sil.
        // TODO: Proje isterlerindeki "Yorumu sadece tweet sahibi veya yorumun sahibi silebilir"
        //  güvenlik kuralı, Spring Security eklendikten sonra buraya ekle.
        commentRepository.deleteById(id);
    }
}