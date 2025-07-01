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

}



