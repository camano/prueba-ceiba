package com.ceiba.prueba.infrastructure.adapter.postgres.impl;

import com.ceiba.prueba.domain.wallet.gateway.IWalletRepositoryPort;
import com.ceiba.prueba.domain.wallet.models.Wallet;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.WalletEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.mapper.MapWallet;
import com.ceiba.prueba.infrastructure.adapter.postgres.repository.IWalletRepository;
import com.ceiba.prueba.infrastructure.web.Exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class WalletRepositoryImpl implements IWalletRepositoryPort {

    private final IWalletRepository walletRepository;
    private final MapWallet mapWallet;

    public WalletRepositoryImpl(IWalletRepository walletRepository, MapWallet mapWallet) {
        this.walletRepository = walletRepository;
        this.mapWallet = mapWallet;
    }

    @Override
    public Wallet getWallet(Long userId) {
        return walletRepository.findByUser_Id(userId)
                .map(mapWallet::mapWallet)
                .orElseThrow(() -> new BusinessException("No existe wallet para el usuario " + userId));
    }

    @Override
    public void update(Wallet wallet) {
        WalletEntity entity = mapWallet.toEntity(wallet);
        walletRepository.save(entity);
    }
}
