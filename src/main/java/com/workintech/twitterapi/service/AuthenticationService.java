package com.workintech.twitterapi.service;

import com.workintech.twitterapi.dto.LoginRequestDTO;
import com.workintech.twitterapi.dto.UserCreateDTO;
import com.workintech.twitterapi.entity.User;

public interface AuthenticationService {
    User register(UserCreateDTO userCreateDTO);// Kayıt  sorumluluğunu userda tutmak yerine burada tuttum
    String login(LoginRequestDTO loginRequestDTO);//LoginRequestDTO alacak JWT stringi döndürecek.
}

