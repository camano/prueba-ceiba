package com.ceiba.prueba.infrastructure.adapter.postgres.impl;

import com.ceiba.prueba.domain.suscription.gateway.ISubscriptionRepository;
import com.ceiba.prueba.domain.suscription.models.Subscription;
import com.ceiba.prueba.domain.suscription.models.SubscriptionStatus;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.SubscriptionEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.mapper.MapSubscription;
import com.ceiba.prueba.infrastructure.adapter.postgres.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionImpl implements ISubscriptionRepository {

    private final SubscriptionRepository repository;
    private final MapSubscription mapSubscription;

    public SubscriptionImpl(SubscriptionRepository repository, MapSubscription mapSubscription) {
        this.repository = repository;
        this.mapSubscription = mapSubscription;
    }

    @Override
    public Optional<Subscription> findActiveByUserAndFund(Long userId, Long fundId) {
        return repository
                .findByUser_IdAndFund_IdAndStatus(userId, fundId, SubscriptionStatus.ACTIVE)
                .map(mapSubscription::mapSubscription);
    }

    @Override
    public Subscription save(Subscription s) {
        SubscriptionEntity addSubscription = repository.save(mapSubscription.mapSubsctiptionEntity(s));

        return mapSubscription.mapSubscription(addSubscription);
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return repository.findById(id).map(mapSubscription::mapSubscription);
    }
}
