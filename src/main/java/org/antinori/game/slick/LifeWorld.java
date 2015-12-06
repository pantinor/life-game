package org.antinori.game.slick;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import org.antinori.game.Player;
import org.antinori.game.Tile;

import org.antinori.game.twl.RootPane;
import org.apache.commons.lang3.text.WordUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LifeWorld extends World {

    private static Color OFF = new Color(1, 1, 1, 0.5f);

    public static final int TILE_SIZE = 96;

    private float offsetx = 0;
    private float offsety = 0;
    private int sectionx;
    private int sectiony;

    public ArrayList<Tile> tiles = new ArrayList<Tile>();
    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public HashMap<String, SelectableOption> selectables = new HashMap<String, SelectableOption>();

    public Actor currentPlayer;

    public NewSlickGame nsg;
    public LifeMap map;

    //private AngelCodeFont font;
    public static TrueTypeFont font_16;
    public static TrueTypeFont font_10;

    private Image grass;
    private Image wheel;
    private float ang;
    private float wheelSpeed = 0.4f;
    private int wheelRoll = 0;
    private boolean spinWheel = false;

    private String dismissalMessageDisplayed = null;
    private String messageDisplayed = null;

    public boolean gameStarted = false;
    public boolean gameOver = false;

    public static SpriteSheet people_sheet1;
    public static SpriteSheet people_sheet2;
    public static SpriteSheet people_sheet3;
    public static SpriteSheet people_sheet4;
    public static SpriteSheet people_sheet5;
    public static SpriteSheet people_sheet6;
    public static SpriteSheet people_sheet7;
    public static SpriteSheet people_sheet8;

    public static SpriteSheet homes_sheet;
    public static SpriteSheet careers_sheet;

    public LifeWorld(int id, GameContainer container) {
        super(id, container);
    }

    @Override
    protected RootPane createRootPane() {
        RootPane rp = new RootPane(this);
        rp.getOrCreateActionMap().addMapping(this);
        return rp;
    }

    @Override
    protected void layoutRootPane() {
        //nothing, it is an empty pane
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        super.init(container, game);

        this.map = new LifeMap("./bigger-map.tmx");

        SpriteSheet map_sprites = new SpriteSheet("96x96-sprites.png", 96, 96);
        grass = map_sprites.getSprite(4, 0);

        //this.font = new AngelCodeFont("./font.fnt","./font.png");
        java.awt.Font awtFont = new java.awt.Font("Verdana", Font.PLAIN, 16);
        font_16 = new TrueTypeFont(awtFont, false);

        awtFont = new java.awt.Font("Verdana", Font.PLAIN, 10);
        font_10 = new TrueTypeFont(awtFont, false);

        wheel = new Image("mywheel.png");

        people_sheet1 = new SpriteSheet("girl-walking.png", 96, 96);
        people_sheet2 = new SpriteSheet("arno-walking.png", 96, 96);
        people_sheet3 = new SpriteSheet("builder-walking.png", 96, 96);
        people_sheet4 = new SpriteSheet("lady-walking.png", 96, 96);
        people_sheet5 = new SpriteSheet("santa-walking.png", 96, 96);
        people_sheet6 = new SpriteSheet("doctor-walking.png", 96, 96);
        people_sheet7 = new SpriteSheet("pirate-walking.png", 96, 96);
        people_sheet8 = new SpriteSheet("boy-walking.png", 96, 96);

        homes_sheet = new SpriteSheet("homes.png", 96, 96);
        careers_sheet = new SpriteSheet("careers.png", 96, 96);

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);

        nsg = new NewSlickGame(LifeWorld.this);
        nsg.createDecks();

        Thread thread = new Thread("Game Loop Thread") {
            public void run() {
                try {

                    PlayerSelectionPopup popup = new PlayerSelectionPopup();
                    popup.showDialog(getRootPane());

                    int index = 13;//where the actors first start out

                    Actor me = new Actor(popup.selected_icon, index, 7);
                    actors.add(me);
                    nsg.addPlayer(popup.playerName, me, java.awt.Color.blue, false);
                    add(me, World.GAME);

                    for (String name : popup.selection.keySet()) {
                        Actor actor = new Actor(popup.selection.get(name), ++index, 7);
                        actors.add(actor);
                        nsg.addPlayer(name, actor, java.awt.Color.blue, true);
                        add(actor, World.GAME);
                    }

                    //nsg.setDebugStartPositions(map.nodes[1][3]);
                    currentPlayer = nsg.initPlayers();

                    gameStarted = true;
                    nsg.startGame();

                    //YesNoPopup yn = new YesNoPopup("Whatever you want to say here?");
                    //yn.showDialog(getRootPane());
                    //int seed = LifeMain.dice.roll();
                    //int roll = spinWheel(seed);
                    //nsg.messageDialog("you spinned a " + roll);
                    //currentPlayer.getPlayer().addCard(new ShareTheWealthCard(Card.Type.SPIN2));
                    //currentPlayer.getPlayer().addCard(new ShareTheWealthCard(Card.Type.SPIN4));
                    //GameInterface gi = (GameInterface)nsg;
                    //SpinToWinPopup stw = new SpinToWinPopup(currentPlayer.getPlayer(), gi);
                    //stw.showDialog(getRootPane());
//					String[] options = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
//					OptionPopup popup = new OptionPopup(options,"Whatever you want to say here?", true);
//					popup.showDialog(getRootPane());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        if (spinWheel) {

            if (wheelSpeed > 0) {
                ang += delta * wheelSpeed;
            }
            wheelSpeed -= 0.0001f;

            if (wheelSpeed < 0) {
                wheelSpeed = 0;

                float remainder = ang % 360;
                while (true) {
                    if (remainder <= 36 * 1) {
                        wheelRoll = 10;
                        break;
                    }
                    if (remainder <= 36 * 2) {
                        wheelRoll = 9;
                        break;
                    }
                    if (remainder <= 36 * 3) {
                        wheelRoll = 8;
                        break;
                    }
                    if (remainder <= 36 * 4) {
                        wheelRoll = 7;
                        break;
                    }
                    if (remainder <= 36 * 5) {
                        wheelRoll = 6;
                        break;
                    }
                    if (remainder <= 36 * 6) {
                        wheelRoll = 5;
                        break;
                    }
                    if (remainder <= 36 * 7) {
                        wheelRoll = 4;
                        break;
                    }
                    if (remainder <= 36 * 8) {
                        wheelRoll = 3;
                        break;
                    }
                    if (remainder <= 36 * 9) {
                        wheelRoll = 2;
                        break;
                    }
                    if (remainder <= 36 * 10) {
                        wheelRoll = 1;
                        break;
                    }
                    break;
                }
            } else {
                if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                    wheelSpeed = 0.4f;
                }
            }
        }

        super.update(container, game, delta);

        if (container.getInput().isKeyPressed(Input.KEY_SPACE) || container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (dismissalMessageDisplayed != null) {
                dismissalMessageDisplayed = null;
            }
        }

        synchronized (selectables) {
            for (SelectableOption option : selectables.values()) {
                try {
                    option.update(container, delta);
                } catch (Exception e) {
                }
            }
        }

        if (gameOver) {
            game.enterState(LifeGame.END_GAME_STATE, new FadeOutTransition(), new FadeInTransition());
            return;
        }

    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setFont(font_16);

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                g.drawImage(grass, i * TILE_SIZE, j * TILE_SIZE);
            }
        }

        if (currentPlayer != null) {

            sectionx = currentPlayer.getCurrentTileX() - 8;
            sectiony = currentPlayer.getCurrentTileY() - 6;
            int width = map.getWidth();
            int height = map.getHeight();
            map.render(0, 0, sectionx, sectiony, width, height);

            offsetx = TILE_SIZE * 8 - currentPlayer.getCurrentTileX() * TILE_SIZE;
            offsety = TILE_SIZE * 6 - currentPlayer.getCurrentTileY() * TILE_SIZE;
            g.translate((int) offsetx, (int) offsety);

            drawLabelsOnTiles(g);
        }

        super.render(container, game, g);

        g.resetTransform();

        if (dismissalMessageDisplayed != null) {
            showMessage(container, g, dismissalMessageDisplayed);
        }

        if (messageDisplayed != null) {
            showMessage(container, g, messageDisplayed);
        }

        if (gameStarted) {
            drawPlayerPanel(container, game, g);
        }

        if (spinWheel) {
            int x = container.getWidth() / 2 - 255;
            int y = container.getHeight() / 2 - 255;

            g.setColor(Color.white);
            Circle c = new Circle(x + 255, y - 20, 10);
            g.fill(c);

            Rectangle line = new Rectangle(x + 255, y - 20, 2, 30);
            g.fill(line);
            g.draw(line);

            g.rotate(x + 255, y + 255, ang);
            wheel.draw(x, y);
        }

    }

    public void drawLabelsOnTiles(Graphics g) {
        g.setFont(font_10);

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Tile tile = map.nodes[i][j].getTile();
                if (tile == null || tile.getDescription().length() < 1) {
                    continue;
                }
                if (tile.getColor() == Tile.ORANGE || tile.getColor() == Tile.PAY_DAY_GREEN || tile.getColor() == Tile.SKY_BLUE) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.darkGray);
                }
                String text = tile.getDescription();
                String wrapped = WordUtils.wrap(text, 12);
                String[] lines = wrapped.split("\n");
                for (int z = 0; z < lines.length; z++) {
                    g.drawString(lines[z], i * TILE_SIZE + 15, j * TILE_SIZE + 10 + z * 10);
                }
            }
        }
        g.setFont(font_16);
    }

    public void drawPlayerPanel(GameContainer container, StateBasedGame game, Graphics g) {

        int single_panel_size = 100;
        int spacing = 10;
        int extra_actor_height = 22;
        int num_players = nsg.getPlayers().size();

        RoundedRectangle r = new RoundedRectangle(
                container.getWidth() - single_panel_size * 2 - spacing, //x
                single_panel_size, //y
                single_panel_size * 2 + spacing * 2, //width
                (single_panel_size + extra_actor_height) * num_players + (spacing) * num_players + spacing, //height
                15); //radius on corner

        g.setColor(Color.darkGray);
        g.fill(r);
        g.draw(r);

        int inner_rect_y = single_panel_size + spacing;

        for (int i = 0; i < num_players; i++) {

            RoundedRectangle inner_rect = new RoundedRectangle(
                    container.getWidth() - single_panel_size * 2, //x
                    inner_rect_y, //y
                    single_panel_size * 2 + spacing * 2, //width
                    single_panel_size + extra_actor_height, //height
                    6); //radius on corner

            inner_rect_y += single_panel_size + extra_actor_height + spacing;

            g.setColor(Color.darkGray.brighter());
            g.fill(inner_rect);
            g.draw(inner_rect);

        }

        int y = single_panel_size + spacing + 2;
        int progress_bar_width = single_panel_size * 2 - 20;
        double factor = (double) progress_bar_width / (double) 2000000;

        for (int i = 0; i < num_players; i++) {
            Player p = nsg.getPlayers().get(i);

            int x = container.getWidth() - single_panel_size * 2 + 8;

            Color color = Color.white;
            if (p.getActor() != currentPlayer) {
                color = OFF;
            }

            g.setColor(Color.blue.darker());
            g.fillRect(x, y, progress_bar_width, 8);

            g.setColor(Color.pink);
            double money = ((double) p.getMoney() * factor);
            g.fillRect(x, y, (int) money, 8);

            g.setColor(color);
            g.setFont(font_10);
            g.drawString(p.getPlayerName(), x + 3, y + 8);
            g.drawString("$" + p.getMoney(), x + 90, y + 8);

            g.drawString(p.getMarried_status_text(), x + 3, y + 8 + 12);
            g.drawString(p.getChild_count() + " children", x + 90, y + 8 + 12);

            g.drawString(p.getCareer_name(), x + 3, y + 8 + 24);

            g.drawString("Salary $" + p.getSalary(), x + 3, y + 8 + 36);
            g.drawString((p.isRetired() ? "RETIRED" : ""), x + 90, y + 8 + 36);

            g.drawString(p.getHouse_name() + (p.getHouse() != null ? " $" + p.getHouse().getValue() : ""), x + 3, y + 8 + 48);

            g.drawString("LIFE: " + p.getLife_card_count(), x + 3, y + 8 + 60);
            g.drawString("LTI: " + (p.getLti_card_value() > 0 ? p.getLti_card_value() : "None"), x + 50, y + 8 + 60);
            g.drawString("Loans: " + p.getLoans(), x + 120, y + 8 + 60);

            g.drawString("STW1 " + p.getStw_card_1(), x + 3, y + 8 + 72);
            g.drawString("STW4 " + p.getStw_card_4(), x + 100, y + 8 + 72);

            g.drawString("STW2 " + p.getStw_card_2(), x + 3, y + 8 + 84);
            g.drawString("STW5 " + p.getStw_card_5(), x + 100, y + 8 + 84);

            g.drawString("STW3 " + p.getStw_card_3(), x + 3, y + 8 + 96);
            g.drawString("STW6 " + p.getStw_card_6(), x + 100, y + 8 + 96);

            y += single_panel_size + extra_actor_height + spacing;

        }
    }

    public static void showMessage(GameContainer container, Graphics g, String text) {
        int x = container.getWidth() / 2 - 300;
        int y = container.getHeight() / 2 - 100;
        RoundedRectangle r = new RoundedRectangle(
                x,
                y,
                container.getWidth() / 2 - 200,
                100,
                10);

        Color c = Color.blue.darker();
        //c.a = 0.6f;
        g.setColor(c);
        g.fill(r);
        g.draw(r);

        g.setColor(Color.white);
        g.drawString(text, x + 20, y + 10);

    }

    public void setMessageDisplayed(String text, boolean dismiss) {
        if (dismiss) {
            this.dismissalMessageDisplayed = text;
        } else {
            this.messageDisplayed = text;
        }
    }

    public String getMessageDisplayed() {
        return this.dismissalMessageDisplayed;
    }

    public int spinWheel(int seed) {

        try {
            ang = seed * 36;
            wheelSpeed = 0.4f;
            wheelRoll = 0;

            spinWheel = true;
            while (wheelSpeed > 0 && wheelRoll == 0) {
                Thread.sleep(200);
            }
            spinWheel = false;
        } catch (Exception e) {

        }

        return wheelRoll;

    }

}
