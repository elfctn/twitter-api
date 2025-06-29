package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.dto.UserSummaryDTO;
import com.workintech.twitterapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //user entitysini al userResponsedto ya cevir ----controller için
    UserResponseDTO userToUserResponseDTO(User user);

    //user entitysini al usersumdto ya cevir ----controller için
    UserSummaryDTO userToUserSummaryDTO(User user);
}
