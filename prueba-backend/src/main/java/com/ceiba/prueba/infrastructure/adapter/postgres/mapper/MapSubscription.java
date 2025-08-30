package com.ceiba.prueba.infrastructure.adapter.postgres.mapper;

import com.ceiba.prueba.domain.suscription.models.Subscription;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.AppUserEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.FundEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.SubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class MapSubscription {


    public Subscription  mapSubscription(SubscriptionEntity subscriptionEntity){

        return Subscription.builder()
                .id(subscriptionEntity.getId())
                .userId(subscriptionEntity.getId())
                .fundId(subscriptionEntity.getId())
                .amount(subscriptionEntity.getAmountCop())
                .createdAt(subscriptionEntity.getCreatedAt())
                .cancelledAt(subscriptionEntity.getCancelledAt())
                .status(subscriptionEntity.getStatus())
                .build();

    }

   public SubscriptionEntity mapSubsctiptionEntity(Subscription subscription){

        AppUserEntity userEntity=new AppUserEntity();
        userEntity.setId(subscription.getUserId());

        FundEntity fundEntity =new FundEntity();
       fundEntity.setId(subscription.getFundId());

        return SubscriptionEntity.builder()
                .user(userEntity)
                .amountCop(subscription.getAmount())
                .fund(fundEntity)
                .cancelledAt(subscription.getCancelledAt())
                .createdAt(subscription.getCreatedAt())
                .status(subscription.getStatus())
                .build();
    }
}
