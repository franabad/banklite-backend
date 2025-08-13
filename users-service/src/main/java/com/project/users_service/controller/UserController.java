package com.project.users_service.controller;


import static org.springframework.http.HttpStatus.*;

import com.project.users_service.model.LoginRequest;
import com.project.users_service.model.UserDTO;
import com.project.users_service.model.UserModel;
import com.project.users_service.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// FIX ME: Later this has to change and set a global configuration with Spring Security
// This is a test for Jenkins
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.status(OK).body(users);
    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
//        UserDTO user = userService.getUserById(id);
//        return ResponseEntity.status(OK).body(user);
//    }

    @GetMapping("/user/{nif}")
    public ResponseEntity<UserDTO> getUserByNif(@PathVariable String nif) {
        UserDTO user = userService.getUserByNif(nif);
        return ResponseEntity.status(OK).body(user);
    }

    @PostMapping("/user/validate")
    public ResponseEntity<UserDTO> validateUserCredentials(@RequestBody LoginRequest loginRequest) {
        UserDTO user = userService.validateUserCredentials(loginRequest);
        return ResponseEntity.status(OK).body(user);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserModel user) {
        return ResponseEntity.status(CREATED).body(userService.createUser(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
