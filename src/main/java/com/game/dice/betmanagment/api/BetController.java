package com.game.dice.betmanagment.api;

import com.game.dice.betmanagment.domain.Bet;
import com.game.dice.betmanagment.services.BetService;
import com.game.dice.shared.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// should be @RequestMapping("/api/bet")
@RequestMapping("/api/play")
@AllArgsConstructor
public class BetController {
    
    private final BetService betService;

    @PostMapping("/")
    public ResponseEntity<Object> placeBet(@RequestBody BetRequest betRequest) {
        try {
            Bet createdBet = betService.placeBet(betRequest.getUserId(), betRequest.getBetAmount(), betRequest.getType());
            return ResponseEntity.ok(BetMapper.mapToDTO(createdBet));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BetDTO>> getBetsByUser(@PathVariable Long userId) {
        List<Bet> bets = betService.getBetsByUser(userId);
        return ResponseEntity.ok(BetMapper.mapToDTOs(bets));
    }

    @GetMapping("/user/{userId}/open")
    public ResponseEntity<List<BetDTO>> getOpenBetsByUser(@PathVariable Long userId) {
        List<Bet> openBets = betService.getOpenBetsByUser(userId);
        return ResponseEntity.ok(BetMapper.mapToDTOs(openBets));
    }

    @PutMapping("/close/{clientId}")
    public ResponseEntity<List<BetDTO>> closeBets(@PathVariable Long clientId) {
        try {
            List<Bet> closedBet = betService.closeBet(clientId);
            List<BetDTO> closedBetDTO = BetMapper.mapToDTOs(closedBet);
            return ResponseEntity.ok(closedBetDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
