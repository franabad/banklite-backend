package com.project.users_service.model;

import java.util.Date;

public record UserDTO(String nif, String name, String lastnames, String email, Date birthDate){}
