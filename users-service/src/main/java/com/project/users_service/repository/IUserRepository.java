package com.project.users_service.repository;

import com.project.users_service.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserModel, String> {
    UserModel findByNif(String nif);

    // Custom query methods can be defined here if needed
    // For example:
    // Optional<UserModel> findByEmail(String email);
}
