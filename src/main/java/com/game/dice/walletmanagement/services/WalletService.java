package com.game.dice.walletmanagement.services;

import com.game.dice.usermanagement.model.User;
import com.game.dice.walletmanagement.domain.Wallet;
import com.game.dice.walletmanagement.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    
    public Wallet createWallet(User user) {
        Wallet wallet = new Wallet(user);
        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWalletByUserId(Long userId) {
        return Optional.ofNullable(walletRepository.findByUserId(userId));
    }

    public Wallet deposit(Long userId, double amount) {
        Wallet wallet = getWalletByUserId(userId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.deposit(amount);
        return walletRepository.save(wallet);
    }

    public Wallet withdraw(Long userId, double amount) {
        Wallet wallet = getWalletByUserId(userId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.withdraw(amount);
        return walletRepository.save(wallet);
    }
}
