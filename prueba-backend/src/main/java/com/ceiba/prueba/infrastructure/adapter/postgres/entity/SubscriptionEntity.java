package com.ceiba.prueba.infrastructure.adapter.postgres.entity;

import com.ceiba.prueba.domain.suscription.models.Subscription;
import com.ceiba.prueba.domain.suscription.models.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscription",schema = "btg",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "fund_id"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private FundEntity fund;

    @Column(name = "amount_cop", nullable = false)
    private java.math.BigDecimal amountCop;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    @Column(name = "created_at", nullable = false)
    private java.time.Instant createdAt;

    @Column(name = "cancelled_at")
    private java.time.Instant cancelledAt;



}
