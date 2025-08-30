package com.ceiba.prueba.infrastructure.adapter.postgres.mapper;

import com.ceiba.prueba.domain.transation.models.WalletTx;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.AppUserEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.FundEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.SubscriptionEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.WalletTxEntity;
import org.springframework.stereotype.Component;

@Component
public class MapWalletTx {
    public WalletTxEntity toEntity(WalletTx tx) {

        AppUserEntity userEntity=new AppUserEntity();
        userEntity.setId(tx.userId());
        FundEntity fundEntity =new FundEntity();
        fundEntity.setId(tx.fundId());

        SubscriptionEntity subscriptionEntity=new SubscriptionEntity();
        subscriptionEntity.setId(tx.subscriptionId());
        return WalletTxEntity.builder()
                .id(tx.id())
                .user(userEntity)
                .fund(fundEntity)
                .type(tx.type())
                .amountCop(tx.amount())
                .createdAt(tx.createdAt())
                .subscription(subscriptionEntity)
                .build();
    }

    public WalletTx toDomain(WalletTxEntity entity) {
        return new WalletTx(
                entity.getId(),
                entity.getUser().getId(),
                entity.getFund().getId(),
                entity.getType(),
                entity.getAmountCop(),
                entity.getCreatedAt(),
                entity.getSubscription().getId()
        );
    }
}
