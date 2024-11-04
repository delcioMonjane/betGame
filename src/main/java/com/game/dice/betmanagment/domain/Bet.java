package com.game.dice.betmanagment.domain;

import com.game.dice.usermanagement.model.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Random;

@Entity
public class Bet {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Column
    @Getter
    private double betAmount;

    @Getter
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Getter
    private BetState state;

    @Column
    @Getter
    private int valueDice;

    @Column
    @Getter
    private boolean win;

    @Enumerated(EnumType.STRING)
    @Getter
    private BetType type;

    @Transient
    private static final Random r = new Random();

    public Bet(User user, double betAmount,  BetType type) {
        this.betAmount = betAmount;
        this.user = user;
        this.state = BetState.OPEN;
        this.valueDice = rollDice();
        if (type == BetType.EVEN){
            this.type = BetType.EVEN;
            this.win = valueDice % 2 == 0;
        } else {
            this.type = BetType.ODD;
            this.win = valueDice % 2 != 0;
        }
    }

    public Bet() {
        // only for JPA
    }

    public void closeBet() {
        this.state = BetState.CLOSED;

    }

    public int rollDice() {
        return r.nextInt(6) + 1;
    }


}
