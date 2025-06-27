package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.User;
import java.util.Optional;

// Artık bu servisin görevi sadece mevcut kullanıcıları bulmak.
// Kayıt (register) sorumluluğu AuthenticationService'e geçti.
public interface UserService {
    Optional<User> findByUsername(String username);
    User getById(Long id);
    //register metodu buradan kaldırıldı.
}