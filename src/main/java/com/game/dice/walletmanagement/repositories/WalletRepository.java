package com.game.dice.walletmanagement.repositories;

import com.game.dice.walletmanagement.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserId(Long userId);
}
