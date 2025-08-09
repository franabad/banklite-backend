package com.project.users_service.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(UserModel userModel);

    UserModel toUserEntity(UserDTO userDTO);
}
