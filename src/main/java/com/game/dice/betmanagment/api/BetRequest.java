package com.game.dice.betmanagment.api;

import lombok.Getter;

@Getter
public class BetRequest {
private Long userId;
    private double betAmount;
    private String type;
}
