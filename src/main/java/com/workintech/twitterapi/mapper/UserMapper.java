package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.dto.UserSummaryDTO;
import com.workintech.twitterapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO userToUserResponseDTO(User user);
    UserSummaryDTO userToUserSummaryDTO(User user);
}
