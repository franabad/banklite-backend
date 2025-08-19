package com.project.accounts_service.services;

import com.project.accounts_service.dto.AccountDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


public interface AccountService {

    AccountDTO getAccountById(String accountId);
    AccountDTO getAccountByIban(String iban);
    AccountDTO getAccountByOwnerId(String ownerId);
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO updateAccount(AccountDTO accountDTO);
    void deleteAccount(String accountId);
}
