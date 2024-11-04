package com.game.dice.betmanagment.api;

import com.game.dice.betmanagment.domain.Bet;
import java.util.List;
import java.util.stream.Collectors;

public class BetMapper {

    public static BetDTO mapToDTO(Bet bet) {
        if (bet == null) {
            return null;
        }
        return new BetDTO(bet.getId(), bet.getBetAmount(), bet.getUser().getId(), bet.getState().name(), bet.getValueDice(), bet.isWin(), bet.getType().name());
    }

    public static List<BetDTO> mapToDTOs(List<Bet> bets) {
        return bets.stream()
                   .map(BetMapper::mapToDTO)
                   .collect(Collectors.toList());
    }
}
