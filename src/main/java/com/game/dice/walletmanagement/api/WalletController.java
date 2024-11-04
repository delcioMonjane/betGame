package com.game.dice.walletmanagement.api;

import com.game.dice.walletmanagement.domain.Wallet;
import com.game.dice.walletmanagement.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WalletDTO> getWallet(@PathVariable Long userId) {
        Optional<Wallet> walletOptional = walletService.getWalletByUserId(userId);

        if (walletOptional.isPresent()) {
            WalletDTO walletDTO = WalletMapper.mapToDTO(walletOptional.get());
            return ResponseEntity.ok(walletDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/deposit/{userId}")
    public ResponseEntity<WalletDTO> deposit(@PathVariable Long userId, @RequestParam double amount) {
        WalletDTO walletDTO = WalletMapper.mapToDTO(walletService.deposit(userId, amount));
        return ResponseEntity.ok(walletDTO);
    }

    @PostMapping("/withdraw/{userId}")
    public ResponseEntity<WalletDTO> withdraw(@PathVariable Long userId, @RequestParam double amount) {
        WalletDTO walletDTO = WalletMapper.mapToDTO(walletService.withdraw(userId, amount));
        return ResponseEntity.ok(walletDTO);
    }
}
