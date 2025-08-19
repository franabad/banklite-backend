package com.project.accounts_service.model;

import com.project.accounts_service.dto.AccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toAccountDTO(AccountModel accountModel);
    AccountModel toAccountModel(AccountDTO accountDTO);
}
