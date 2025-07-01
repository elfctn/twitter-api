package com.workintech.twitterapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<TweetSummaryDTO> tweets;







    // DAHA SONRA AYRI SINIF OLUŞTURACAĞIM

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
