package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.UserNotFoundException;
import com.workintech.twitterapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + id));
    }
}

