package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.User;
import java.util.Optional;



public interface UserService {
    Optional<User> findByUsername(String username);
    User getById(Long id);
}



