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
    // @Value: Spring'e, application.properties dosyasındaki değeri bu değişkene atamasını söyler.
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    private SecretKey secretKey;

    // Bu metot, TokenService nesnesi oluşturulduktan hemen sonra Spring tarafından otomatik olarak çalıştırılır.
    @jakarta.annotation.PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // app.prop teki metin halindeki secretı jjwt ktph anlayacağı güvenli bir anahtar formatına çevir.
    }


    

    @Override
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();// Kimliği doğrulanmış kullanıcının adını al
        return Jwts.builder()// JWTyi oluşturmaya başla
                .setSubject(username)// subject: Token'ın kiminle ilgili olduğunu belir (kullanıcı adı).
                .setIssuedAt(new Date())// issuedAt: Token'ın ne zaman oluşturulduğunu belirt
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // "expiration": Token'ın ne zaman geçersiz olacağını belirt
                .signWith(secretKey, SignatureAlgorithm.HS256)// "signWith": Token'ı, bizim gizli anahtarımızla ve HS256 algoritmasıyla imzala
                                                              // Bu imza, token'ın yolda değiştirilmediğini garantiler.
                .compact();//, sıkıştırılmış güvenli token stringini oluştur
    }
}