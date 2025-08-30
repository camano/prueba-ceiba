package com.ceiba.prueba.domain.suscription.gateway;

import com.ceiba.prueba.domain.suscription.models.Subscription;

import java.util.Optional;

public interface ISubscriptionRepository {
    Optional<Subscription> findActiveByUserAndFund(Long userId, Long fundId);
    Subscription save(Subscription s);
    Optional<Subscription> findById(Long id);
}
