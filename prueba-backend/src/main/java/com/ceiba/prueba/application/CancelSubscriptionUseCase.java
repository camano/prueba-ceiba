package com.ceiba.prueba.application;

import com.ceiba.prueba.domain.fund.gateway.IFundRepositoryPort;
import com.ceiba.prueba.domain.suscription.gateway.ISubscriptionRepository;
import com.ceiba.prueba.domain.suscription.models.Subscription;
import com.ceiba.prueba.domain.suscription.models.SubscriptionStatus;
import com.ceiba.prueba.domain.transation.gateway.IWalletTxRepositoryPort;
import com.ceiba.prueba.domain.transation.models.TransactionType;
import com.ceiba.prueba.domain.transation.models.WalletTx;
import com.ceiba.prueba.domain.wallet.gateway.IWalletRepositoryPort;
import com.ceiba.prueba.domain.wallet.models.Wallet;
import com.ceiba.prueba.infrastructure.web.Exceptions.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class CancelSubscriptionUseCase {
    private final ISubscriptionRepository subscriptionRepository;
    private final IWalletRepositoryPort walletRepository;
    private final IWalletTxRepositoryPort walletTxRepository;

    public CancelSubscriptionUseCase(ISubscriptionRepository subscriptionRepository, IWalletRepositoryPort walletRepository, IWalletTxRepositoryPort walletTxRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.walletRepository = walletRepository;
        this.walletTxRepository = walletTxRepository;
    }
    public Subscription execute(Long userId, Long subscriptionId, String notifyChannel) {
        Subscription s = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new BusinessException("Suscripción no existe"));

        if (!s.getUserId().equals(userId)) throw new BusinessException("No pertenece al usuario");
        if (s.getStatus() == SubscriptionStatus.CANCELLED)
            throw new BusinessException("La suscripción ya está cancelada");

        // Reintegrar y cerrar
        Wallet w = walletRepository.getWallet(userId);
        walletRepository.update(w.credit(s.getAmount()));
        s.setStatus(SubscriptionStatus.CANCELLED);
        s.setCancelledAt(Instant.now());
        subscriptionRepository.save(s);

        walletTxRepository.save(new WalletTx(null, userId, s.getFundId(), TransactionType.CANCEL,
                s.getAmount(), Instant.now(), s.getId()));



        return s;
    }
}
