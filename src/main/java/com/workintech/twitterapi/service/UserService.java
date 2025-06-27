package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.UserCreateDTO;
import com.workintech.twitterapi.entity.User;

import java.util.Optional;

public interface UserService {
    User register(UserCreateDTO userCreateDTO);
    Optional<User> findByUsername(String username);
    User getById(Long id);
}