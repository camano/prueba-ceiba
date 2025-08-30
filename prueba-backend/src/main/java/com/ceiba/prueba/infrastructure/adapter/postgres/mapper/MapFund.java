package com.ceiba.prueba.infrastructure.adapter.postgres.mapper;

import com.ceiba.prueba.domain.fund.models.Fund;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.FundEntity;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class MapFund {

    public Fund mapFund(FundEntity fundEntity){
        return new Fund(fundEntity.getId(), fundEntity.getName(), fundEntity.getCategory().getName(),fundEntity.getMinAmountCop());
    }

}
