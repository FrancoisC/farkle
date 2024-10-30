package io.github.francoisc.farkle.model.players;

import java.util.List;

public interface Player {
    void resetDices();
    void removeDicesWithIndex(List<Integer> indexes);
    void addToTurnScore(int dicesScore);
    void bankScore();
}
