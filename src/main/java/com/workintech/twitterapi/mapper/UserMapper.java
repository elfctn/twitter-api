package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.entity.User;
import org.mapstruct.Mapper;

// @Mapper: Bu arayüzün bir MapStruct Mapper'ı olduğunu belirtir.
// componentModel = "spring": MapStruct'a, bu mapper'ın bir Spring Bean'i olarak
// yönetilmesi gerektiğini söyler. Bu sayede onu başka sınıflara enjekte edebiliriz.
@Mapper(componentModel = "spring")
public interface UserMapper {

    // Bu metot imzası, MapStruct'a "User" nesnesini alıp bir
    // "UserResponseDTO" nesnesine dönüştürmesi gerektiğini söyler.
    // MapStruct, alan isimleri aynı olduğu için (username -> username, email -> email)
    // tüm dönüşümü otomatik olarak yapacaktır.
    UserResponseDTO userToUserResponseDTO(User user);
}
