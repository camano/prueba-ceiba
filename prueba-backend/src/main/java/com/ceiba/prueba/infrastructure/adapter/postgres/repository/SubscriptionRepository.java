package com.ceiba.prueba.infrastructure.adapter.postgres.repository;

import com.ceiba.prueba.domain.suscription.models.SubscriptionStatus;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity,Long> {

    Optional<SubscriptionEntity> findByUser_IdAndFund_IdAndStatus(Long userId, Long fundId, SubscriptionStatus status);
}
