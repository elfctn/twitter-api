package com.workintech.twitterapi.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    // Artık bağımsız olan UserSummaryDTO'yu kullanıyor.
    private UserSummaryDTO user;
    private Long tweetId;
    // İçindeki 'static class UserSummaryDTO' tanımı SİLİNDİ.
}