package org.antinori.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class LifeMap implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Location[][] nodes;
    private int hbound = 0;
    private int vbound = 0;
    private static final int HEIGHT = 100;

    public static Location START_CAREER_LOCATION;
    public static Location START_COLLEGE_LOCATION;

    public static Location RISKY_PATH_SELECTED;
    public static Location RISKY_PATH_NOT_SELECTED;

    public static Location BACK_TO_SCHOOL_SELECTED;
    public static Location BACK_TO_SCHOOL_NOT_SELECTED;

    public static Location FAMILY_PATH_SELECTED;
    public static Location FAMILY_PATH_NOT_SELECTED;

    public static Location MARRIED_LOCATION;

    public LifeMap(String template) throws Exception {

        StringTokenizer tokens = new StringTokenizer(template, "\n");
        vbound = tokens.countTokens();
        if (vbound == 0) {
            throw new Exception("Template is empty.");
        }
        String line = tokens.nextToken();
        if (hbound == 0) {
            hbound = line.length();
        }

        nodes = new Location[hbound][vbound];
        for (int i = 0; i < hbound; i++) {
            for (int j = 0; j < vbound; j++) {
                nodes[i][j] = new Location(i, j);
                nodes[i][j].setHeight(HEIGHT);
            }
        }

        tokens = new StringTokenizer(template, "\n");
        int y = 0;

        while (tokens.hasMoreTokens()) {
            line = tokens.nextToken();
            char[] array = line.toCharArray();
            for (int x = 0; x < array.length; x++) {
                Location loc = nodes[x][y];
                if (array[x] == 'x') {
                    loc.setBlocked(true);
                }
            }
            y++;
        }

        START_COLLEGE_LOCATION = nodes[13][9];
        nodes[13][9].setTile(new Tile("Start College, borrow $100,000", 100000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[12][9].setTile(new Tile("Scholarship! collect $20,000", 20000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[12][10].setTile(new Tile("Buy book and supplies, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[11][10].setTile(new Tile("Make new friends for life", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[11][11].setTile(new Tile("Part-time job, collect $10,000", 10000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[11][12].setTile(new Tile("Semester in London", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[12][12].setTile(new Tile("Spring break in Florida, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[13][12].setTile(new Tile("Honor Role", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[14][12].setTile(new Tile("Final tests and term paper, miss next turn", 0, Tile.Type.MISS_TURN, 1, Tile.TILE_YELLOW));
        nodes[15][12].setTile(new Tile("Summer internship at company in the city", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[16][12].setTile(new Tile("Graduation Day", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[17][12].setTile(new Tile("College Career Choice", 0, Tile.Type.COLLEGE_CAREER_CHOICE, 1, Tile.ORANGE));

        START_CAREER_LOCATION = nodes[15][9];
        nodes[15][9].setTile(new Tile("Start Career", 0, Tile.Type.NULL, 1, Tile.TILE_YELLOW));
        nodes[16][9].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[16][10].setTile(new Tile("Buy a Chevy Impala, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[17][10].setTile(new Tile("Rent apartment, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[18][10].setTile(new Tile("Inheritance!, collect $20,000", 20000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[18][11].setTile(new Tile("Break up with girlfriend.", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[18][12].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));

        nodes[19][12].setTile(new Tile("Adopt a pet from Animal Shelter", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[20][12].setTile(new Tile("Take a Share the Wealth Card", 0, Tile.Type.TAKE_STW_CARD, 1, Tile.TILE_YELLOW));
        nodes[20][11].setTile(new Tile("Get engaged!", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[21][11].setTile(new Tile("Snowboarding accident, pay $5000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[21][10].setTile(new Tile("Elope! Move to GET MARRIED", 0, Tile.Type.MOVE_TO_MARRIED, 1, Tile.TILE_YELLOW));
        nodes[22][10].setTile(new Tile("Volunteer at soup kitchen", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[23][10].setTile(new Tile("Engagement party", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[23][9].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[23][8].setTile(new Tile("Win a race, collect $10,000", 10000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[24][8].setTile(new Tile("Get Married", 0, Tile.Type.GET_MARRIED, 1, Tile.ORANGE));
        MARRIED_LOCATION = nodes[24][8];

        nodes[25][8].setTile(new Tile("Wedding reception, pay $20,000", 20000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[26][8].setTile(new Tile("Happy Honeymoon!", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[26][9].setTile(new Tile("Take a Share the Wealth Card", 0, Tile.Type.TAKE_STW_CARD, 1, Tile.TILE_YELLOW));
        nodes[26][10].setTile(new Tile("Car accident, pay $10,000", 10000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[25][10].setTile(new Tile("Job relocation, pay $20,000", 20000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[25][11].setTile(new Tile("Pay Day", 10000, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[25][12].setTile(new Tile("Taxes Due", 0, Tile.Type.TAXES_DUE, 1, Tile.TILE_YELLOW));
        nodes[26][12].setTile(new Tile("Family trip to Myrtle Beach", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[27][12].setTile(new Tile("Buy a Starter Home", 0, Tile.Type.BUY_STARTER_HOME, 1, Tile.ORANGE));

        nodes[28][12].setTile(new Tile("Win the lottery! collect $50,000", 50000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[28][11].setTile(new Tile("Lose your job. Take new Career Card", 0, Tile.Type.LOSE_JOB, 1, Tile.TILE_YELLOW));
        nodes[28][10].setTile(new Tile("Buy flat screen plasma TV, pay $5000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[28][9].setTile(new Tile("Baby Boy!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[28][8].setTile(new Tile("Furnish baby's room, pay $5000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[28][7].setTile(new Tile("Receive childrens gift from grandparents, collect $5000", 5000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[28][6].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));

        nodes[27][6].setTile(new Tile("Baby Girl!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[26][6].setTile(new Tile("Vote!", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[25][6].setTile(new Tile("Win the American Idol TV Show! collect $100,000", 100000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[25][5].setTile(new Tile("Twins!", 2, Tile.Type.LIFE_ADD_CHILDREN, 2, Tile.TILE_YELLOW));
        nodes[25][4].setTile(new Tile("Get best seats at the big game, pay $20,000", 20000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[26][4].setTile(new Tile("Attend Hollywood movie premiere!", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[27][4].setTile(new Tile("Baby Girl!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[28][4].setTile(new Tile("Take a Share the Wealth Card", 0, Tile.Type.TAKE_STW_CARD, 1, Tile.TILE_YELLOW));
        nodes[28][3].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[28][2].setTile(new Tile("Learn sign language", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[28][1].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));

        nodes[27][1].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[26][1].setTile(new Tile("Lose your job, take new career card", 0, Tile.Type.LOSE_JOB, 1, Tile.TILE_YELLOW));
        nodes[25][1].setTile(new Tile("Earn some money from Elizabeth Arden cosmetic sales, collect $5000", 5000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[24][1].setTile(new Tile("Learn to brew beer", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[23][1].setTile(new Tile("Buy a Ford F-150 truck, pay $10,000", 10000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[22][1].setTile(new Tile("Return to school, pay $50,000 OR continue on the path of life", 50000, Tile.Type.RETURN_TO_SCHOOL, 1, Tile.ORANGE));

        BACK_TO_SCHOOL_SELECTED = nodes[22][2];
        nodes[22][2].setTile(new Tile("Take a double course load for extra credit, spin again", 0, Tile.Type.SPIN_AGAIN, 1, Tile.TILE_YELLOW));
        nodes[22][3].setTile(new Tile("Buy books and supplies, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[22][4].setTile(new Tile("Employer provides scholarship, collect $20,000", 20000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[21][4].setTile(new Tile("Upgrade computer, pay $10,000", 10000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[21][5].setTile(new Tile("Join honor society", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[21][6].setTile(new Tile("Go to summer seminar, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[20][6].setTile(new Tile("CHANGE CAREER, or get $20,000 pay raise", 20000, Tile.Type.CHANGE_CAREER, 1, Tile.ORANGE));
        nodes[19][6].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));

        BACK_TO_SCHOOL_NOT_SELECTED = nodes[21][1];
        nodes[21][1].setTile(new Tile("House flooded, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[20][1].setTile(new Tile("Inlaws visit", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[19][1].setTile(new Tile("Pay Day", 10000, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[19][2].setTile(new Tile("Coach youth sports team", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[19][3].setTile(new Tile("Adopt twins!", 2, Tile.Type.LIFE_ADD_CHILDREN, 2, Tile.TILE_YELLOW));
        nodes[19][4].setTile(new Tile("Lose your job, take new career card", 0, Tile.Type.LOSE_JOB, 1, Tile.TILE_YELLOW));
        nodes[19][5].setTile(new Tile("Taxes Due", 0, Tile.Type.TAXES_DUE, 1, Tile.TILE_YELLOW));

        nodes[19][7].setTile(new Tile("Run for Congress", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[18][7].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[17][7].setTile(new Tile("Baby boy!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[17][6].setTile(new Tile("Take family cruise vacation, pay $25,000", 25000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[17][5].setTile(new Tile("Take a Share the Wealth Card", 0, Tile.Type.TAKE_STW_CARD, 1, Tile.TILE_YELLOW));
        nodes[17][4].setTile(new Tile("Win on TV game show, collect $100,000", 100000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[17][3].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[17][2].setTile(new Tile("Art auction, pay $20,000", 20000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[17][1].setTile(new Tile("Pay Day", 10000, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[16][1].setTile(new Tile("Visit Grand Canyon", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[15][1].setTile(new Tile("Taxes Due", 0, Tile.Type.TAXES_DUE, 1, Tile.TILE_YELLOW));
        nodes[14][1].setTile(new Tile("Sports camp for the kids, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[13][1].setTile(new Tile("Donate to African Ophans, pay $40,000", 40000, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[12][1].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[12][2].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[12][3].setTile(new Tile("Buy an SUV, pay $40,000", 40000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[12][4].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[12][5].setTile(new Tile("Lose your job, take new career card", 0, Tile.Type.LOSE_JOB, 1, Tile.TILE_YELLOW));
        nodes[12][6].setTile(new Tile("TV Dance show winner, collect $100,000", 100000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[12][7].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[11][7].setTile(new Tile("Summer school, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));

        FAMILY_PATH_NOT_SELECTED = nodes[10][6];
        nodes[10][7].setTile(new Tile("Take the family path, or continue on the path of life", 0, Tile.Type.TAKE_FAMILY_PATH, 1, Tile.ORANGE));
        nodes[10][6].setTile(new Tile("Buy home gym, pay $30,000", 30000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[10][5].setTile(new Tile("Pay Day", 10000, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[9][5].setTile(new Tile("Learn CPR", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[8][5].setTile(new Tile("Buy foreign sports car, pay $30,000", 30000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[7][5].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));

        FAMILY_PATH_SELECTED = nodes[10][8];
        nodes[10][8].setTile(new Tile("Baby girl!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[9][8].setTile(new Tile("Open daycare, other players pay you $5000 for each child", 5000, Tile.Type.COLLECT_FOR_EACH_CHILD_FROM_OTHERS, 1, Tile.TILE_YELLOW));
        nodes[8][8].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[7][8].setTile(new Tile("Baby boy!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[7][7].setTile(new Tile("Get family physicals, pay $5,000", 5000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[7][6].setTile(new Tile("Twins!", 2, Tile.Type.LIFE_ADD_CHILDREN, 2, Tile.TILE_YELLOW));

        nodes[7][4].setTile(new Tile("Find buried treasure, collect $500,000", 500000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[7][3].setTile(new Tile("Baby boy!", 1, Tile.Type.LIFE_ADD_CHILDREN, 1, Tile.TILE_YELLOW));
        nodes[8][3].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[9][3].setTile(new Tile("Buy lakeside cabin, pay $120,000", 120000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[10][3].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[10][2].setTile(new Tile("Adopt pet from anumal shelter", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[10][1].setTile(new Tile("Win nobel prize, collect $100,000", 100000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));

        nodes[9][1].setTile(new Tile("Buy Better Home. draw new deed", 0, Tile.Type.BUY_BETTER_HOME, 1, Tile.ORANGE));
        nodes[8][1].setTile(new Tile("Tornado hits house, pay $125,000", 125000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[7][1].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[6][1].setTile(new Tile("Taxes Due", 0, Tile.Type.TAXES_DUE, 1, Tile.TILE_YELLOW));
        nodes[5][1].setTile(new Tile("Write best selling book, collect $200,000", 200000, Tile.Type.COLLECT, 1, Tile.TILE_YELLOW));
        nodes[4][1].setTile(new Tile("College, pay $50,000 per child", 50000, Tile.Type.PAY_PER_CHILD, 1, Tile.TILE_YELLOW));
        nodes[3][1].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[2][1].setTile(new Tile("Buy sailboat, pay $30,000", 30000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[1][1].setTile(new Tile("Pay Day", 10000, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[1][2].setTile(new Tile("Tax refund", 0, Tile.Type.TAX_REFUND, 1, Tile.TILE_YELLOW));
        nodes[1][3].setTile(new Tile("Get a hunting firearm license", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));

        RISKY_PATH_SELECTED = nodes[3][3];
        nodes[2][3].setTile(new Tile("Take Risky Path of Life or the Safe Path of Life", 0, Tile.Type.TAKE_RISKY_PATH, 1, Tile.ORANGE));
        nodes[3][3].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[4][3].setTile(new Tile("Sponsor sports tournament, pay $35,000", 35000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[4][4].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[4][5].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[4][6].setTile(new Tile("Have cosmetic surgery, pay $100,000", 100000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[4][7].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[4][8].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[3][8].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));

        RISKY_PATH_NOT_SELECTED = nodes[2][4];
        nodes[2][4].setTile(new Tile("Take family on trip to Europe, pay $30,000", 30000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[2][5].setTile(new Tile("Visit Pyramids in Egypt", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[2][6].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[2][7].setTile(new Tile("Visit old soldiers home", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[2][8].setTile(new Tile("Redecorate your home, pay $80,000", 80000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));

        nodes[2][9].setTile(new Tile("You're a grandparent", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[2][10].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[1][10].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[1][11].setTile(new Tile("Sponsor public arts exhibit, pay $125,000", 125000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[1][10].setTile(new Tile("Host family reunion", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[1][11].setTile(new Tile("You're a grandparent", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[1][12].setTile(new Tile("Hire maid and butler service, pay $65,000", 65000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[2][12].setTile(new Tile("Climb Mt. Fuji in Japan", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[3][12].setTile(new Tile("Pay Day", 10000, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[4][12].setTile(new Tile("Spin to WIN!", 0, Tile.Type.SPIN_TO_WIN, 1, Tile.TILE_YELLOW));
        nodes[5][12].setTile(new Tile("Visit the Great Wall of China", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[6][12].setTile(new Tile("Have life saving operation, pay $50,000", 50000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[7][12].setTile(new Tile("LAWSUIT Sue another player for $100,000", 100000, Tile.Type.LAWSUIT, 1, Tile.SKY_BLUE));
        nodes[8][12].setTile(new Tile("Have family web site designed, pay $50,000", 50000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[9][12].setTile(new Tile("You're a grandparent", 0, Tile.Type.LIFE, 1, Tile.TILE_YELLOW));
        nodes[9][11].setTile(new Tile("Pay Day", 0, Tile.Type.PAYDAY, 1, Tile.PAY_DAY_GREEN));
        nodes[9][10].setTile(new Tile("Host entertainment awards party, pay $35,000", 35000, Tile.Type.PAY, 1, Tile.TILE_YELLOW));
        nodes[8][10].setTile(new Tile("Pension, collect $10,000 times spin", 0, Tile.Type.PENSION, 1, Tile.TILE_YELLOW));

        nodes[7][10].setTile(new Tile("RETIREMENT", 0, Tile.Type.RETIRE, 1, Tile.TILE_YELLOW));

        nodes[13][9].setNextLocation(nodes[12][9]);
        nodes[12][9].setNextLocation(nodes[12][10]);
        nodes[12][10].setNextLocation(nodes[11][10]);
        nodes[11][10].setNextLocation(nodes[11][11]);
        nodes[11][11].setNextLocation(nodes[11][12]);
        nodes[11][12].setNextLocation(nodes[12][12]);
        nodes[12][12].setNextLocation(nodes[13][12]);
        nodes[13][12].setNextLocation(nodes[14][12]);
        nodes[14][12].setNextLocation(nodes[15][12]);
        nodes[15][12].setNextLocation(nodes[16][12]);
        nodes[16][12].setNextLocation(nodes[17][12]);
        nodes[17][12].setNextLocation(nodes[18][12]);

        nodes[15][9].setNextLocation(nodes[16][9]);
        nodes[16][9].setNextLocation(nodes[16][10]);
        nodes[16][10].setNextLocation(nodes[17][10]);
        nodes[17][10].setNextLocation(nodes[18][10]);
        nodes[18][10].setNextLocation(nodes[18][11]);
        nodes[18][11].setNextLocation(nodes[18][12]);
        nodes[18][12].setNextLocation(nodes[19][12]);

        nodes[19][12].setNextLocation(nodes[20][12]);
        nodes[20][12].setNextLocation(nodes[20][11]);
        nodes[20][11].setNextLocation(nodes[21][11]);
        nodes[21][11].setNextLocation(nodes[21][10]);
        nodes[21][10].setNextLocation(nodes[22][10]);
        nodes[22][10].setNextLocation(nodes[23][10]);
        nodes[23][10].setNextLocation(nodes[23][9]);
        nodes[23][9].setNextLocation(nodes[23][8]);
        nodes[23][8].setNextLocation(nodes[24][8]);
        nodes[24][8].setNextLocation(nodes[25][8]);

        nodes[25][8].setNextLocation(nodes[26][8]);
        nodes[26][8].setNextLocation(nodes[26][9]);
        nodes[26][9].setNextLocation(nodes[26][10]);
        nodes[26][10].setNextLocation(nodes[25][10]);
        nodes[25][10].setNextLocation(nodes[25][11]);
        nodes[25][11].setNextLocation(nodes[25][12]);
        nodes[25][12].setNextLocation(nodes[26][12]);
        nodes[26][12].setNextLocation(nodes[27][12]);
        nodes[27][12].setNextLocation(nodes[28][12]);

        nodes[28][12].setNextLocation(nodes[28][11]);
        nodes[28][11].setNextLocation(nodes[28][10]);
        nodes[28][10].setNextLocation(nodes[28][9]);
        nodes[28][9].setNextLocation(nodes[28][8]);
        nodes[28][8].setNextLocation(nodes[28][7]);
        nodes[28][7].setNextLocation(nodes[28][6]);
        nodes[28][6].setNextLocation(nodes[27][6]);

        nodes[27][6].setNextLocation(nodes[26][6]);
        nodes[26][6].setNextLocation(nodes[25][6]);
        nodes[25][6].setNextLocation(nodes[25][5]);
        nodes[25][5].setNextLocation(nodes[25][4]);
        nodes[25][4].setNextLocation(nodes[26][4]);
        nodes[26][4].setNextLocation(nodes[27][4]);
        nodes[27][4].setNextLocation(nodes[28][4]);
        nodes[28][4].setNextLocation(nodes[28][3]);
        nodes[28][3].setNextLocation(nodes[28][2]);
        nodes[28][2].setNextLocation(nodes[28][1]);
        nodes[28][1].setNextLocation(nodes[27][1]);

        nodes[27][1].setNextLocation(nodes[26][1]);
        nodes[26][1].setNextLocation(nodes[25][1]);
        nodes[25][1].setNextLocation(nodes[24][1]);
        nodes[24][1].setNextLocation(nodes[23][1]);
        nodes[23][1].setNextLocation(nodes[22][1]);
        nodes[22][1].setNextLocation(null);

        nodes[22][2].setNextLocation(nodes[22][3]);
        nodes[22][3].setNextLocation(nodes[22][4]);
        nodes[22][4].setNextLocation(nodes[21][4]);
        nodes[21][4].setNextLocation(nodes[21][5]);
        nodes[21][5].setNextLocation(nodes[21][6]);
        nodes[21][6].setNextLocation(nodes[20][6]);
        nodes[20][6].setNextLocation(nodes[19][6]);
        nodes[19][6].setNextLocation(nodes[19][7]);

        nodes[21][1].setNextLocation(nodes[20][1]);
        nodes[20][1].setNextLocation(nodes[19][1]);
        nodes[19][1].setNextLocation(nodes[19][2]);
        nodes[19][2].setNextLocation(nodes[19][3]);
        nodes[19][3].setNextLocation(nodes[19][4]);
        nodes[19][4].setNextLocation(nodes[19][5]);
        nodes[19][5].setNextLocation(nodes[19][6]);

        nodes[19][7].setNextLocation(nodes[18][7]);
        nodes[18][7].setNextLocation(nodes[17][7]);
        nodes[17][7].setNextLocation(nodes[17][6]);
        nodes[17][6].setNextLocation(nodes[17][5]);
        nodes[17][5].setNextLocation(nodes[17][4]);
        nodes[17][4].setNextLocation(nodes[17][3]);
        nodes[17][3].setNextLocation(nodes[17][2]);
        nodes[17][2].setNextLocation(nodes[17][1]);
        nodes[17][1].setNextLocation(nodes[16][1]);
        nodes[16][1].setNextLocation(nodes[15][1]);
        nodes[15][1].setNextLocation(nodes[14][1]);
        nodes[14][1].setNextLocation(nodes[13][1]);
        nodes[13][1].setNextLocation(nodes[12][1]);
        nodes[12][1].setNextLocation(nodes[12][2]);
        nodes[12][2].setNextLocation(nodes[12][3]);
        nodes[12][3].setNextLocation(nodes[12][4]);
        nodes[12][4].setNextLocation(nodes[12][5]);
        nodes[12][5].setNextLocation(nodes[12][6]);
        nodes[12][6].setNextLocation(nodes[12][7]);
        nodes[12][7].setNextLocation(nodes[11][7]);
        nodes[11][7].setNextLocation(nodes[10][7]);

        nodes[10][7].setNextLocation(null);
        nodes[10][6].setNextLocation(nodes[10][5]);
        nodes[10][5].setNextLocation(nodes[9][5]);
        nodes[9][5].setNextLocation(nodes[8][5]);
        nodes[8][5].setNextLocation(nodes[7][5]);
        nodes[7][5].setNextLocation(nodes[7][4]);

        nodes[10][8].setNextLocation(nodes[9][8]);
        nodes[9][8].setNextLocation(nodes[8][8]);
        nodes[8][8].setNextLocation(nodes[7][8]);
        nodes[7][8].setNextLocation(nodes[7][7]);
        nodes[7][7].setNextLocation(nodes[7][6]);
        nodes[7][6].setNextLocation(nodes[7][5]);

        nodes[7][4].setNextLocation(nodes[7][3]);
        nodes[7][3].setNextLocation(nodes[8][3]);
        nodes[8][3].setNextLocation(nodes[9][3]);
        nodes[9][3].setNextLocation(nodes[10][3]);
        nodes[10][3].setNextLocation(nodes[10][2]);
        nodes[10][2].setNextLocation(nodes[10][1]);
        nodes[10][1].setNextLocation(nodes[9][1]);

        nodes[9][1].setNextLocation(nodes[8][1]);
        nodes[8][1].setNextLocation(nodes[7][1]);
        nodes[7][1].setNextLocation(nodes[6][1]);
        nodes[6][1].setNextLocation(nodes[5][1]);
        nodes[5][1].setNextLocation(nodes[4][1]);
        nodes[4][1].setNextLocation(nodes[3][1]);
        nodes[3][1].setNextLocation(nodes[2][1]);
        nodes[2][1].setNextLocation(nodes[1][1]);
        nodes[1][1].setNextLocation(nodes[1][2]);
        nodes[1][2].setNextLocation(nodes[1][3]);
        nodes[1][3].setNextLocation(nodes[2][3]);

        nodes[2][3].setNextLocation(null);
        nodes[3][3].setNextLocation(nodes[4][3]);
        nodes[4][3].setNextLocation(nodes[4][4]);
        nodes[4][4].setNextLocation(nodes[4][5]);
        nodes[4][5].setNextLocation(nodes[4][6]);
        nodes[4][6].setNextLocation(nodes[4][7]);
        nodes[4][7].setNextLocation(nodes[4][8]);
        nodes[4][8].setNextLocation(nodes[3][8]);
        nodes[3][8].setNextLocation(nodes[2][8]);

        nodes[2][4].setNextLocation(nodes[2][5]);
        nodes[2][5].setNextLocation(nodes[2][6]);
        nodes[2][6].setNextLocation(nodes[2][7]);
        nodes[2][7].setNextLocation(nodes[2][8]);
        nodes[2][8].setNextLocation(nodes[2][9]);

        nodes[2][9].setNextLocation(nodes[2][10]);
        nodes[2][10].setNextLocation(nodes[1][10]);
        nodes[1][10].setNextLocation(nodes[1][11]);
        nodes[1][11].setNextLocation(nodes[1][10]);
        nodes[1][10].setNextLocation(nodes[1][11]);
        nodes[1][11].setNextLocation(nodes[1][12]);
        nodes[1][12].setNextLocation(nodes[2][12]);
        nodes[2][12].setNextLocation(nodes[3][12]);
        nodes[3][12].setNextLocation(nodes[4][12]);
        nodes[4][12].setNextLocation(nodes[5][12]);
        nodes[5][12].setNextLocation(nodes[6][12]);
        nodes[6][12].setNextLocation(nodes[7][12]);
        nodes[7][12].setNextLocation(nodes[8][12]);
        nodes[8][12].setNextLocation(nodes[9][12]);
        nodes[9][12].setNextLocation(nodes[9][11]);
        nodes[9][11].setNextLocation(nodes[9][10]);
        nodes[9][10].setNextLocation(nodes[8][10]);
        nodes[8][10].setNextLocation(nodes[7][10]);

        nodes[7][10].setNextLocation(null);

    }

    public Location getLocation(int x, int y) {
        return nodes[x][y];
    }

    public int getXSize() {
        return nodes.length;
    }

    public int getYSize() {
        return nodes[0].length;
    }

    public Collection<Location> getLocations() {
        Collection<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                locations.add(nodes[i][j]);
            }
        }
        return locations;
    }

}
