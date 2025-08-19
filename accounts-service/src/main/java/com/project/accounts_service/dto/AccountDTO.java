package com.project.accounts_service.dto;

import java.math.BigDecimal;

public record AccountDTO(String iban, BigDecimal balance) {
}
