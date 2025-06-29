package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // username e göre ara varsa getir yoksa
                                                    // .empty() döndür exept fırlatma
}








// SQL sorgu karşılığı; SELECT * FROM "User" WHERE username =??
//login endpointini karşılar


