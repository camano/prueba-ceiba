package com.ceiba.prueba.domain.fund.gateway;

import com.ceiba.prueba.domain.fund.models.Fund;

import java.util.List;
import java.util.Optional;

public interface IFundRepositoryPort {
    Optional<Fund> findById(Long id);
    List<Fund> findAll();
}
