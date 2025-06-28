package com.workintech.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bir kullanıcı hakkındaki temel, güvenli bilgileri taşımak için kullanılan
 * merkezi DTO. Diğer tüm DTO'lar bu sınıfı kullanacak.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private Long id;
    private String username;
}
