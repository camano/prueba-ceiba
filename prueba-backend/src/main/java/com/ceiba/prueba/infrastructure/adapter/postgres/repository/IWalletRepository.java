package com.ceiba.prueba.infrastructure.adapter.postgres.repository;

import com.ceiba.prueba.infrastructure.adapter.postgres.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWalletRepository extends JpaRepository<WalletEntity,Long> {

    Optional<WalletEntity> findByUser_Id(Long userId);
}
