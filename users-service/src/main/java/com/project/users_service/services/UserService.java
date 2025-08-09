package com.project.users_service.services;


import com.project.users_service.model.LoginRequest;
import com.project.users_service.model.UserDTO;
import com.project.users_service.model.UserModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
    UserDTO getUserByNif(String nif);
    UserDTO validateUserCredentials(LoginRequest loginRequest);
    UserDTO createUser(UserModel user);
    void deleteUserById(String id);
}
