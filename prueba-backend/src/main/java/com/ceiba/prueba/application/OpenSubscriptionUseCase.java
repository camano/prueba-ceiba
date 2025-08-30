package com.ceiba.prueba.application;

import com.ceiba.prueba.domain.fund.gateway.IFundRepositoryPort;
import com.ceiba.prueba.domain.fund.models.Fund;
import com.ceiba.prueba.domain.suscription.gateway.ISubscriptionRepository;
import com.ceiba.prueba.domain.suscription.models.Subscription;
import com.ceiba.prueba.domain.suscription.models.SubscriptionStatus;
import com.ceiba.prueba.domain.suscription.usecase.IsubscriptionService;
import com.ceiba.prueba.domain.transation.gateway.IWalletTxRepositoryPort;
import com.ceiba.prueba.domain.transation.models.TransactionType;
import com.ceiba.prueba.domain.transation.models.WalletTx;
import com.ceiba.prueba.domain.wallet.gateway.IWalletRepositoryPort;
import com.ceiba.prueba.domain.wallet.models.Wallet;
import com.ceiba.prueba.infrastructure.web.Exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OpenSubscriptionUseCase  {

    private final IFundRepositoryPort fundRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final IWalletRepositoryPort walletRepository;
    private final IWalletTxRepositoryPort walletTxRepository;

    public OpenSubscriptionUseCase(IFundRepositoryPort fundRepository, ISubscriptionRepository subscriptionRepository, IWalletRepositoryPort walletRepository, IWalletTxRepositoryPort walletTxRepository) {
        this.fundRepository = fundRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.walletRepository = walletRepository;
        this.walletTxRepository = walletTxRepository;
    }

    public Subscription  execute(Long userId, Long fundId, BigDecimal amount, String notifyChannel){

        Fund fund = fundRepository.findById(fundId)
                .orElseThrow(() -> new BusinessException("Fondo no existe"));


        if (amount.compareTo(fund.minAmount()) < 0) {
            throw new BusinessException("El monto debe ser >= mínimo del fondo ("+fund.minAmount()+")");
        }


        subscriptionRepository.findActiveByUserAndFund(userId, fundId).ifPresent(s -> {
            throw new BusinessException("Ya existe una suscripción activa a este fondo");
        });


        Wallet wallet = walletRepository.getWallet(userId);
        if (wallet.balance().compareTo(amount) < 0) {
            throw new BusinessException("No tiene saldo disponible para vincularse al fondo " + fund.name());
        }

        Subscription s = new Subscription();
        s.setUserId(userId);
        s.setFundId(fundId);
        s.setAmount(amount);
        s.setStatus(SubscriptionStatus.ACTIVE);
        s.setCreatedAt(Instant.now());
        s = subscriptionRepository.save(s);

        walletTxRepository.save(new WalletTx(null, userId, fundId, TransactionType.OPEN, amount, Instant.now(), s.getId()));

        return s;
    }

}
