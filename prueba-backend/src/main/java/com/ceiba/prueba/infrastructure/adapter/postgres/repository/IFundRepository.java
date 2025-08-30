package com.ceiba.prueba.infrastructure.adapter.postgres.repository;

import com.ceiba.prueba.infrastructure.adapter.postgres.entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IFundRepository extends JpaRepository<FundEntity, Long> {
}
