package com.ceiba.prueba.domain.transation.gateway;

import com.ceiba.prueba.domain.transation.models.WalletTx;

import java.util.List;

public interface IWalletTxRepositoryPort {
    WalletTx save(WalletTx tx);

    public List<WalletTx> getLastTransactions(Long userId, int limit);
}
