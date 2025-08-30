package com.ceiba.prueba.infrastructure.adapter.postgres.entity;
import com.ceiba.prueba.domain.transation.models.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wallet_tx")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private java.util.UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id")
    private FundEntity fund;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(name = "amount_cop", nullable = false)
    private java.math.BigDecimal amountCop;

    @Column(name = "created_at", nullable = false)
    private java.time.Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;

}
