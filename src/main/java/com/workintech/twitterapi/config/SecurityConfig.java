package com.workintech.twitterapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean SecurityFilterChain: Spring Security'nin ana güvenlik kurallarını
    // tanımladığımız yer burasıdır. Bu metot, projemizin "güvenlik kalkanını" oluşturur.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // CSRF korumasını devre dışı bırakıyoruz. REST API'lerde genellikle gereksizdir.
                .csrf(csrf -> csrf.disable())
                // Gelen isteklere (HttpRequests) yetkilendirme kuralları uygula.
                .authorizeHttpRequests(auth -> {
                    // Kural 1: "/auth/**" ile başlayan tüm yollara (yani /auth/register, /auth/login vs.)
                    // kimlik sormadan, herkese (permitAll) izin ver.
                    auth.requestMatchers("/auth/**").permitAll();
                    // Kural 2: Yukarıdaki kuralın dışındaki DİĞER TÜM isteklere (anyRequest)
                    // sadece kimliği doğrulanmış (authenticated) kullanıcıların erişebilmesini sağla.
                    auth.anyRequest().authenticated();
                })
                .build();
    }
}
