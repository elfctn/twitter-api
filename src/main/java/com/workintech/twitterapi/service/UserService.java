package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.UserDTO;
import com.workintech.twitterapi.entity.User;

import java.util.Optional;

public interface UserService {
    User register(UserDTO userDTO);
    Optional<User> findByUsername(String username);
    User getById(Long id);
}