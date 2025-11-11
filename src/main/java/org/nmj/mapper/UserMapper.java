package org.nmj.mapper;

import org.nmj.dto.UserDto;
import org.nmj.model.User;

public class UserMapper {
    public static UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
