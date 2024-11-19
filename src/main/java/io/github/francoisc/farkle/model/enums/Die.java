package io.github.francoisc.farkle.model.enums;

import lombok.Getter;

@Getter
public enum Die {

    ONE(1, 100, 1000),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5, 50, 500),
    SIX(6);

    private final int name;
    private final int score;
    private final int tripleScore;

    Die(int name, int score, int tripleScore) {
        this.name = name;
        this.score = score;
        this.tripleScore = tripleScore;
    }

    Die(int name) {
        this.name = name;
        this.score = 0;
        this.tripleScore = name * 100;
    }
}
