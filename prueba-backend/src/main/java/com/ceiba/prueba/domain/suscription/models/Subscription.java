package com.ceiba.prueba.domain.suscription.models;

import com.ceiba.prueba.infrastructure.adapter.postgres.entity.AppUserEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.FundEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.SubscriptionEntity;
import lombok.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {
    private Long id;
    private Long userId;
    private Long fundId;
    private BigDecimal amount;
    private SubscriptionStatus status;
    private Instant createdAt;
    private Instant cancelledAt;

}
