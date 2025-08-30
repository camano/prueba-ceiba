package com.ceiba.prueba.infrastructure.adapter.postgres.repository;

import com.ceiba.prueba.infrastructure.adapter.postgres.entity.WalletTxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWalletTxRepository extends JpaRepository<WalletTxEntity,Long> {
    List<WalletTxEntity> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);
}
