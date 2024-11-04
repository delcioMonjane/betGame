package com.game.dice.walletmanagement.api;

import com.game.dice.walletmanagement.domain.Wallet;

public class WalletMapper {
    public static WalletDTO mapToDTO(Wallet wallet) {
        return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
    }
}
