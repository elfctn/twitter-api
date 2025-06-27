package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String message;
    private long timestamp;
}


//Hata olduğunda kullanıcıya nasıl bir JSON göndereceğimi
// tanımlayan bir rapor şablonu (DTO) oluşturmuş oldum