package com.workintech.twitterapi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    // @Value: Spring'e, application.properties dosyasındaki değeri
    // bu değişkene atamasını söyler.
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey secretKey;

    // Bu metot, TokenService nesnesi oluşturulduktan hemen sonra
    // Spring tarafından otomatik olarak çalıştırılır.
    @jakarta.annotation.PostConstruct
    public void init() {
        // application.properties'den aldığımız metin halindeki secret'ı,
        // jjwt kütüphanesinin anlayacağı güvenli bir anahtar formatına çeviriyoruz.
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public String generateToken(Authentication authentication) {
        // Kimliği doğrulanmış kullanıcının adını alıyoruz.
        String username = authentication.getName();

        // JWT'yi oluşturmaya başlıyoruz.
        return Jwts.builder()
                // "subject": Token'ın kiminle ilgili olduğunu belirtir (kullanıcı adı).
                .setSubject(username)
                // "issuedAt": Token'ın ne zaman oluşturulduğunu belirtir.
                .setIssuedAt(new Date())
                // "expiration": Token'ın ne zaman geçersiz olacağını belirtir.
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // "signWith": Token'ı, bizim gizli anahtarımızla ve HS256 algoritmasıyla imzalıyoruz.
                // Bu imza, token'ın yolda değiştirilmediğini garantiler.
                .signWith(secretKey, SignatureAlgorithm.HS256)
                // Son olarak, sıkıştırılmış ve güvenli token string'ini oluşturuyoruz.
                .compact();
    }
}