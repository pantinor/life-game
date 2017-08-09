package org.antinori.life.gdx;

import com.badlogic.gdx.graphics.Color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.antinori.game.SoundEffect;
import org.antinori.game.cards.Card;
import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;
import org.antinori.game.cards.LifeTile;
import org.antinori.game.cards.ShareTheWealthCard;
import static org.antinori.life.gdx.Life.TILE_DIM;

public class NewGame {

    private final BaseScreen screen;
    public static String[] NUMBER_OPTIONS = {"No", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private List<Player> players = new ArrayList<>(6);

    private final List<Card> STW_CARDS;
    private final List<Card> CAREER_CARDS;
    private final List<Card> COLLEGE_CAREER_CARDS;
    private final List<LifeTile> LIFE_TILES;

    private final List<HouseCard> STARTER_HOMES = new ArrayList<>(6);
    private final List<HouseCard> BETTER_HOMES = new ArrayList<>(6);

    private final String[] STARTER_HOMES_OPTIONS;
    private final String[] BETTER_HOMES_OPTIONS;

    private int currentPlayerIdx = 0;
    public boolean gameOver = false;

    public NewGame(BaseScreen screen) {

        this.screen = screen;

        ArrayList<Card> deck = new ArrayList<>(21);
        for (int i = 0; i < 4; i++) {
            deck.add(new ShareTheWealthCard(Card.Type.PAY_BANK));
        }
        for (int i = 0; i < 4; i++) {
            deck.add(new ShareTheWealthCard(Card.Type.PAY_YOU));
        }
        for (int i = 0; i < 4; i++) {
            deck.add(new ShareTheWealthCard(Card.Type.SPIN2));
        }
        for (int i = 0; i < 4; i++) {
            deck.add(new ShareTheWealthCard(Card.Type.SPIN4));
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new ShareTheWealthCard(Card.Type.EXEMPT));
        }
        STW_CARDS = shuffleDeck(deck, 21);

        deck = new ArrayList<>(6);
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_SALESPERSON, 20000, 50000, 5000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_MECHANIC, 30000, 60000, 10000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_POLICE_OFFICER, 40000, 70000, 15000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_ENTERTAINER, 50000, 1000000, 20000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_ATHLETE, 60000, 1000000, 25000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_HAIR_STYLIST, 30000, 60000, 10000));
        CAREER_CARDS = shuffleDeck(deck, 6);

        deck = new ArrayList<>(6);
        deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_ACCOUNTANT, 70000, 110000, 30000));
        deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_COMPUTER_DESIGNER, 50000, 80000, 20000));
        deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_DOCTOR, 100000, 1000000, 45000));
        deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_LAWYER, 90000, 1000000, 40000));
        deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_VETERINARIAN, 80000, 120000, 35000));
        deck.add(new CareerCard(Card.Type.COLLEGE_CAREER, CareerCard.CareerType.TYPE_TEACHER, 40000, 70000, 15000));
        COLLEGE_CAREER_CARDS = shuffleDeck(deck, 6);

        HouseCard shc1 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.TS, 180000, 200000);
        HouseCard shc2 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.MH, 80000, 80000);
        HouseCard shc3 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.LC, 120000, 140000);
        HouseCard shc4 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.RS, 140000, 160000);
        HouseCard shc5 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.C, 100000, 105000);
        HouseCard shc6 = new HouseCard(Card.Type.STARTER_HOME, HouseCard.HomeType.SC, 160000, 180000);

        String sh1 = shc1.getDescription();
        String sh2 = shc2.getDescription();
        String sh3 = shc3.getDescription();
        String sh4 = shc4.getDescription();
        String sh5 = shc5.getDescription();
        String sh6 = shc6.getDescription();

        HouseCard bhc1 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.EC, 400000, 400000);
        HouseCard bhc2 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.PS, 700000, 700000);
        HouseCard bhc3 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.LMR, 600000, 600000);
        HouseCard bhc4 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.M, 800000, 800000);
        HouseCard bhc5 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.MV, 500000, 500000);
        HouseCard bhc6 = new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.DWRV, 300000, 300000);

        String bh1 = bhc1.getDescription();
        String bh2 = bhc2.getDescription();
        String bh3 = bhc3.getDescription();
        String bh4 = bhc4.getDescription();
        String bh5 = bhc5.getDescription();
        String bh6 = bhc6.getDescription();

        STARTER_HOMES_OPTIONS = new String[]{sh1, sh2, sh3, sh4, sh5, sh6};
        BETTER_HOMES_OPTIONS = new String[]{"None", bh1, bh2, bh3, bh4, bh5, bh6};

        STARTER_HOMES.add(shc1);
        STARTER_HOMES.add(shc2);
        STARTER_HOMES.add(shc3);
        STARTER_HOMES.add(shc4);
        STARTER_HOMES.add(shc5);
        STARTER_HOMES.add(shc6);

        BETTER_HOMES.add(bhc1);
        BETTER_HOMES.add(bhc2);
        BETTER_HOMES.add(bhc3);
        BETTER_HOMES.add(bhc4);
        BETTER_HOMES.add(bhc5);
        BETTER_HOMES.add(bhc6);

        ArrayList<LifeTile> tiles = new ArrayList<>(25);
        for (int i = 0; i < 7; i++) {
            tiles.add(new LifeTile(10000));
        }
        for (int i = 0; i < 6; i++) {
            tiles.add(new LifeTile(20000));
        }
        for (int i = 0; i < 5; i++) {
            tiles.add(new LifeTile(30000));
        }
        for (int i = 0; i < 4; i++) {
            tiles.add(new LifeTile(40000));
        }
        for (int i = 0; i < 3; i++) {
            tiles.add(new LifeTile(50000));
        }
        LIFE_TILES = shuffleLifeTiles(tiles, 25);

    }

    private List<Card> shuffleDeck(List<Card> deck, int total) {
        List<Card> shuffled = new ArrayList<>(total);
        Random rand = new Random();
        for (int i = 0; i < total; i++) {
            int r = rand.nextInt(deck.size());
            Card c = deck.remove(r);
            shuffled.add(c);
        }
        return shuffled;
    }

    private List<LifeTile> shuffleLifeTiles(List<LifeTile> deck, int total) {
        List<LifeTile> shuffled = new ArrayList<>(total);
        Random rand = new Random();
        for (int i = 0; i < total; i++) {
            int r = rand.nextInt(deck.size());
            LifeTile c = deck.remove(r);
            shuffled.add(c);
        }
        return shuffled;
    }

    public void initPlayers() {

        log("Spin the wheel to see who goes first.");

        //spin to see who goes first
        Map<Integer, Player> map = new HashMap<>();
        for (Player player : players) {
            int roll = 0;
            while (true) {
                roll = spinWheel(player, false);
                //cannot contain duplicates
                if (!map.containsKey(roll)) {
                    map.put(roll, player);
                    break;
                } else {
                    log(player, player.getName() + " will spin again since someone already rolled a " + roll);
                }
            }
        }
        List<Integer> sortedKeys = new ArrayList<>(map.keySet());
        Collections.sort(sortedKeys);
        Collections.reverse(sortedKeys);

        List<Player> tempPlayers = new ArrayList<>();
        for (Integer roll : sortedKeys) {
            Player player = map.get(roll);
            tempPlayers.add(player);
        }
        players = tempPlayers;

        // deal the stw cards, each player gets 3 at start
        for (Player player : players) {
            player.addCard((ShareTheWealthCard) STW_CARDS.remove(0));
            player.addCard((ShareTheWealthCard) STW_CARDS.remove(0));
            player.addCard((ShareTheWealthCard) STW_CARDS.remove(0));
        }

        for (Player player : players) {
            player.setMoney(10000);
        }

    }

    public void addPlayer(String name, Actor actor, boolean isComputer) {
        Player player = new Player(name, actor, isComputer);
        actor.setPlayer(player);
        players.add(player);
    }

    public Iterator<Player> getPlayers() {
        return players.iterator();
    }

    public Player currentPlayer() {
        return players.get(currentPlayerIdx);
    }

    public void play() {

        Player player = currentPlayer();

        try {

            if (!gameOver) {

                if (player.isMissTurn()) {
                    player.setMissTurn(false);
                    return;
                }

                if (player.getLocation() == null) {

                    boolean college = false;
                    if (!player.isComputerPlayer()) {
                        String[] options = {"Go to COLLEGE", "Start a CAREER"};
                        int n = optionDialog(player.getName() + ", where will you start on the path of life?", options);
                        college = (n == 0);
                    } else {
                        college = new Random().nextBoolean();
                    }

                    player.setLocation(college ? LifeMap.START_COLLEGE_LOCATION : LifeMap.START_CAREER_LOCATION);

                    if (college) {
                        player.addMoney(100000);
                        player.setLoans(player.getLoans() + 5);
                        player.setHasCollegeDegree(true);
                    } else {
                        CareerCard career = pullCareerCard(false);
                        log(player, player.getName() + " picked a career " + career.getDescription());
                        player.setCareer(career);
                    }
                }

                if (!player.isComputerPlayer() && player.getLtiCardValue() == -1) {
                    int n = optionDialog(player.getName() + ", do you want to buy a Long Term investment?", NUMBER_OPTIONS);
                    if (n != 0) {
                        player.setLtiCardValue(n);
                    }
                } else if (player.getLtiCardValue() == -1) {
                    int pick = new Random().nextInt(9);
                    player.setLtiCardValue(pick + 1);
                }

                if (player.isRetired()) {

                    spinWheel(player, true);

                    LifeTile tile = pullLifeTile(player);
                    if (tile != null) {
                        player.addLifeTile(tile);
                    }

                    if (allPlayersRetired()) {
                        gameOver = true;
                    }

                    return;
                }

                this.screen.setMapPixelCoords(player.getLocation().getX(), player.getLocation().getY());
                this.screen.move(player.getActor(), player.getLocation().getX(), player.getLocation().getY());

                int roll = spinWheel(player, true);

                for (int i = 0; i < roll; i++) {

                    if (advance(player)) {
                        i = 0;
                        roll = spinWheel(player, true);
                    }

                    if (player.isRetired()) {
                        break;
                    }

                }

                Location finalLocation = player.getLocation();

                screen.setMapPixelCoords(finalLocation.getX(), finalLocation.getY());

                log(player, finalLocation.getTile().getType() + " - " + finalLocation.getTile().getDescription());

                switch (finalLocation.getTile().getType()) {
                    case COLLECT: {
                        int amount = finalLocation.getTile().getAmount();
                        for (Player p : players) {
                            if (p.getName().equals(player.getName())) {
                                continue;
                            }
                            boolean choice = false;
                            if (p.hasPayYouCard()) {
                                choice = yesNoChoice(player, player.getName() + ", do you want to use your COLLECT card to collect half of $" + amount + "?");
                            } else {
                                continue;
                            }
                            if (choice) {
                                amount = amount / 2;
                                log(p, p.getName() + " used a PAY YOU card and took half.");
                                p.addMoney(amount);
                                replaceSTWCard(p.usePayCard(Card.Type.PAY_YOU));
                                break;
                            }
                        }
                        player.addMoney(amount);
                        break;
                    }
                    case LAWSUIT: {
                        sueAnotherPlayer(player);
                        break;
                    }
                    case TAXES_DUE: {
                        int amount = player.getCareer().getTaxes_due();
                        player.subtractMoney(amount);
                        break;
                    }
                    case TAX_REFUND: {
                        int amount = player.getCareer().getTaxes_due();
                        player.addMoney(amount);
                        break;
                    }
                    case PAY: {
                        int amount = finalLocation.getTile().getAmount();
                        if (player.hasPayBankCard()) {
                            tryPayBankCard(player, amount);
                        }
                        break;
                    }
                    case LIFE: {
                        LifeTile tile = pullLifeTile(player);
                        if (tile != null) {
                            player.addLifeTile(tile);
                        }
                        break;
                    }
                    case MOVE_TO_MARRIED: {
                        player.setLocation(LifeMap.MARRIED_LOCATION);
                        screen.setMapPixelCoords(player.getLocation().getX(), player.getLocation().getY());
                        this.screen.move(player.getActor(), player.getLocation().getX(), player.getLocation().getY());
                        break;
                    }
                    case MISS_TURN: {
                        player.setMissTurn(true);
                        break;
                    }
                    case SPIN_AGAIN: {
                        for (Player p : players) {
                            if (p.getName().equals(player.getName())) {
                                continue;
                            }
                            p.setMissTurn(true);
                        }
                        break;
                    }
                    case TAKE_STW_CARD: {
                        int pick = new Random().nextInt(STW_CARDS.size() - 1);
                        ShareTheWealthCard card = (ShareTheWealthCard) STW_CARDS.get(pick);
                        player.addCard(card);
                        break;
                    }
                    case LOSE_JOB: {
                        CareerCard career = pullCareerCard(false);
                        log(player, player.getName() + " picked a career " + career.getDescription());
                        player.setCareer(career);
                        player.setHasCollegeDegree(false);
                        break;
                    }
                    case SPIN_TO_WIN: {
                        spinToWin();
                        break;
                    }
                    case LIFE_ADD_CHILDREN: {
                        int kids = finalLocation.getTile().getAmount();
                        player.addChild(kids);
                        LifeTile tile = pullLifeTile(player);
                        if (tile != null) {
                            player.addLifeTile(tile);
                        }
                        for (Player p : players) {
                            if (p.getName().equals(player.getName())) {
                                continue;
                            }
                            if (p.getMoney() < 5000) {
                                p.getLoanFromBank();
                            }
                            p.subtractMoney(5000);
                            player.addMoney(5000);
                            log(player, player.getName() + " received a $5000 gift from " + p.getName());
                        }
                        break;
                    }
                    case COLLECT_FOR_EACH_CHILD_FROM_OTHERS: {
                        for (Player p : players) {
                            if (p.getName().equals(player.getName())) {
                                continue;
                            }
                            int count = p.getChildCount();
                            if (count == 0) {
                                continue;
                            }
                            int amount = 5000 * count;
                            if (p.getMoney() < amount) {
                                p.getLoanFromBank();
                            }
                            p.subtractMoney(amount);
                            player.addMoney(amount);
                            log(player, player.getName() + " received $" + amount + " from " + p.getName());
                        }
                        break;
                    }
                    case PAY_PER_CHILD: {
                        if (player.getChildCount() > 0) {
                            if (player.getMoney() < 5000 * player.getChildCount()) {
                                player.getLoanFromBank();
                            }
                            player.subtractMoney(5000 * player.getChildCount());
                        }
                        break;
                    }
                    case PENSION: {
                        int spin = spinWheel(player, false);
                        player.addMoney(spin * 10000);
                        log(player, player.getName() + " received a pension of $" + (spin * 10000));
                        break;
                    }
                }

            } else {
                //show stats and winner
            }

        } finally {

            currentPlayerIdx++;
            if (currentPlayerIdx >= players.size()) {
                currentPlayerIdx = 0;
            }
        }

    }

    private boolean advance(Player player) {

        boolean spinAgain = false;

        Location loc = player.getLocation();
        Location next = loc.getConnections().first().getToNode();

        if (next == null) {
            switch (loc.getTile().getType()) {

                case RETURN_TO_SCHOOL: {
                    String[] options = {"Return to School", "Continue on the path of life"};
                    boolean choice = choosePath(player, player.getName() + ", which path will you take?", options);
                    log(player, player.getName() + " chose " + (choice ? options[0] : options[1]) + ".");
                    next = (choice ? LifeMap.BACK_TO_SCHOOL_SELECTED : LifeMap.BACK_TO_SCHOOL_NOT_SELECTED);
                    if (choice) {
                        player.subtractMoney(50000);
                    }
                    break;
                }

                case TAKE_FAMILY_PATH: {
                    String[] options = {"Take the Family Path", "Continue on the path of life"};
                    boolean choice = choosePath(player, player.getName() + ", which path will you take?", options);
                    log(player, player.getName() + " chose " + (choice ? options[0] : options[1]) + ".");
                    next = (choice ? LifeMap.FAMILY_PATH_SELECTED : LifeMap.FAMILY_PATH_NOT_SELECTED);
                    break;
                }

                case TAKE_RISKY_PATH: {
                    String[] options = {"Take the Risky Path", "Continue on the path of life"};
                    boolean choice = choosePath(player, player.getName() + ", which path will you take?", options);
                    log(player, player.getName() + " chose " + (choice ? options[0] : options[1]) + ".");
                    next = (choice ? LifeMap.RISKY_PATH_SELECTED : LifeMap.RISKY_PATH_NOT_SELECTED);
                    break;
                }

                case RETIRE:
                    //repay loans
                    player.subtractMoney(player.getLoans() * 20000);
                    player.setLoans(0);
                    //sell house
                    if (player.getHouse() != null) {
                        int sale = player.getHouse().getSellFor();
                        player.addMoney(sale);
                    }
                    player.setHouse(null);
                    //set null career
                    player.setCareer(null);
                    //set retired boolean on player
                    player.setRetired(true);
                    // collect LTI each spin after this
                    //still play STW cards
                    //collect 10000 times children
                    player.addMoney(10000 * player.getChildCount());
                    //choose ME or CA
                    String[] options = {"Millionaire Estates", "Countryside Acres"};
                    boolean choice = choosePath(player, player.getName() + ", which retirement home will you choose?", options);
                    log(player, player.getName() + " chose " + (choice ? options[0] : options[1]) + ".");
                    player.setRetireeHome((choice ? options[0] : options[1]));
                    return false;

            }
        }

        player.setLocation(next);

        this.screen.move(player.getActor(), player.getLocation().getX(), player.getLocation().getY());

        //check for ORANGE spaces or PAYDAY spaces
        switch (loc.getTile()
                .getType()) {
            case PAYDAY: {
                int payraise = loc.getTile().getAmount();
                int salary = player.getSalary();
                if (payraise > 0) {
                    player.addPayRaise(payraise);
                }
                SoundEffect.CASH.play();
                player.addMoney(salary);
                if (!player.isComputerPlayer()) {
                    log(player, player.getName() + " got paid $" + (salary + payraise) + " (payraise $" + payraise + ")");
                }
                break;
            }
            case COLLEGE_CAREER_CHOICE: {
                CareerCard career = pullCareerCard(true);
                log(player, player.getName() + " picked a career " + career.getDescription());
                player.setCareer(career);
                spinAgain = true;
                break;
            }
            case GET_MARRIED: {
                getMarried(player);
                spinAgain = true;
                break;
            }
            case CHANGE_CAREER: {
                changeCareer(player);
                spinAgain = true;
                break;
            }
            case BUY_STARTER_HOME: {
                selectStarterHome(player);
                spinAgain = true;
                break;
            }
            case BUY_BETTER_HOME: {
                selectBetterHome(player);
                spinAgain = true;
                break;
            }
            case RETURN_TO_SCHOOL: {
                spinAgain = true;
                break;
            }
            case TAKE_FAMILY_PATH: {
                spinAgain = true;
                break;
            }
            case TAKE_RISKY_PATH: {
                spinAgain = true;
                break;
            }
            case RETIRE: {
                //repay loans
                player.subtractMoney(player.getLoans() * 20000);
                player.setLoans(0);
                //sell house
                if (player.getHouse() != null) {
                    int sale = player.getHouse().getSellFor();
                    player.addMoney(sale);
                }
                player.setHouse(null);
                //set null career
                player.setCareer(null);
                //set retired boolean on player
                player.setRetired(true);
                // collect LTI each spin after this
                //still play STW cards
                //collect 10000 times children
                player.addMoney(10000 * player.getChildCount());
                //choose ME or CA
                String[] options = {"Millionaire Estates", "Countryside Acres"};
                boolean choice = choosePath(player, player.getName() + ", which retirement home will you choose?", options);
                log(player, player.getName() + " chose " + (choice ? options[0] : options[1]) + ".");
                player.setRetireeHome((choice ? options[0] : options[1]));
                break;
            }
        }

        return spinAgain;

    }

    private void log(Player player, String text) {
        if (Life.hud != null) {
            Life.hud.add(player.getName() + ": " + text, player.getActor().getType().getColor());
        } else {
            System.out.println(player.getName() + ": " + text);
        }
    }

    private void log(String text) {
        if (Life.hud != null) {
            Life.hud.add(text, Color.WHITE);
        } else {
            System.out.println(text);
        }
    }

    private int optionDialog(String text, String[] options) {
        final AtomicInteger result = new AtomicInteger();
        final CountDownLatch latch = new CountDownLatch(1);
        Dialog popup = new Dialog("", Life.skin, "dialog") {
            @Override
            public void result(Object object) {
                result.set((int) object);
                latch.countDown();
            }
        };
        popup.text(text);
        for (int i = 0; i < options.length; i++) {
            popup.button(options[i], i);
        }
        popup.show(screen.getStage());
        try {
            latch.await();
        } catch (Exception e) {
        }
        return result.get();
    }

    private int spinWheel(Player player, boolean check_lti) {

        int seed = Life.dice.roll();
        int roll = seed;

        if (!player.isComputerPlayer()) {
            //SoundEffect.SPIN.play();
            //roll = this.world.spinWheel(seed);
        }

        log(player, player.getName() + " spinned a " + roll);

        if (check_lti) {
            for (Player p : players) {
                if (p.getLtiCardValue() == roll) {
                    p.addMoney(5000);
                    log(p, p.getName() + " received a $5000 payout from Long Term Investment.");
                }
            }
        }

        return roll;

    }

    private boolean choosePath(Player player, String text, String[] options) {
        boolean choice = false;
        if (!player.isComputerPlayer()) {
            int n = optionDialog(text, options);
            choice = (n == 0);
        } else {
            choice = new Random().nextBoolean();
        }
        return choice;
    }

    private boolean yesNoChoice(Player player, String text) {
        boolean choice = false;
        if (!player.isComputerPlayer()) {
            final AtomicBoolean result = new AtomicBoolean();
            final CountDownLatch latch = new CountDownLatch(1);
            Dialog popup = new Dialog("", Life.skin, "dialog") {
                @Override
                public void result(Object object) {
                    result.set((boolean) object);
                    latch.countDown();
                }
            };
            popup.text(text);
            popup.button("Yes", true);
            popup.button("No", false);
            popup.show(screen.getStage());
            try {
                latch.await();
            } catch (Exception e) {
            }
            return result.get();
        } else {
            choice = new Random().nextBoolean();
        }
        return choice;

    }

    private void changeCareer(Player player) {
        boolean change = false;
        if (!player.isComputerPlayer()) {
            String[] options = {"Change Career", "Take a $20,000 Pay Raise"};
            int n = optionDialog(player.getName() + ", which will choose?", options);
            change = (n == 0);
        } else {
            change = new Random().nextBoolean();
        }

        if (change) {
            boolean college = new Random().nextBoolean();
            CareerCard career = pullCareerCard(college);
            log(player, player.getName() + " picked a career " + career.getDescription());
            player.setCareer(career);
            player.setHasCollegeDegree(college);
        } else {
            player.addPayRaise(20000);
        }
    }

    private LifeTile pullLifeTile(Player puller) {

        if (LIFE_TILES.isEmpty()) {

            List<Player> others = new ArrayList<>();
            for (Player player : players) {
                if (player.getName().equals(puller.getName()) || (player.isRetired() && "Countryside Acres".equals(player.getRetireeHome()))) {
                    continue;
                }
                if (player.getLifeCardCount() < 1) {
                    continue;
                }
                others.add(player);
            }

            if (others.isEmpty()) {
                return null;
            }

            if (!puller.isComputerPlayer()) {

                String[] choices = new String[others.size()];
                int i = 0;
                for (Player player : others) {
                    choices[i] = player.getName();
                    i++;
                }
                int n = optionDialog("There are no more LIFE tiles in the bank. Select another player to take a LIFE tile from.", choices);
                Player choice = null;
                for (Player player : others) {
                    if (player.getName().equals(choices[n])) {
                        choice = player;
                    }
                }

                log(puller, puller.getName() + " took a LIFE Card from " + choice.getName() + ".");

                return choice.removeLifeTile();

            } else {
                //pick the player with the most life cards
                Player choice = others.get(0);
                for (Player player : others) {
                    if (player.getLifeCardCount() > choice.getLifeCardCount()) {
                        choice = player;
                    }
                }
                log(puller, puller.getName() + " took a LIFE Card from " + choice.getName() + ".");

                return choice.removeLifeTile();
            }

        }
        return LIFE_TILES.remove(0);
    }

    private int sueAnotherPlayer(Player player) {

        int amount = 100000;
        String[] others = new String[players.size() - 1];
        int i = 0;
        for (Player p : players) {
            if (p.getName().equals(player.getName())) {
                continue;
            }
            others[i] = p.getName();
            i++;
        }

        Player choice = null;
        if (!player.isComputerPlayer()) {
            int n = optionDialog("LAWSUIT - Select another player to sue.", others);
            for (Player p : players) {
                if (p.getName().equals(others[n])) {
                    choice = p;
                }
            }
        } else {
            //computer sues the player with most money
            for (Player p : players) {
                if (p.getName().equals(player.getName())) {
                    continue;
                }
                if (choice == null || choice.getMoney() > p.getMoney()) {
                    choice = p;
                }
            }
        }

        ShareTheWealthCard exemption = choice.hasExemptionCard();
        if (exemption != null) {
            log(choice, choice.getName() + " was sued but had an exemption card and used it.");
            replaceSTWCard(exemption);
            amount = 0;
        } else {
            log(choice, choice.getName() + " paid $100,000 in a lawsuit to " + player.getName());
        }

        player.addMoney(amount);
        choice.subtractMoney(amount);

        return amount;
    }

    private void tryPayBankCard(Player player, int amount) {

        boolean choosePay = yesNoChoice(player, player.getName() + ", do you want to use your Share The Wealth card so someone else pays half of $" + amount + "?");
        if (choosePay) {

            String[] others = new String[players.size() - 1];
            int i = 0;
            for (Player p : players) {
                if (p.getName().equals(player.getName())) {
                    continue;
                }
                others[i] = p.getName();
                i++;
            }

            Player choice = null;
            if (!player.isComputerPlayer()) {
                int n = optionDialog("Pick which player you want to pay half of the $" + amount + ".", others);
                for (Player p : players) {
                    if (p.getName().equals(others[n])) {
                        choice = p;
                    }
                }
            } else {
                //computer chooses the player with most money
                for (Player p : players) {
                    if (p.getName().equals(player.getName())) {
                        continue;
                    }
                    if (choice == null || choice.getMoney() > p.getMoney()) {
                        choice = p;
                    }
                }
            }

            replaceSTWCard(player.usePayCard(Card.Type.PAY_BANK));

            ShareTheWealthCard exemption = choice.hasExemptionCard();
            if (exemption != null) {
                log(choice, choice.getName() + " had an exemption card and used it.");
                replaceSTWCard(exemption);
            } else {
                amount = amount / 2;
                log(choice, choice.getName() + " paid half to the bank!");
                choice.subtractMoney(amount);
            }
        }

        player.subtractMoney(amount);

    }

    void replaceSTWCard(ShareTheWealthCard card) {
        STW_CARDS.add(card);
    }

    private void selectStarterHome(Player player) {
        String choice = null;
        if (!player.isComputerPlayer()) {
            int n = optionDialog("Pick your STARTER HOME", STARTER_HOMES_OPTIONS);
            choice = STARTER_HOMES_OPTIONS[n];
        } else {
            choice = STARTER_HOMES_OPTIONS[new Random().nextInt(5)];
        }
        log(player, player.getName() + " chose " + choice + ".");
        if (choice == null) {
            player.setHouse(STARTER_HOMES.get(0));
            player.subtractMoney(STARTER_HOMES.get(0).getValue());
        } else {
            for (HouseCard card : STARTER_HOMES) {
                boolean eq = card.getDescription().equals(choice);
                if (eq) {
                    player.setHouse(card);
                    player.subtractMoney(card.getValue());
                }
            }
        }
    }

    private void selectBetterHome(Player player) {
        String choice = null;
        if (!player.isComputerPlayer()) {
            int n = optionDialog("Select a BETTER HOME or else CANCEL.", BETTER_HOMES_OPTIONS);
            if (n == 0) {
                return;
            }
            choice = BETTER_HOMES_OPTIONS[n];
        } else {
            choice = BETTER_HOMES_OPTIONS[new Random().nextInt(5)];
        }
        log(player, player.getName() + " chose " + choice + ".");
        for (HouseCard card : BETTER_HOMES) {
            boolean eq = card.getDescription().equals(choice);
            if (eq) {
                if (player.getHouse() != null) {
                    player.addMoney(player.getHouse().getSellFor());
                }
                player.setHouse(card);
                player.subtractMoney(card.getValue());
            }
        }
    }

    private void spinToWin() {
        for (Player p : players) {
            List<Integer> select = new ArrayList<>();
            if (!p.isComputerPlayer()) {
                CountDownLatch latch = new CountDownLatch(1);
                SpinToWinDialog dialog = new SpinToWinDialog(p, select, this, screen, latch);
                dialog.show(screen.getStage());
                try {
                    latch.await();
                } catch (Exception e) {
                }
            } else {
                boolean play = true;//new Random().nextBoolean();
                if (!play) {
                    continue;
                }
                if (p.hasSPIN4Card()) {
                    while (select.size() < 5) {
                        int pick = Life.dice.roll();
                        if (!select.contains(pick)) {
                            select.add(pick);
                        }
                    }
                    ShareTheWealthCard card = p.useSPINCard(Card.Type.SPIN4);
                    replaceSTWCard(card);
                } else if (p.hasSPIN2Card()) {
                    while (select.size() < 3) {
                        int pick = Life.dice.roll();
                        if (!select.contains(pick)) {
                            select.add(pick);
                        }
                    }
                    ShareTheWealthCard card = p.useSPINCard(Card.Type.SPIN2);
                    replaceSTWCard(card);
                } else {
                    select.add(Life.dice.roll());
                }
                boolean big_wager = new Random().nextBoolean();
                p.setSpintoWinWager(big_wager ? 20000 : 5000);

                String selString = "";
                for (Integer pick : select) {
                    selString += pick + ", ";
                }
                log(p, p.getName() + " selected " + selString + " and is wagering $" + p.getSpintoWinWager());
            }

            if (select.contains(-1)) {
                continue; //did not play
            }
            int spin = spinWheel(p, false);
            if (select.contains(spin)) {
                p.addMoney(p.getSpintoWinWager() * 10);
                log(p, p.getName() + " won $" + (p.getSpintoWinWager() * 10));
            } else {
                p.subtractMoney(p.getSpintoWinWager());
                log(p, p.getName() + " lost the wager.");
            }
        }
    }

    private CareerCard pullCareerCard(boolean requireCollege) {
        CareerCard card = null;
        int index = new Random().nextInt(6);
        if (requireCollege) {
            card = (CareerCard) COLLEGE_CAREER_CARDS.get(index);
        } else {
            card = (CareerCard) CAREER_CARDS.get(index);
        }
        return card;
    }

    private void getMarried(Player player) {
        player.setMarried(true);
        LifeTile tile = pullLifeTile(player);
        if (tile != null) {
            player.addLifeTile(tile);
        }
        int spin = spinWheel(player, false);
        int amount = 0;
        if (spin > 7) {
            amount = 10000;
        } else if (spin > 4) {
            amount = 5000;
        }
        String text = player.getName() + " spinned a " + spin;
        if (amount > 0) {
            text = text + ". Each player will give you $" + amount + " as a wedding gift.";
        } else {
            text = text + " and does not receive any wedding gifts.";
        }
        log(player, text);
        for (Player p : players) {
            if (p == player) {
                continue;
            }
            p.subtractMoney(amount);
            player.addMoney(amount);
        }
    }

    public boolean allPlayersRetired() {
        boolean all_retired = true;
        for (Player player : players) {
            if (!player.isRetired()) {
                all_retired = false;
            }
        }
        return all_retired;
    }

    private void setDebugStartPositions(Location loc) {

//        for (Actor actor : this.world.actors) {
//            actor.setCurrentTile(loc.getX(), loc.getY());
//        }
//        for (Player player : players) {
//            player.setPlayerLocation(loc);
//            player.setCareer((CareerCard) CAREER_CARDS.get(0));
//        }
    }

}
