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
public class UserDTO {
    @NotNull
    @Size(max = 50)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;


}
