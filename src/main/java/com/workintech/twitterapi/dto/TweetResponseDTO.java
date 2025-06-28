package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // Artık bağımsız olan UserSummaryDTO'yu kullanıyor.
    private UserSummaryDTO user;
    private List<CommentResponseDTO> comments;
    private List<LikeResponseDTO> likes;
    private List<RetweetResponseDTO> retweets;
    // İçindeki 'static class UserSummaryDTO' tanımı SİLİNDİ.
}