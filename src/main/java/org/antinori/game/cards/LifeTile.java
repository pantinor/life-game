package org.antinori.game.cards;

public class LifeTile {

    private int value = 0;

    public LifeTile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LifeTile [value=" + value + "]";
    }

}
