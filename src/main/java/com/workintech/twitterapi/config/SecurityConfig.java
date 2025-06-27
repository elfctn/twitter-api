package com.workintech.twitterapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration: Bu sınıfın, Spring için merkezi yapılandırma (konfigürasyon)
// tanımlamaları içeren bir sınıf olduğunu belirtir.
@Configuration
public class SecurityConfig {

    // @Bean: Bu anotasyon bir metoda konulur ve Spring'e der ki:
    // "Bu metodu çalıştır, geri döndürdüğü nesneyi al ve kendi yönettiğin
    // parçaların (bean'lerin) arasına koy. Artık bu nesneden sen sorumlusun
    // ve ihtiyaç duyan herkese bu nesneyi sen vereceksin."
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Bu projede şifreleme algoritması olarak BCrypt kullanacağımızı söylüyoruz.
        return new BCryptPasswordEncoder();
    }
}