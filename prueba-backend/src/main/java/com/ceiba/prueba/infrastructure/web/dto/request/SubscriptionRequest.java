package com.ceiba.prueba.infrastructure.web.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SubscriptionRequest {

    private Long userId;
    private Long fundId;
    private BigDecimal amount;
    private String notifyChannel;

}
