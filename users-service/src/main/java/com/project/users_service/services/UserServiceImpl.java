package com.project.users_service.services;


import com.project.users_service.exceptions.PasswordInvalidException;
import com.project.users_service.exceptions.UserAlreadyExistsException;
import com.project.users_service.exceptions.UserNullException;
import com.project.users_service.model.LoginRequest;
import com.project.users_service.model.UserDTO;
import com.project.users_service.model.UserMapper;
import com.project.users_service.model.UserModel;
import com.project.users_service.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<UserModel> users = (List<UserModel>) userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNullException("No users found");
        }

        return users.stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(String userId) {
        UserModel user = userRepository.findById(userId).orElseThrow(
                () -> new UserNullException("User not found with ID: " + userId)
        );
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO getUserByNif(String nif) {
        UserModel user = userRepository.findByNif(nif);

        if (user == null) {
            throw new UserNullException("User not found with NIF: " + nif);
        }

        return userMapper.toUserDTO(user);
    }


    @Override
    @Transactional(readOnly = true)
    public UserDTO validateUserCredentials(LoginRequest loginRequest) {
        UserModel user = userRepository.findByNif(loginRequest.getNif());

        if (user == null) {
            throw new UserNullException("User not found with NIF: " + loginRequest.getNif());
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new PasswordInvalidException("Invalid password for user with NIF: " + loginRequest.getNif());
        }

        return userMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO createUser(UserModel user) {
        UserModel existedUser = userRepository.findByNif(user.getNif());

        if (existedUser != null) {
            throw new UserAlreadyExistsException("User with NIF " + user.getNif() + " already exists");
        }

        UserModel saveUser = userRepository.save(user);

        return userMapper.toUserDTO(saveUser);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        UserModel user = userRepository.findById(id).orElseThrow(
                () -> new UserNullException("User not found")
        );

        userRepository.delete(user);
    }
}
