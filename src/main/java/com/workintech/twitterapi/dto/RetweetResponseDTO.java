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
    private LocalDateTime createdAt;
    private Long userId;
    private Long tweetId;
}
