package org.antinori.game.cards;

public class ShareTheWealthCard extends Card {

    public static final String TITLE = "SHARE THE WEALTH";
    public static final String TITLE_STW = "SHARE THE WEALTH";
    public static final String TITLE_SPIN = "SPIN TO WIN";
    public static final String TITLE_EXEMPT = "EXEMPTION CARD";

    public static final String pay_bank_description = "When you land on a yellow pay space, give this card to any other player.  That player will pay 1/2 the amount you'd pay to the bank.";
    public static final String pay_you_description = "Give this card to any other player landing on a yellow collect space.  That player will pay you 1/2 the amount he/she collects.";
    public static final String spin2_description = "The holder of this card may play two numbers, instead of one, on a spin to win space.";
    public static final String spin4_description = "The holder of this card may play four numbers, instead of one, on a spin to win space.";
    public static final String exemption_description = "The holder of this card does not pay when given a share the wealth card or when sued through a lawsuit space.";

    public ShareTheWealthCard(Type type) {
        this.type = type;
        switch (type) {
            case SPIN2:
                description = TITLE_SPIN + " - " + spin2_description;
                title = TITLE;
                break;
            case SPIN4:
                description = TITLE_SPIN + " - " + spin4_description;
                title = TITLE;
                break;
            case EXEMPT:
                description = TITLE_EXEMPT + " - " + exemption_description;
                title = TITLE;
                break;
            case PAY_BANK:
                description = TITLE_STW + " - " + pay_bank_description;
                title = TITLE;
                break;
            case PAY_YOU:
                description = TITLE_STW + " - " + pay_you_description;
                title = TITLE;
                break;
            default:
                description = "error";
                title = "error";
        }

    }

    public String toShortString() {
        String description = "";
        switch (type) {
            case SPIN2:
                description = TITLE_SPIN + " - " + "PLAY 2 NUMBERS";
                break;
            case SPIN4:
                description = TITLE_SPIN + " - " + "PLAY 4 NUMBERS";
                break;
            case EXEMPT:
                description = TITLE_EXEMPT;
                break;
            case PAY_BANK:
                description = TITLE_STW + " - " + "OPPONENT PAYS HALF";
                break;
            case PAY_YOU:
                description = TITLE_STW + " - " + "YOU COLLECT HALF";
                break;
        }
        return description;
    }

    public String toShortestString() {
        String description = "";
        switch (type) {
            case SPIN2:
                description = "Play2";
                break;
            case SPIN4:
                description = "Play4";
                break;
            case EXEMPT:
                description = "Exempt";
                break;
            case PAY_BANK:
                description = "OtherPays";
                break;
            case PAY_YOU:
                description = "Collect";
                break;
        }
        return description;
    }

}
