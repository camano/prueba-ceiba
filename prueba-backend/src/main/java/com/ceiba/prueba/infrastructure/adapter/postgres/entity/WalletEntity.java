package com.ceiba.prueba.infrastructure.adapter.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wallet", schema = "btg")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUserEntity user;

    @Column(name = "balance_cop", nullable = false)
    private java.math.BigDecimal balanceCop;
}
