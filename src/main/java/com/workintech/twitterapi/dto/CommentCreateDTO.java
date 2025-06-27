package com.workintech.twitterapi.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private String content;
}

//yotum oluşturma isteği için sadece içerik taşıyor