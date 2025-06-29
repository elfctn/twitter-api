package com.workintech.twitterapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Bir kullanıcı hakkındaki genel bilgileri ve tweet özetlerini içeren DTO.
 */
@Data
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<TweetSummaryDTO> tweets;



    /**
     * Kullanıcının tweet'lerinin sadece ID ve içeriğini gösteren özet DTO.
     */
    @Data
    @NoArgsConstructor
    public static class TweetSummaryDTO {
        private Long id;
        private String content;

        public TweetSummaryDTO(Long id, String content) {
            this.id = id;
            this.content = content;
        }
    }


}
//registten sonra user sayfasında görünenler