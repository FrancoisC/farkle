package io.github.francoisc.farkle.service;

import org.springframework.stereotype.Service;
import io.github.francoisc.farkle.model.players.HumanPlayer;
import io.github.francoisc.farkle.model.players.Player;

import java.util.List;

@Service
public class GameService {

    private final List<Player> players;

    public GameService() {
        // TODO : gérér le choix du nombre de joueurs
        players = List.of(HumanPlayer.initDefaultPlayer());
    }
}
