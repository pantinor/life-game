package org.antinori.game.cards;

public class LongTermInvestmentCard extends Card {

    public static final String TITLE = "LONG-TERM INVESTMENT";

    private int value = 0;

    public LongTermInvestmentCard(Type type, int value) {
        this.value = value;
        this.type = type;
        this.title = TITLE;
        this.description = "Value " + value;
    }

    public int getValue() {
        return value;
    }

}
