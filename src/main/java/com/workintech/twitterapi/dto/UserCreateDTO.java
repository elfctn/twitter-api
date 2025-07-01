package com.workintech.twitterapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @NotNull(message = "Kullanıcı adı boş bırakılamaz")
    @Size(min = 3, max = 50, message = "Kullanıcı adı en az 3, en fazla 50 karakter olmalıdır")
    private String username;

    @NotNull(message = "Şifre boş bırakılamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
    private String password;

    @NotNull(message = "Email boş bırakılamaz")
    @Email(message = "Lütfen geçerli bir email adresi giriniz")
    private String email;
}


