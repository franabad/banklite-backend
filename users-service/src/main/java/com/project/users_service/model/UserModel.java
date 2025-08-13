package com.project.users_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

// FIX ME: Lombok doesn't work. I have the generate getters and setters manually
// DONE: Lombok works now ✅
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false, length = 9, name = "nif")
    private String nif;
    @Column(nullable = false, length = 100, name = "password")
    private String password;
    @Column(nullable = false, length = 50, name = "name")
    private String name;
    @Column(length = 50, name = "lastnames")
    private String lastnames;
    @Column(unique = true, nullable = false, length = 50, name = "email")
    private String email;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
}
