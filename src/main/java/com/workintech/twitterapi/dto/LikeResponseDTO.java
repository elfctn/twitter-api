package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {
    private Long likeId;
    private Long userId;
    private Long tweetId;
}
