package com.game.dice.betmanagment.repositories;

import com.game.dice.betmanagment.domain.Bet;
import com.game.dice.betmanagment.domain.BetState;
import com.game.dice.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findByUser(User user);

    List<Bet> findByUserAndState(User user, BetState state);
}
