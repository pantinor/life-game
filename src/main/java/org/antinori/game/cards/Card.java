package org.antinori.game.cards;

public abstract class Card {

    String description = "";
    String title = "";

    public enum Type {

        SPIN2, SPIN4, EXEMPT, PAY_BANK, PAY_YOU, HOUSE, STARTER_HOME, LTI, CAREER, COLLEGE_CAREER
    };
    Type type;

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Card [\ndescription=%s,\ntitle=%s,\ntype=%s]", description, title, type);
    }

}
