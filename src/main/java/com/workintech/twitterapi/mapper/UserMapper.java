package com.workintech.twitterapi.mapper;

import com.workintech.twitterapi.dto.UserResponseDTO;
import com.workintech.twitterapi.dto.UserSummaryDTO;
import com.workintech.twitterapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Bu metot, UserController'ın ihtiyacını karşılamak için User entity'sini,
     * tweet listesini de içeren tam UserResponseDTO'ya çevirir.
     * MapStruct, alan isimleri eşleştiği için (tweets -> tweets) iç içe listeyi
     * otomatik olarak dönüştürecektir.
     */
    UserResponseDTO userToUserResponseDTO(User user);

    /**
     * Bu metot, diğer mapper'ların (TweetMapper, CommentMapper vb.)
     * ihtiyacını karşılamak için User entity'sini, sadece temel bilgileri
     * içeren UserSummaryDTO'ya çevirir.
     */
    UserSummaryDTO userToUserSummaryDTO(User user);
}
