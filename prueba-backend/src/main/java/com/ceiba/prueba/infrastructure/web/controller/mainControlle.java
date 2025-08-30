package com.ceiba.prueba.infrastructure.web.controller;

import com.ceiba.prueba.application.CancelSubscriptionUseCase;
import com.ceiba.prueba.application.GetTransactionHistoryUseCase;
import com.ceiba.prueba.application.OpenSubscriptionUseCase;
import com.ceiba.prueba.domain.transation.models.WalletTx;
import com.ceiba.prueba.infrastructure.web.dto.request.SubscriptionRequest;
import com.ceiba.prueba.infrastructure.web.dto.response.SubscriptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class mainControlle {

    private final OpenSubscriptionUseCase openSubscriptionUseCase;
    private final CancelSubscriptionUseCase cancelSubscriptionUseCase;
    private final GetTransactionHistoryUseCase historyUseCase;

    public mainControlle(OpenSubscriptionUseCase openSubscriptionUseCase, CancelSubscriptionUseCase cancelSubscriptionUseCase, GetTransactionHistoryUseCase historyUseCase) {
        this.openSubscriptionUseCase = openSubscriptionUseCase;
        this.cancelSubscriptionUseCase = cancelSubscriptionUseCase;
        this.historyUseCase = historyUseCase;
    }

    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> open(@RequestBody SubscriptionRequest request) {

        return new ResponseEntity<>(openSubscriptionUseCase.execute(1L, request.getFundId(), request.getAmount(), request.getNotifyChannel()),HttpStatus.CREATED);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id,
                                       @RequestParam(defaultValue = "EMAIL") String notifyChannel) {

        return new ResponseEntity<>(cancelSubscriptionUseCase.execute(1L, id, notifyChannel),HttpStatus.OK);
    }

    @GetMapping("/History")
    public ResponseEntity<?> history(){
        return new ResponseEntity<>(historyUseCase.execute(1L, 10),HttpStatus.OK);
    }

}
