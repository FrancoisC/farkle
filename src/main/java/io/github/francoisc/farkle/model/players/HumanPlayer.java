package io.github.francoisc.farkle.model.players;

import lombok.*;
import io.github.francoisc.farkle.model.enums.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

@Getter
public class HumanPlayer implements Player {
    private int bankScore;
    private int turnScore;
    private int remainingDices;
    private List<Dice> dices;

    private HumanPlayer(int bankScore, int turnScore, int remainingDices, List<Dice> dices) {
        this.bankScore = bankScore;
        this.turnScore = turnScore;
        this.remainingDices = remainingDices;
        this.dices = dices;
    }

    public static HumanPlayer initDefaultPlayer() {
        var player = new HumanPlayer(0, 0, 6, null);
        player.resetDices();
        return player;
    }

    @Override
    public void resetDices() {
        this.dices = new ArrayList<>(remainingDices);
    }

    @Override
    public void removeDicesWithIndex(List<Integer> indexes) {
        for (int index : indexes) {
            dices.remove(index);
        }
        dices.removeIf(Objects::isNull);
        remainingDices = dices.size();
    }

    @Override
    public void bankScore() {
        bankScore += turnScore;
    }

    @Override
    public void addToTurnScore(int dicesScore) {
        turnScore += dicesScore;
    }

    public Dice rollDice() {
        int randomDice = RandomGenerator.getDefault().nextInt(6) + 1;
        return Arrays.stream(Dice.values()).filter(dice -> dice.getName() == randomDice).findFirst().orElseThrow();
    }

    public void performNewDiceRoll() {
        resetDices();
        for (int i = 0; i < getRemainingDices(); i++) {
            dices.add(rollDice());
        }
    }

    public void computeSelectedDicesScore(List<Integer> dicesIndex) {
        int dicesScore = getDices().stream()
                .reduce(0, (subtotal, dice) -> subtotal + dice.getScore(), Integer::sum);
        if (dicesScore <= 0) {
            throw new RuntimeException("Le score 'dicesScore' est nul ou négatif");
        }
        addToTurnScore(dicesScore);
        removeDicesWithIndex(dicesIndex);
    }
}
