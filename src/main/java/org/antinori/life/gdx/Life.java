/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antinori.life.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.antinori.game.Dice;
import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;

public class Life extends Game {

    public static final int TILE_DIM = 96;
    public static final int SCREEN_DIM_WIDTH = TILE_DIM * 15;
    public static final int SCREEN_DIM_HEIGHT = TILE_DIM * 9;
    public static final int VIEWPORT_DIM_WIDTH = TILE_DIM * 11;
    public static final int VIEWPORT_DIM_HEIGHT = TILE_DIM * 9;

    TextureRegion[][] homes_sheet;
    TextureRegion[][] careers_sheet;

    public static Skin skin;
    public static BitmapFont font;
    public static BitmapFont fontSmall;
    public static Hud hud;
    
    public static final Dice dice = new Dice(1, 10);

    public static void main(String[] args) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = SCREEN_DIM_WIDTH;
        config.height = SCREEN_DIM_HEIGHT;
        config.resizable = false;
        config.title = "Life";
        new LwjglApplication(new Life(), config);
    }

    @Override
    public void create() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.classpath("assets/fonts/gnuolane.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 18;
        font = generator.generateFont(parameter);
        
        parameter.size = 16;
        fontSmall = generator.generateFont(parameter);

        parameter.size = 16;
        parameter.color = Color.BLACK;
        BitmapFont small = generator.generateFont(parameter);

        generator.dispose();

        skin = new Skin(Gdx.files.classpath("assets/skin/uiskin.json"));
        skin.remove("default-font", BitmapFont.class);
        skin.add("default-font", font, BitmapFont.class);
        skin.add("small-font", small, BitmapFont.class);
        Label.LabelStyle ls = new Label.LabelStyle();
        skin.add("small-font", ls, Label.LabelStyle.class);
        ls.font = small;
        
        hud = new Hud();

        homes_sheet = TextureRegion.split(new Texture(Gdx.files.classpath("assets/images/homes.png")), 96, 96);
        careers_sheet = TextureRegion.split(new Texture(Gdx.files.classpath("assets/images/careers.png")), 96, 96);

        GameScreen gameScreen = new GameScreen(this);
        Screen start = new PlayerSelectionScreen(this, gameScreen);
        setScreen(start);
    }

    public TextureRegion getHomeImage(HouseCard.HomeType type) {
        TextureRegion img = null;
        switch (type) {
            case EC:
                img = homes_sheet[0][0];
                break;
            case PS:
                img = homes_sheet[4][0];
                break;
            case LMR:
                img = homes_sheet[0][1];
                break;
            case M:
                img = homes_sheet[3][0];
                break;
            case MV:
                img = homes_sheet[1][0];
                break;
            case DWRV:
                img = homes_sheet[2][0];
                break;
            case TS:
                img = homes_sheet[1][1];
                break;
            case MH:
                img = homes_sheet[5][0];
                break;
            case LC:
                img = homes_sheet[2][1];
                break;
            case RS:
                img = homes_sheet[3][1];
                break;
            case C:
                img = homes_sheet[4][1];
                break;
            case SC:
                img = homes_sheet[5][1];
                break;
        }
        return img;

    }

    public TextureRegion getCareerImage(CareerCard.CareerType type) {

        TextureRegion img = null;
        switch (type) {
            case TYPE_SALESPERSON:
                img = careers_sheet[1][1];
                break;
            case TYPE_MECHANIC:
                img = careers_sheet[5][1];
                break;
            case TYPE_POLICE_OFFICER:
                img = careers_sheet[5][0];
                break;
            case TYPE_ENTERTAINER:
                img = careers_sheet[2][0];
                break;
            case TYPE_ATHLETE:
                img = careers_sheet[1][0];
                break;
            case TYPE_HAIR_STYLIST:
                img = careers_sheet[0][0];
                break;
            case TYPE_TEACHER:
                img = careers_sheet[4][1];
                break;
            case TYPE_VETERINARIAN:
                img = careers_sheet[2][1];
                break;
            case TYPE_LAWYER:
                img = careers_sheet[3][1];
                break;
            case TYPE_DOCTOR:
                img = careers_sheet[0][1];
                break;
            case TYPE_COMPUTER_DESIGNER:
                img = careers_sheet[4][0];
                break;
            case TYPE_ACCOUNTANT:
                img = careers_sheet[3][0];
                break;
        }
        return img;

    }

    public static final FileHandleResolver CLASSPTH_RSLVR = new FileHandleResolver() {
        @Override
        public FileHandle resolve(String fileName) {
            return Gdx.files.classpath(fileName);
        }
    };

}
