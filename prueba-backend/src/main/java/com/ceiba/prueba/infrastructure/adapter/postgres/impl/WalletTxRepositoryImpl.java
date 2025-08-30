package com.ceiba.prueba.infrastructure.adapter.postgres.impl;

import com.ceiba.prueba.domain.transation.gateway.IWalletTxRepositoryPort;
import com.ceiba.prueba.domain.transation.models.WalletTx;
import com.ceiba.prueba.domain.wallet.gateway.IWalletRepositoryPort;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.WalletTxEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.mapper.MapWalletTx;
import com.ceiba.prueba.infrastructure.adapter.postgres.repository.IWalletTxRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WalletTxRepositoryImpl implements IWalletTxRepositoryPort {

    private final IWalletTxRepository walletTxRepository;
    private final MapWalletTx mapWalletTx;

    public WalletTxRepositoryImpl(IWalletTxRepository walletTxRepository, MapWalletTx mapWalletTx) {
        this.walletTxRepository = walletTxRepository;
        this.mapWalletTx = mapWalletTx;
    }

    @Override
    public WalletTx save(WalletTx tx) {
        WalletTxEntity entity = mapWalletTx.toEntity(tx);
        WalletTxEntity saved = walletTxRepository.save(entity);
        return mapWalletTx.toDomain(saved);
    }

    @Override
    public List<WalletTx> getLastTransactions(Long userId, int limit) { return walletTxRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId)
            .stream()
            .map(mapWalletTx::toDomain)
            .toList();
    }
}
