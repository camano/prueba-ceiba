package com.ceiba.prueba.infrastructure.adapter.postgres.mapper;

import com.ceiba.prueba.domain.wallet.models.Wallet;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.AppUserEntity;
import com.ceiba.prueba.infrastructure.adapter.postgres.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class MapWallet {

    public Wallet mapWallet(WalletEntity walletEntity){

        return new Wallet(walletEntity.getUserId(),walletEntity.getBalanceCop());

    }

    public WalletEntity toEntity(Wallet wallet) {
        AppUserEntity userEntity=new AppUserEntity();
        userEntity.setId(wallet.userId());
        return WalletEntity.builder()
                .userId(wallet.userId())
                .user(userEntity)
                .balanceCop(wallet.balance())
                .build();
    }

}
