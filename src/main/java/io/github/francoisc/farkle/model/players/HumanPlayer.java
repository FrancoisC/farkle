package io.github.francoisc.farkle.model.players;

import lombok.*;
import io.github.francoisc.farkle.model.enums.Die;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

@Getter
public class HumanPlayer implements Player {
    private int bankScore;
    private int turnScore;
    private int remainingDice;
    private List<Die> dice;

    private HumanPlayer(int bankScore, int turnScore, int remainingDice, List<Die> dice) {
        this.bankScore = bankScore;
        this.turnScore = turnScore;
        this.remainingDice = remainingDice;
        this.dice = dice;
    }

    public static HumanPlayer initDefaultPlayer() {
        var player = new HumanPlayer(0, 0, 6, null);
        player.resetDice();
        return player;
    }

    @Override
    public void resetDice() {
        this.dice = new ArrayList<>(remainingDice);
    }

    @Override
    public void removeDiceWithIndex(List<Integer> indexes) {
        for (int index : indexes) {
            dice.remove(index);
        }
        dice.removeIf(Objects::isNull);
        remainingDice = dice.size();
    }

    @Override
    public void bankScore() {
        bankScore += turnScore;
    }

    @Override
    public void addToTurnScore(int diceScore) {
        turnScore += diceScore;
    }

    public Die rollDie() {
        int randomDieNumber = RandomGenerator.getDefault().nextInt(6) + 1;
        return Arrays.stream(Die.values()).filter(die -> die.getName() == randomDieNumber).findFirst().orElseThrow();
    }

    public void rollDice() {
        resetDice();
        for (int i = 0; i < getRemainingDice(); i++) {
            dice.add(rollDie());
        }
    }

    public void computeSelectedDiceScore(List<Integer> diceIndex) {
        int diceScore = getDice().stream()
                .reduce(0, (subtotal, die) -> subtotal + die.getScore(), Integer::sum);
        if (diceScore <= 0) {
            throw new RuntimeException("Le score 'diceScore' est nul ou négatif");
        }
        addToTurnScore(diceScore);
        removeDiceWithIndex(diceIndex);
    }
}
