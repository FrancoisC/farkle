package io.github.francoisc.farkle.model.players;

import java.util.List;

public interface Player {
    void resetDice();
    void removeDiceWithIndex(List<Integer> indexes);
    void addToTurnScore(int diceScore);
    void bankScore();
}
