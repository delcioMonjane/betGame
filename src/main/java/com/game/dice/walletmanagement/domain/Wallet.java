package com.game.dice.walletmanagement.domain;

import com.game.dice.usermanagement.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private double balance;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Wallet() {
        // Default constructor for JPA
    }

    public Wallet(User user) {
        this.user = user;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

}
