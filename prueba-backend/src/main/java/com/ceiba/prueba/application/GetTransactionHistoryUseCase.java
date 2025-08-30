package com.ceiba.prueba.application;

import com.ceiba.prueba.domain.transation.gateway.IWalletTxRepositoryPort;
import com.ceiba.prueba.domain.transation.models.WalletTx;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetTransactionHistoryUseCase {

      private final IWalletTxRepositoryPort walletTxRepository;

    public GetTransactionHistoryUseCase(IWalletTxRepositoryPort walletTxRepository) {
        this.walletTxRepository = walletTxRepository;
    }

    public List<WalletTx> execute(Long userId, int limit) {
        return walletTxRepository.getLastTransactions(userId, limit);
    }
}
