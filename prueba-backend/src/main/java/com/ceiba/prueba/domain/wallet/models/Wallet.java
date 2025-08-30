package com.ceiba.prueba.domain.wallet.models;

import java.math.BigDecimal;

// domain/model/Wallet.java
public record Wallet(Long userId, BigDecimal balance) {
    public Wallet debit(BigDecimal amount) {
        return new Wallet(userId, balance.subtract(amount));
    }
    public Wallet credit(BigDecimal amount) {
        return new Wallet(userId, balance.add(amount));
    }
}
