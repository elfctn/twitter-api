package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.UserDTO;
import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.repository.UserRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 1. Constructor Injection: Bağımlılıkları constructor üzerinden istiyorum.
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserDTO userDTO) {
        // 2. DTO Kullanımı: Dışarıdan gelen veriyi temiz bir DTO ile alıyorum.
        User user = new User();
        user.setUsername(userDTO.getUsername());
        // 3. Şifreleme: Şifreyi her zaman encode ederek veritabanına kaydediyorum
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // 4. Null Safety: Kullanıcı bulunamazsa NullPointerException almamak için Optional kullanıyorm.
        return userRepository.findByUsername(username);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        // TODO: Burası için "UserNotFoundException" oluşturulacak.
        throw new RuntimeException("User not found with id: " + id);
    }
}