package com.ceiba.prueba.domain.suscription.usecase;


import com.ceiba.prueba.domain.suscription.models.Subscription;

import java.util.Optional;

public interface IsubscriptionService {

    Optional<Subscription> findActiveByUserAndFund(Long userId, Long fundId);
    Subscription save(Subscription s);
    Optional<Subscription> findById(Long id);
}
