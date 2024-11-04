package com.game.dice.betmanagment.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BetDTO {
    private Long id;
    private double betAmount;
    private Long userId;
    private String state;
    private int valueDice;
    private boolean win;
    private String type; // Similar to state, consider using an enum for this
}
