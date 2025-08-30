package com.ceiba.prueba.domain.transation.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WalletTx(UUID id, Long userId, Long fundId, TransactionType type,
                       BigDecimal amount, Instant createdAt, Long subscriptionId) {
}
