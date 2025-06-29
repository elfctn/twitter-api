package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.User;
import java.util.Optional;



public interface UserService {
    Optional<User> findByUsername(String username);//Kullanıcı varsa getir yosa boş dön
    User getById(Long id);// olduğu zaman ID ile getir --yoksa exception fırlatacak
}



