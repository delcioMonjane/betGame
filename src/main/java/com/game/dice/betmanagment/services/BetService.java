package com.game.dice.betmanagment.services;

import com.game.dice.betmanagment.domain.Bet;
import com.game.dice.betmanagment.domain.BetState;
import com.game.dice.betmanagment.domain.BetType;
import com.game.dice.betmanagment.repositories.BetRepository;
import com.game.dice.usermanagement.model.User;
import com.game.dice.usermanagement.repositories.UserRepository;
import com.game.dice.walletmanagement.domain.Wallet;
import com.game.dice.walletmanagement.repositories.WalletRepository;
import com.game.dice.walletmanagement.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BetService {
    private final UserRepository userRepository;
    private final BetRepository betRepository;
    private final WalletRepository walletRepository;
    private final WalletService walletService;
    private static final String USER_NOT_FOUND = "User not found";

    public List<Bet> getBetsByUser(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));
        return betRepository.findByUser(user);
    }

    public List<Bet> getOpenBetsByUser(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));
        return betRepository.findByUserAndState(user, BetState.OPEN);
    }

    public List<Bet> closeBet(Long userId) {
        List<Bet> openBets = getOpenBetsByUser(userId);
        List<Bet> closedBets = new ArrayList<>();
        for (Bet bet : openBets) {
            bet.closeBet();
            if (bet.isWin()) {
                walletService.deposit(userId, 2 * bet.getBetAmount());
            }
            closedBets.add(betRepository.save(bet));
        }
        return closedBets;
    }

    public Bet placeBet(Long userID, double amount, String type) {
        List<Bet> openBets = getOpenBetsByUser(userID);
        if (!openBets.isEmpty()) {
            throw new IllegalArgumentException("User already has an open bet");
        }
        Wallet wallet = walletRepository.findByUserId(userID);
        if (wallet.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        User user = userRepository.findById(userID).orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));
        BetType betType = BetType.valueOf(type);
        Bet bet = new Bet(user, amount, betType);
        walletService.withdraw(userID, bet.getBetAmount());
        return betRepository.save(bet);
    }
}
