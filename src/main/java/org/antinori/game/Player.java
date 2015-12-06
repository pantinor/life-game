package org.antinori.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;
import org.antinori.game.cards.Card;
import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;
import org.antinori.game.cards.LifeTile;
import org.antinori.game.cards.ShareTheWealthCard;
import org.antinori.game.slick.Actor;

public class Player {

    private String playerName = "";
    private boolean computerPlayer = false;
    private Location playerLocation = null;
    private Color playerColor = Color.gray;
    private int money = 0;
    private Vector<LifeTile> lifeTiles = new Vector<LifeTile>();
    private int child_count = 0;
    private boolean married = false;
    private CareerCard career = null;
    private boolean hasCollegeDegree = false;
    private boolean isRetired = false;
    private String retireeHome = null;
    private HouseCard house = null;
    private ArrayList<ShareTheWealthCard> stw_cards = new ArrayList<ShareTheWealthCard>(6);
    private int lti_card_value = -1;
    private int loans = 0;
    private int salary = 0;
    private boolean miss_turn = false;
    private int spintowin_wager = 0;
    private Actor actor = null;

    public Player(String name, Color color, boolean computer) {
        this.playerName = name;
        this.playerColor = color;
        this.computerPlayer = computer;
    }

    public Player(String name, Actor actor, Color color, boolean computer) {
        this.actor = actor;
        this.playerName = name;
        this.playerColor = color;
        this.computerPlayer = computer;
        actor.setPlayerObject(this);
    }

    public boolean addCard(ShareTheWealthCard card) {
        stw_cards.add(card);
        return true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isComputerPlayer() {
        return computerPlayer;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public Color getPlayerColor() {
        return playerColor;
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

    public int getLife_card_count() {
        return lifeTiles.size();
    }

    public void addLifeTile(LifeTile tile) {
        lifeTiles.add(tile);
    }

    public LifeTile removeLifeTile() {
        if (lifeTiles.size() == 0) {
            return null;
        }
        return (LifeTile) lifeTiles.remove(0);
    }

    public int getChild_count() {
        return child_count;
    }

    public CareerCard getCareer() {
        return career;
    }

    public HouseCard getHouse() {
        return house;
    }

    public int getLti_card_value() {
        return lti_card_value;
    }

    public int getLoans() {
        return loans;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setComputerPlayer(boolean computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    public void setPlayerLocation(Location playerLocation) {
        this.playerLocation = playerLocation;

        if (actor != null) {
            //block here until it returns by rendering/moving the actor to the target location
            actor.waitUntilMovedToDestination(playerLocation.getX(), playerLocation.getY());
        }
    }

    public void setPlayerIsCurrent() {
        if (actor != null) {
            actor.setIsCurrent();
        }
    }

    public Actor getActor() {
        return this.actor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
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

    public void setLti_card_value(int lti_card_value) {
        this.lti_card_value = lti_card_value;
    }

    public void setLoans(int loans) {
        this.loans = loans;
    }

    public String getStw_card_1() {
        try {
            if (computerPlayer) {
                return "Hidden";
            }
            return stw_cards.get(0).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStw_card_2() {
        try {
            if (computerPlayer) {
                return "Hidden";
            }
            return stw_cards.get(1).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStw_card_3() {
        try {
            if (computerPlayer) {
                return "Hidden";
            }
            return stw_cards.get(2).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStw_card_4() {
        try {
            if (computerPlayer) {
                return "Hidden";
            }
            return stw_cards.get(3).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStw_card_5() {
        try {
            if (computerPlayer) {
                return "Hidden";
            }
            return stw_cards.get(4).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public String getStw_card_6() {
        try {
            if (computerPlayer) {
                return "Hidden";
            }
            return stw_cards.get(5).toShortestString();
        } catch (Exception e) {
            return "";
        }
    }

    public ShareTheWealthCard hasExemptionCard() {
        ShareTheWealthCard found = null;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == Card.Type.EXEMPT) {
                found = card;
                stw_cards.remove(card);
                break;
            }
        }
        return found;
    }

    public boolean hasPayYouCard() {
        boolean found = false;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == Card.Type.PAY_YOU) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean hasPayBankCard() {
        boolean found = false;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == Card.Type.PAY_BANK) {
                found = true;
                break;
            }
        }
        return found;
    }

    public ShareTheWealthCard usePayCard(Card.Type type) {
        ShareTheWealthCard ret = null;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == type) {
                ret = card;
                stw_cards.remove(card);
                break;
            }
        }
        return ret;
    }

    public boolean hasSPIN2Card() {
        boolean found = false;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == Card.Type.SPIN2) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean hasSPIN4Card() {
        boolean found = false;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == Card.Type.SPIN4) {
                found = true;
                break;
            }
        }
        return found;
    }

    public ShareTheWealthCard useSPINCard(Card.Type type) {
        ShareTheWealthCard found = null;
        for (ShareTheWealthCard card : stw_cards) {
            if (card.getType() == type) {
                found = card;
                stw_cards.remove(card);
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

    public String getMarried_status_text() {
        return (married ? "Married with " : "Not Married");
    }

    public int getSalary() {
        return salary;
    }

    public String getCareer_name() {
        if (career == null) {
            return "No Career";
        }
        return career.getDescription();
    }

    public String getHouse_name() {
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
        return playerName;
    }

    public String toShortString() {
        return String.format("%s $%s %s with %s kids and %s",
                playerName, money, (career == null ? "no job" : career.getDescription()), child_count, (house == null ? "no house" : house.getTitle()));
    }

    public boolean isMiss_turn() {
        return miss_turn;
    }

    public void setMiss_turn(boolean miss_turn) {
        this.miss_turn = miss_turn;
    }

    public int getSpintowin_wager() {
        return spintowin_wager;
    }

    public void setSpintowin_wager(int spintowin_wager) {
        this.spintowin_wager = spintowin_wager;
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

    public static void main(String[] args) {

        Player player = new Player("test", Color.blue, false);
        player.addMoney(10000);

        double factor = (double) 200 / (double) 1000000;

        double money = ((double) player.getMoney() * factor);
        System.out.println(money);

    }

}
