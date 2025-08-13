package com.project.accounts_service.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//TESTTTTTTTTTT
public class AccountModel {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role; // e.g., "USER", "ADMIN"
    private boolean isActive; // Indicates if the account is active or not
}
