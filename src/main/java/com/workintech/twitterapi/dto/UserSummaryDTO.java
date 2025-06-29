package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private Long id;
    private String username;
}





// kullanıcı hakkındaki temel bilgileri taşımak için bir dto sınıfı
// Diğer tüm DTO'lar bu sınıfı kullanacak- merkezi dto-
