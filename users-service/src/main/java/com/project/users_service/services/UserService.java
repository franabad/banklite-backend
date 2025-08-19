package com.project.users_service.services;


import com.project.users_service.dto.ApiResponse;
import com.project.users_service.dto.LoginRequest;
import com.project.users_service.dto.UserDTO;
import com.project.users_service.model.UserModel;

public interface UserService {

    ApiResponse<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
    UserDTO getUserByNif(String nif);
    UserDTO validateUserCredentials(LoginRequest loginRequest);
    UserDTO createUser(UserModel user);
    void deleteUserById(String id);
}
