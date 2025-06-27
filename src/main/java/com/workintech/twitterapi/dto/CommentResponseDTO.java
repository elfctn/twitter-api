package com.workintech.twitterapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private UserSummaryDTO user;
    private Long tweetId;

    @Data
    public static class UserSummaryDTO {
        private Long id;
        private String username;

        public UserSummaryDTO(Long id, String username) {
            this.id = id;
            this.username = username;
        }
    }
}


//Yorumları yanıtlarken kullanılacak
//  yorum hangi tweete ve hangi kullanıcıya ait bilgisis içeriyor