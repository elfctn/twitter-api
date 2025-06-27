package com.workintech.twitterapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Bu DTO, bir tweet'in detaylarını API yanıtı olarak dışarıya göndermek için kullanılır.
 * Entity'nin kendisini değil, sadece gerekli ve güvenli olan verileri içerir.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private UserSummaryDTO user;

    /**
     * Tweet'i atan kullanıcının sadece temel bilgilerini içeren iç içe bir DTO.
     * Bu, kullanıcının şifresi gibi hassas bilgilerin sızmasını engeller.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummaryDTO {
        private Long id;
        private String username;
    }
}
