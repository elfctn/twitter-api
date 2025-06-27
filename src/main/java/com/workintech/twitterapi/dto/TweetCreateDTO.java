package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bu DTO, yeni bir tweet oluşturma isteği (request) geldiğinde,
 * sadece tweet'in içeriğini taşımak için kullanılır.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetCreateDTO {
    private String content;
}