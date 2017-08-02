package org.antinori.life.gdx;

import java.util.ArrayList;
import java.util.List;
import org.antinori.game.cards.Card;
import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;
import org.antinori.game.cards.LifeTile;
import org.antinori.game.cards.ShareTheWealthCard;

public class Player {

    private final String name;
    private final boolean computerPlayer;
    private Location location;
    private int money;
    private final List<LifeTile> lifeTiles = new ArrayList<>();
    private int child_count;
    private boolean married;
    private CareerCard career;
    private boolean hasCollegeDegree;
    private boolean isRetired;
    private String retireeHome;
    private HouseCard house;
    private final List<ShareTheWealthCard> stwCards = new ArrayList<>(6);
    private int ltiCardValue = -1;
    private int loans;
    private int salary;
    private boolean missTurn;
    private int spintoWinWager;
    private final Actor actor;

    public Player(String name, Actor actor, boolean computer) {
        this.actor = actor;
        this.name = name;
        this.computerPlayer = computer;
    }

    public boolean addCard(ShareTheWealthCard card) {
        stwCards.add(card);
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean isComputerPlayer() {
        return computerPlayer;
    }

    public Location getLocation() {
        return location;
    }

    public Actor getActor() {
        return this.actor;
    }

    public int getMoney() {
        return money;
    }

    public int addMoney(int amount) {
        money = money + amount;
        return money;
    }

    public int subtractMoney(int amount) {
        money = money - amount;

        //get a loan if you go minus
        while (money < 0) {
            getLoanFromBank();
        }

        return money;
    }

    public int getLoanFromBank() {
        loans++;
        money = money + 20000;
        return money;
    }

    public int getLifeCardCount() {
        return lifeTiles.size();
    }

    public void addLifeTile(LifeTile tile) {
        lifeTiles.add(tile);
    }

    public LifeTile removeLifeTile() {
        if (lifeTiles.isEmpty()) {
            return null;
        }
        return (LifeTile) lifeTiles.remove(0);
    }

    public int getChildCount() {
        return child_count;
    }

    public CareerCard getCareer() {
        return career;
    }

    public HouseCard getHouse() {
        return house;
    }

    public int getLtiCardValue() {
        return ltiCardValue;
    }

    public int getLoans() {
        return loans;
    }

    public void setLocation(Location newLocation) {

        if (this.location != null) {
            int dx = newLocation.getX() - this.location.getX();
            int dy = newLocation.getY() - this.location.getY();

            float ang = (float) Math.toDegrees(Math.atan2(dy, dx));
            if (ang < 0) {
                ang = 360 + ang;
            }
            ang = (ang + 90 + 22.5f) % 360;
            ang /= 45f;

            this.actor.setDirection((int) ang);
        }

        this.location = newLocation;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addChild(int count) {
        child_count = child_count + count;
    }

    public void setCareer(CareerCard career) {
        this.career = career;
        if (this.career != null) {
            this.salary = career.getBase_salary();
        }
    }

    public int addPayRaise(int amount) {
        salary = salary + amount;
        if (salary > career.getMax_salary()) {
            salary = career.getMax_salary();
        }
        return salary;
    }

    public void setHouse(HouseCard house) {
        this.house = house;
    }

    public void setLtiCardValue(int lti_card_value) {
        this.ltiCardValue = lti_card_value;
    }

    public void setLoans(int loans) {
        this.loans = loans;
    }

    public String getStwCard(int idx) {
        try {
            if (computerPlayer) {
                return "?";
            }
            return stwCards.get(idx).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public ShareTheWealthCard hasExemptionCard() {
        ShareTheWealthCard found = null;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == Card.Type.EXEMPT) {
                found = card;
                stwCards.remove(card);
                break;
            }
        }
        return found;
    }

    public boolean hasPayYouCard() {
        boolean found = false;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == Card.Type.PAY_YOU) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean hasPayBankCard() {
        boolean found = false;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == Card.Type.PAY_BANK) {
                found = true;
                break;
            }
        }
        return found;
    }

    public ShareTheWealthCard usePayCard(Card.Type type) {
        ShareTheWealthCard ret = null;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == type) {
                ret = card;
                stwCards.remove(card);
                break;
            }
        }
        return ret;
    }

    public boolean hasSPIN2Card() {
        boolean found = false;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == Card.Type.SPIN2) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean hasSPIN4Card() {
        boolean found = false;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == Card.Type.SPIN4) {
                found = true;
                break;
            }
        }
        return found;
    }

    public ShareTheWealthCard useSPINCard(Card.Type type) {
        ShareTheWealthCard found = null;
        for (ShareTheWealthCard card : stwCards) {
            if (card.getType() == type) {
                found = card;
                stwCards.remove(card);
                break;
            }
        }
        return found;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getMarriedStatusText() {
        return (married ? "Married with " : "Not Married");
    }

    public int getSalary() {
        return salary;
    }

    public String getCareerName() {
        if (career == null) {
            return "No Career";
        }
        return career.getDescription();
    }

    public String getHouseName() {
        if (house == null) {
            return "No House";
        }
        return house.getDescription();
    }

    public boolean hasCollegeDegree() {
        return hasCollegeDegree;
    }

    public void setHasCollegeDegree(boolean hasCollegeDegree) {
        this.hasCollegeDegree = hasCollegeDegree;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toShortString() {
        return name + "     " + getMarriedStatusText() + "     " + child_count+ " kids\n" +
                "$" + money + "     " + (career == null ? "no job" : career.getDescription()) + "\n" +
                "Salary: $" + salary + "     " + (house == null ? "no house" : house.getTitle()) + "\n" +
                "LIFE: " + lifeTiles.size() + "     LTI: " + ltiCardValue + "     Loans: " + loans;
    }
    
    public String displaySTW() {
        return "S1: " + getStwCard(0) + " S4: " + getStwCard(3) + "\n" +
                "S2: " + getStwCard(1) + " S5: " + getStwCard(4) + "\n" +
                "S3: " + getStwCard(2) + " S6: " + getStwCard(5) ;
    }

    public boolean isMissTurn() {
        return missTurn;
    }

    public void setMissTurn(boolean miss_turn) {
        this.missTurn = miss_turn;
    }

    public int getSpintoWinWager() {
        return spintoWinWager;
    }

    public void setSpintoWinWager(int spintowin_wager) {
        this.spintoWinWager = spintowin_wager;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean isRetired) {
        this.isRetired = isRetired;
    }

    public String getRetireeHome() {
        return retireeHome;
    }

    public void setRetireeHome(String retireeHome) {
        this.retireeHome = retireeHome;
    }

}
