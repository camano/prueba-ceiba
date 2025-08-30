package com.ceiba.prueba.domain.fund.models;

import java.math.BigDecimal;

public record Fund(Long id, String name, String categoryCode, BigDecimal minAmount) {}
