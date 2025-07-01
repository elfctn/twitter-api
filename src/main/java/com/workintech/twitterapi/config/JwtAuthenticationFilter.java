package com.workintech.twitterapi.config;

import com.workintech.twitterapi.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;
    private final UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {// gelen istekte Authorization başlığı var mı ve Bearer ile başlıyor mu?
            filterChain.doFilter(request, response); //  yoksa işlemi kes.
            return;
        }
        String token = authHeader.substring(7); // Bearer  kısmını at
        String username = null;
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            username = claims.getSubject(); // tokenı ayrıştır- içindeki subjecti (kullanıcı adı?)
        }
        catch (Exception e) {}// token geçersizse veya süresi dolmuşsa hatayı yakala ve devam et





        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);// kullanıcı adı alındıysa ve daha önce kimlik doğrulanmadıysa..
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());// kullanıcıyı ve yetkilerini SpringSec.nin anlayacağı bir forma sok
            // mevcut güvenlik contextine bu kimliği yerleştir.
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);  // Artık bu kullanıcı, bu istek için "kimliği doğrulanmış" sayılır.
    }
}
