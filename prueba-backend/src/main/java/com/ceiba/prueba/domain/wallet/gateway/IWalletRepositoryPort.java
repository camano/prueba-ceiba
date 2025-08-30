package com.ceiba.prueba.domain.wallet.gateway;

import com.ceiba.prueba.domain.wallet.models.Wallet;

public interface IWalletRepositoryPort {
    Wallet getWallet(Long userId);
    void update(Wallet wallet);
}
