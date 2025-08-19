package com.project.accounts_service.controller;

import com.project.accounts_service.dto.AccountDTO;
import com.project.accounts_service.services.AccountService;
import static org.springframework.http.HttpStatus.*;

import com.project.accounts_service.services.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id ) {
        return ResponseEntity.status(OK).body(accountService.getAccountById(id));
    }

    @GetMapping("iban/{iban}")
    public ResponseEntity<AccountDTO> getAccountByIban(@PathVariable String iban) {
        return ResponseEntity.status(OK).body(accountService.getAccountByIban(iban));
    }

    @GetMapping("owner/{ownerId}")
    public ResponseEntity<AccountDTO> getAccountByOwnerId(@PathVariable String ownerId) {
        return ResponseEntity.status(OK).body(accountService.getAccountByOwnerId(ownerId));
    }
}
