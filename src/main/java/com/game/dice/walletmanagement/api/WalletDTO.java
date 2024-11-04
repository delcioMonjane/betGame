package com.game.dice.walletmanagement.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WalletDTO {
    private long userId;
    private double balance;


}
