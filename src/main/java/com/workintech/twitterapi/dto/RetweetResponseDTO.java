package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetweetResponseDTO {
    private Long id;
    // Artık bağımsız olan UserSummaryDTO'yu kullanıyor.
    private UserSummaryDTO user;
    private LocalDateTime createdAt;
    private Long tweetId;
    // İçindeki 'static class UserSummaryDTO' tanımı SİLİNDİ.
}