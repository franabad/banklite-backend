package com.project.accounts_service.services;

import com.project.accounts_service.dto.AccountDTO;
import com.project.accounts_service.exceptions.AccountNullException;
import com.project.accounts_service.exceptions.InvalidAccountIdException;
import com.project.accounts_service.model.AccountMapper;
import com.project.accounts_service.model.AccountModel;
import com.project.accounts_service.repository.IAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    private final IAccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(IAccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDTO getAccountById(String accountId) {
        try {
            UUID uuid = UUID.fromString(accountId);

            AccountModel account = accountRepository.findById(uuid).orElseThrow(
                    () -> new AccountNullException("Account not found with ID: " + accountId)
            );

            return accountMapper.toAccountDTO(account);
        } catch (IllegalArgumentException e) {
            throw new InvalidAccountIdException("Invalid account ID format: " + accountId);
        }
    }

    @Override
    public AccountDTO getAccountByIban(String iban) {
        AccountModel account = accountRepository.findByIban(iban).orElseThrow(
                () -> new AccountNullException("Account not found with IBAN: " + iban)
        );
        return accountMapper.toAccountDTO(account);
    }

    @Override
    public AccountDTO getAccountByOwnerId(String ownerId) {
        AccountModel account = accountRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new AccountNullException("Account not found for owner ID: " + ownerId)
        );

        return accountMapper.toAccountDTO(account);
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public void deleteAccount(String accountId) {

    }
}
