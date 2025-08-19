package com.project.users_service.model;

import com.project.users_service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(UserModel userModel);
    UserModel toUserModel(UserDTO userDTO);
}
