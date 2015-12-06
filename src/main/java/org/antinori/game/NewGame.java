package org.antinori.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.antinori.game.cards.Card;
import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;
import org.antinori.game.cards.LifeTile;
import org.antinori.game.cards.LongTermInvestmentCard;
import org.antinori.game.cards.ShareTheWealthCard;

public class NewGame implements GameInterface {

    public boolean gameOver = false;

    private ArrayList<Player> players = new ArrayList<Player>(6);
    private Player currentPlayer = null;

    private ArrayList<Card> STW_CARDS = new ArrayList<Card>(21);
    private ArrayList<Card> CAREER_CARDS = new ArrayList<Card>(6);
    private ArrayList<Card> COLLEGE_CAREER_CARDS = new ArrayList<Card>(6);
    private ArrayList<LifeTile> LIFE_TILES = new ArrayList<LifeTile>(25);

    public static String[] NUMBER_OPTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    String[] STARTER_HOMES_OPTIONS = null;
    String[] BETTER_HOMES_OPTIONS = null;
    ArrayList<HouseCard> STARTER_HOMES = new ArrayList<HouseCard>(6);
    ArrayList<HouseCard> BETTER_HOMES = new ArrayList<HouseCard>(6);

    public NewGame() {
    }

    public static void main(String[] args) {
        NewGame game = new NewGame();
        game.createDecks();

        //int n = JOptionPane.showOptionDialog(null, "Do you want to buy a BETTER HOME?", "", 
        //		JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, BETTER_HOMES_OPTIONS, BETTER_HOMES_OPTIONS[0]);
        String choice = (String) JOptionPane.showInputDialog(null, "Pick the home", "TEST",
                JOptionPane.QUESTION_MESSAGE, null, game.BETTER_HOMES_OPTIONS, game.BETTER_HOMES_OPTIONS[0]);

        for (HouseCard card : game.BETTER_HOMES) {
            boolean eq = card.getDescription().equals(choice);
        }

        //ArrayList<Card> deck = new ArrayList<Card>(6);
        //deck.add(new HouseCard(Card.Type.HOUSE, HouseCard.HomeType.EC, 400000, 400000));
        //boolean flag1 = deck.contains(game.BETTER_HOMES[0].getDescription());
        //System.out.println("1="+flag1);
        //boolean flag2 = game.BETTER_HOMES[0].getDescription().equals(deck.get(0).getDescription());
        //System.out.println("2="+flag2);
    }

    public void createDecks() {

        ArrayList<Card> deck = new ArrayList<Card>(21);
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

        deck = new ArrayList<Card>(6);
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_SALESPERSON, 20000, 50000, 5000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_MECHANIC, 30000, 60000, 10000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_POLICE_OFFICER, 40000, 70000, 15000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_ENTERTAINER, 50000, 1000000, 20000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_ATHLETE, 60000, 1000000, 25000));
        deck.add(new CareerCard(Card.Type.CAREER, CareerCard.CareerType.TYPE_HAIR_STYLIST, 30000, 60000, 10000));
        CAREER_CARDS = shuffleDeck(deck, 6);

        deck = new ArrayList<Card>(6);
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

        String[] sh = {sh1, sh2, sh3, sh4, sh5, sh6};
        STARTER_HOMES_OPTIONS = sh;
        String[] bh = {bh1, bh2, bh3, bh4, bh5, bh6};
        BETTER_HOMES_OPTIONS = bh;

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

        ArrayList<LifeTile> tiles = new ArrayList<LifeTile>(25);
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

    public ArrayList<Card> shuffleDeck(ArrayList<Card> deck, int total) {
        ArrayList<Card> shuffled = new ArrayList<Card>(total);
        Random rand = new Random();
        for (int i = 0; i < total; i++) {
            int r = rand.nextInt(deck.size());
            Card c = deck.remove(r);
            shuffled.add(c);
        }
        return shuffled;
    }

    public ArrayList<LifeTile> shuffleLifeTiles(ArrayList<LifeTile> deck, int total) {
        ArrayList<LifeTile> shuffled = new ArrayList<LifeTile>(total);
        Random rand = new Random();
        for (int i = 0; i < total; i++) {
            int r = rand.nextInt(deck.size());
            LifeTile c = deck.remove(r);
            shuffled.add(c);
        }
        return shuffled;
    }

    public String initPlayers() throws Exception {

        if (STW_CARDS == null) {
            throw new Exception("Shuffled Deck is null.");
        }

        if (players == null || players.size() < 2) {
            throw new Exception("Not enough players.");
        }

        // deal the stw cards, each player gets 3 at start
        for (Player player : players) {
            player.addCard((ShareTheWealthCard) STW_CARDS.remove(0));
            player.addCard((ShareTheWealthCard) STW_CARDS.remove(0));
            player.addCard((ShareTheWealthCard) STW_CARDS.remove(0));
        }

        for (Player player : players) {
            player.setMoney(10000);
        }

        String msg = "Share the Wealth Cards have been dealt, and the players are:\n";
        for (Player player : players) {
            msg += player.toString() + "\n";
        }

        System.out.println(msg);

        return msg;
    }

    public void startGame() throws Exception {

        while (!gameOver) {

            for (Player player : players) {

                if (player.isMiss_turn()) {
                    player.setMiss_turn(false);
                    continue;
                }

                currentPlayer = player;

                LifeMain.playerInfoPanel.setPlayer(player);
                LifeMain.statusPanel.setText(players);

                if (player.getPlayerLocation() == null) {

                    boolean college = false;
                    if (!player.isComputerPlayer()) {
                        Object[] options = {"Go to COLLEGE", "Start a CAREER"};
                        int n = JOptionPane.showOptionDialog(null, player.getPlayerName() + ", where will you start on the path of life?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        college = (n == 0 ? true : false);
                    } else {
                        college = new Random().nextBoolean();
                    }

                    player.setPlayerLocation(college ? LifeMap.START_COLLEGE_LOCATION : LifeMap.START_CAREER_LOCATION);

                    if (college) {
                        player.addMoney(100000);
                        player.setLoans(player.getLoans() + 5);
                        player.setHasCollegeDegree(true);
                    } else {
                        CareerCard career = pullCareerCard(false);
                        messageDialog(player.getPlayerName() + " picked a career\n\n " + career.getDescription());
                        player.setCareer(career);
                    }
                }

                if (!player.isComputerPlayer() && player.getLti_card_value() == 0) {
                    int n = JOptionPane.showOptionDialog(null, "Do you want to buy a Long Term investment?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, NUMBER_OPTIONS, NUMBER_OPTIONS[0]);
                    if (n != -1) {
                        player.setLti_card_value(n + 1);
                    }
                } else if (player.getLti_card_value() == 0) {
                    int pick = new Random().nextInt(8);
                    player.setLti_card_value(pick + 1);
                }

                if (player.isRetired()) {

                    continue;
                }

                int roll = spinWheel(player, true);

                boolean spin_again = false;

                for (int i = 0; i < roll; i++) {

                    Location loc = player.getPlayerLocation();
                    Location next = loc.getNextLocation();

                    //check for ORANGE spaces or PAYDAY spaces
                    switch (loc.getTile().getType()) {
                        case PAYDAY: {
                            int payraise = loc.getTile().getAmount();
                            int salary = player.getSalary();
                            if (payraise > 0) {
                                player.addPayRaise(payraise);
                            }
                            SoundEffect.CASH.play();
                            player.addMoney(salary);
                            if (!player.isComputerPlayer()) {
                                messageDialog(player.getPlayerName() + " got paid $" + (salary + payraise) + " (payraise $" + payraise + ")");
                            }
                            break;
                        }
                        case COLLEGE_CAREER_CHOICE: {
                            CareerCard career = pullCareerCard(true);
                            messageDialog(player.getPlayerName() + " picked a career\n\n " + career.getDescription());
                            player.setCareer(career);
                            spin_again = true;
                            break;
                        }
                        case GET_MARRIED: {
                            getMarried(player);
                            spin_again = true;
                            break;
                        }
                        case CHANGE_CAREER: {
                            changeCareer(player);
                            spin_again = true;
                            break;
                        }
                        case BUY_STARTER_HOME: {
                            selectStarterHome(player);
                            spin_again = true;
                            break;
                        }
                        case BUY_BETTER_HOME: {
                            selectBetterHome(player);
                            spin_again = true;
                            break;
                        }
                        case RETURN_TO_SCHOOL: {
                            Object[] options = {"Return to School", "Continue on the path of life"};
                            boolean choice = choosePath(player, options);
                            next = (choice ? LifeMap.BACK_TO_SCHOOL_SELECTED : LifeMap.BACK_TO_SCHOOL_NOT_SELECTED);
                            if (choice) {
                                player.subtractMoney(50000);
                            }
                            spin_again = true;
                            break;
                        }
                        case TAKE_FAMILY_PATH: {
                            Object[] options = {"Take the Family Path", "Continue on the path of life"};
                            boolean choice = choosePath(player, options);
                            next = (choice ? LifeMap.FAMILY_PATH_SELECTED : LifeMap.FAMILY_PATH_NOT_SELECTED);
                            spin_again = true;
                            break;
                        }
                        case TAKE_RISKY_PATH: {
                            Object[] options = {"Take the Risky Path", "Continue on the path of life"};
                            boolean choice = choosePath(player, options);
                            next = (choice ? LifeMap.RISKY_PATH_SELECTED : LifeMap.RISKY_PATH_NOT_SELECTED);
                            spin_again = true;
                            break;
                        }
                        case RETIRE: {
                            //repay loans
                            player.subtractMoney(player.getLoans() * 20000);
                            player.setLoans(0);
                            //sell house
                            int sale = player.getHouse().getSellFor();
                            player.addMoney(sale);
                            player.setHouse(null);
                            //set null career
                            player.setCareer(null);
                            //set retired boolean on player
                            player.setRetired(true);
                            // collect LTI each spin after this
                            //still play STW cards
                            //collect 10000 times children
                            player.addMoney(10000 * player.getChild_count());
                            //choose ME or CA
                            Object[] options = {"Millionaire Estates", "Countryside Acres"};
                            String s = (String) JOptionPane.showInputDialog(null, "Where will you retire to?", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            player.setRetireeHome(s);
                            break;
                        }
                    }

                    if (spin_again) {
                        i = 0;
                        roll = spinWheel(player, true);
                        spin_again = false;
                    }

                    if (player.isRetired()) {
                        LifeMain.mapView.repaint();
                        break;
                    }

                    player.setPlayerLocation(next);
                    LifeMain.mapView.repaint();
                    Thread.sleep(1000);

                }

                Location finalLocation = player.getPlayerLocation();
                messageDialog(player.getPlayerName() + " landed on\n" + finalLocation.getTile().getType() + "\n" + finalLocation.getTile().getDescription());

                switch (finalLocation.getTile().getType()) {
                    case COLLECT: {
                        player.addMoney(finalLocation.getTile().getAmount());
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
                        player.subtractMoney(finalLocation.getTile().getAmount());
                        break;
                    }
                    case LIFE: {
                        player.addLifeTile(pullLifeTile());
                        break;
                    }
                    case MOVE_TO_MARRIED: {
                        player.setPlayerLocation(LifeMap.MARRIED_LOCATION);
                        break;
                    }
                    case MISS_TURN: {
                        player.setMiss_turn(true);
                        break;
                    }
                    case SPIN_AGAIN: {
                        for (Player p : players) {
                            if (p == currentPlayer) {
                                continue;
                            }
                            p.setMiss_turn(true);
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
                        messageDialog(player.getPlayerName() + " picked a career\n\n " + career.getDescription());
                        player.setCareer(career);
                        player.setHasCollegeDegree(false);
                        break;
                    }
                    case SPIN_TO_WIN: {
                        spinToWin();
                        break;
                    }
                    case LIFE_ADD_CHILDREN: {
                        player.addChild(finalLocation.getTile().getAmount());
                        player.addLifeTile(pullLifeTile());
                        for (Player p : players) {
                            if (p == currentPlayer) {
                                continue;
                            }
                            if (p.getMoney() < 5000) {
                                p.getLoanFromBank();
                            }
                            p.subtractMoney(5000);
                            player.addMoney(5000);
                        }
                        break;
                    }
                    case COLLECT_FOR_EACH_CHILD_FROM_OTHERS: {
                        for (Player p : players) {
                            if (p == currentPlayer) {
                                continue;
                            }
                            if (p.getMoney() < 5000 * p.getChild_count()) {
                                p.getLoanFromBank();
                            }
                            p.subtractMoney(5000 * p.getChild_count());
                            player.addMoney(5000 * p.getChild_count());
                        }
                        break;
                    }
                    case PAY_PER_CHILD: {
                        if (player.getMoney() < 5000 * player.getChild_count()) {
                            player.getLoanFromBank();
                        }
                        player.subtractMoney(5000 * player.getChild_count());
                        break;
                    }
                    case PENSION: {
                        int spin = spinWheel(player, false);
                        player.addMoney(spin * 10000);
                        messageDialog(player.getPlayerName() + " received a pension of $" + (spin * 10000));
                        break;
                    }
                }

            }
        }
    }

    public void messageDialog(String text2) {
        final String text = text2;
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, text);
                }
            });
        } catch (Exception e) {

        }
    }

    public int spinWheel(Player player, boolean check_lti) {

        int roll = LifeMain.dice.roll();
        SoundEffect.SPIN.play();
        LifeMain.wheelPanel.setSpin(roll);

        messageDialog(player.getPlayerName() + " spinned a " + roll);

        if (check_lti) {
            for (Player p : players) {
                if (p.getLti_card_value() == roll) {
                    p.addMoney(5000);
                    messageDialog(p.getPlayerName() + " received a payout from Long Term Investment of $5000.");
                }
            }
        }

        return roll;

    }

    public boolean choosePath(Player player, Object[] options) {
        boolean choice = false;
        if (!player.isComputerPlayer()) {
            int n = JOptionPane.showOptionDialog(null, player.getPlayerName() + ", which path will you take?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            choice = (n == 0 ? true : false);
        } else {
            choice = new Random().nextBoolean();
        }
        return choice;
    }

    public void changeCareer(Player player) {
        boolean change = false;
        if (!player.isComputerPlayer()) {
            String[] options = {"Change Career", "Take a $20,000 Pay Raise"};
            int n = JOptionPane.showOptionDialog(null, player.getPlayerName() + ", which will choose?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            change = (n == 0 ? true : false);
        } else {
            change = new Random().nextBoolean();
        }

        if (change) {
            boolean college = new Random().nextBoolean();
            CareerCard career = pullCareerCard(college);
            messageDialog(player.getPlayerName() + " picked a career\n\n " + career.getDescription());
            player.setCareer(career);
            player.setHasCollegeDegree(college);
        } else {
            player.addPayRaise(20000);
        }
    }

    public void addPlayer(String name, Color color, boolean isComputer) {
        Player player = new Player(name, color, isComputer);
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public LifeTile pullLifeTile() {
        if (LIFE_TILES.size() == 0) {
            Object[] others = new Object[players.size() - 1];
            int i = 0;
            for (Player player : players) {
                if (player == currentPlayer) {
                    continue;
                }
                others[i] = player;
                i++;
            }
            Player choice = (Player) JOptionPane.showInputDialog(null, "There are no more LIFE tiles in the bank.\nSelect another player to take a LIFE tile from.", "", JOptionPane.QUESTION_MESSAGE, null, others, others[0]);
            return choice.removeLifeTile();//safe to say at this point that all players have some life tiles, no one has none
        }
        return LIFE_TILES.remove(0);
    }

    public int sueAnotherPlayer(Player player) {

        int amount = 100000;
        Object[] others = new Object[players.size() - 1];
        int i = 0;
        for (Player p : players) {
            if (p == currentPlayer) {
                continue;
            }
            others[i] = p;
            i++;
        }

        Player choice = null;
        if (!player.isComputerPlayer()) {
            choice = (Player) JOptionPane.showInputDialog(null, "LAWSUIT\nSelect another player to sue.", "", JOptionPane.QUESTION_MESSAGE, null, others, others[0]);
        } else {
            //computer sues the player with most money
            for (Player p : players) {
                if (p == currentPlayer) {
                    continue;
                }
                if (choice == null || choice.getMoney() > p.getMoney()) {
                    choice = p;
                }
            }
        }

        ShareTheWealthCard exemption = choice.hasExemptionCard();
        if (exemption != null) {
            messageDialog(choice.getPlayerName() + " was sued but had an exemption card and used it.");
            replaceSTWCard(exemption);
            amount = 0;
        } else {
            messageDialog(choice.getPlayerName() + " paid $100,000 in a lawsuit to " + player.getPlayerName());
        }

        player.addMoney(amount);
        choice.subtractMoney(amount);

        return amount;
    }

    public void replaceSTWCard(ShareTheWealthCard card) {
        STW_CARDS.add(card);
    }

    public void selectStarterHome(Player player) {
        String choice = null;
        if (!player.isComputerPlayer()) {
            choice = (String) JOptionPane.showInputDialog(null, "Pick your STARTER HOME:", "", JOptionPane.QUESTION_MESSAGE, null, STARTER_HOMES_OPTIONS, STARTER_HOMES_OPTIONS[0]);
        } else {
            choice = STARTER_HOMES_OPTIONS[new Random().nextInt(5)];
        }
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

    public void selectBetterHome(Player player) {
        String choice = null;
        if (!player.isComputerPlayer()) {
            choice = (String) JOptionPane.showInputDialog(null, "Select a BETTER HOME or cancel to stay in current home.", "", JOptionPane.QUESTION_MESSAGE, null, BETTER_HOMES_OPTIONS, BETTER_HOMES_OPTIONS[0]);
        } else {
            choice = BETTER_HOMES_OPTIONS[new Random().nextInt(5)];
        }
        for (HouseCard card : BETTER_HOMES) {
            boolean eq = card.getDescription().equals(choice);
            if (eq) {
                player.addMoney(player.getHouse().getSellFor());
                player.setHouse(card);
                player.subtractMoney(card.getValue());
            }
        }
    }

    public void spinToWin() {
        for (Player p : players) {
            ArrayList<Integer> select = null;
            if (!p.isComputerPlayer()) {
                SpinToWinDialog dialog = new SpinToWinDialog(LifeMain.frame, true, p, this);
                select = dialog.showDialog();
            } else {
                boolean play = new Random().nextBoolean();
                if (!play) {
                    continue;
                }
                select = new ArrayList<Integer>();
                if (p.hasSPIN4Card()) {
                    while (select.size() < 5) {
                        int pick = LifeMain.dice.roll();
                        if (!select.contains(pick)) {
                            select.add(pick);
                        }
                    }
                    ShareTheWealthCard card = p.useSPINCard(Card.Type.SPIN4);
                    replaceSTWCard(card);
                } else if (p.hasSPIN2Card()) {
                    while (select.size() < 3) {
                        int pick = LifeMain.dice.roll();
                        if (!select.contains(pick)) {
                            select.add(pick);
                        }
                    }
                    ShareTheWealthCard card = p.useSPINCard(Card.Type.SPIN2);
                    replaceSTWCard(card);
                } else {
                    select.add(LifeMain.dice.roll());
                }
                boolean big_wager = new Random().nextBoolean();
                p.setSpintowin_wager(big_wager ? 20000 : 5000);
            }

            if (select == null) {
                continue; //did not play
            }
            int spin = spinWheel(p, false);
            if (select.contains(spin)) {
                p.addMoney(p.getSpintowin_wager() * 10);
                messageDialog(p.getPlayerName() + " won $" + (p.getSpintowin_wager() * 10));
            } else {
                p.subtractMoney(p.getSpintowin_wager());
                messageDialog(p.getPlayerName() + " lost the wager.");
            }
        }
    }

    public CareerCard pullCareerCard(boolean requireCollege) {
        CareerCard card = null;
        int index = new Random().nextInt(6);
        if (requireCollege) {
            card = (CareerCard) COLLEGE_CAREER_CARDS.get(index);
        } else {
            card = (CareerCard) CAREER_CARDS.get(index);
        }
        return card;
    }

    public void getMarried(Player player) {
        player.setMarried(true);
        player.addLifeTile(pullLifeTile());
        int spin = spinWheel(player, false);
        int amount = 0;
        if (spin > 7) {
            amount = 10000;
        } else if (spin > 4) {
            amount = 5000;
        }
        String text = player.getPlayerName() + " spinned a " + spin + " for a wedding gift.";
        if (amount > 0) {
            text = text + "\nEach player will give you $" + amount;
        }
        messageDialog(text);
        for (Player p : players) {
            if (p == player) {
                continue;
            }
            p.subtractMoney(amount);
            player.addMoney(amount);
        }
    }

    //debug
    public void setDebugStartPositions(Location loc) {
        for (Player player : players) {
            player.setPlayerLocation(loc);
            player.setCareer((CareerCard) CAREER_CARDS.get(0));
        }

    }

}
