package com.ceiba.prueba.infrastructure.adapter.postgres.impl;

import com.ceiba.prueba.domain.fund.gateway.IFundRepositoryPort;
import com.ceiba.prueba.domain.fund.models.Fund;
import com.ceiba.prueba.infrastructure.adapter.postgres.mapper.MapFund;
import com.ceiba.prueba.infrastructure.adapter.postgres.repository.IFundRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FundRepositoryImpl implements IFundRepositoryPort {
    private final IFundRepository fundRepository;
    private final MapFund mapFund;

    public FundRepositoryImpl(IFundRepository fundRepository, MapFund mapFund) {
        this.fundRepository = fundRepository;
        this.mapFund = mapFund;
    }


    @Override
    public Optional<Fund> findById(Long id) {
        return fundRepository.findById(id).map(mapFund::mapFund);
    }

    @Override
    public List<Fund> findAll() {
        return fundRepository.findAll().stream().map(mapFund::mapFund).toList();
    }
}
