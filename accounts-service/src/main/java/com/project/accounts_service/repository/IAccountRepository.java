package com.project.accounts_service.repository;

import com.project.accounts_service.model.AccountModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccountRepository extends CrudRepository<AccountModel, UUID> {

    Optional<AccountModel> findByIban(String iban);
    Optional<AccountModel> findByOwnerId(String ownerId);

    // Additional custom query methods can be defined here if needed
}
