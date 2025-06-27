package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponseDTO {
    private Long userId;
    private String message;
}