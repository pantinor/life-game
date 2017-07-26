package org.antinori.life.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public enum ActorType {

    PLAYER1("assets/images/pirate-walking.png", Color.PINK),
    PLAYER2("assets/images/arno-walking.png", Color.RED),
    PLAYER3("assets/images/builder-walking.png", Color.YELLOW),
    PLAYER4("assets/images/lady-walking.png", Color.CYAN),
    PLAYER5("assets/images/santa-walking.png", Color.GREEN),
    PLAYER6("assets/images/doctor-walking.png", Color.PURPLE),
    PLAYER7("assets/images/girl-walking.png", Color.MAGENTA),
    PLAYER8("assets/images/boy-walking.png", Color.BLUE);


    final private Animation east;
    final private Animation west;
    final private Animation north;
    final private Animation south;
    final private Animation southeast;
    final private Animation southwest;
    final private Animation northeast;
    final private Animation northwest;
    final private Color color;

    private ActorType(String sn, Color color) {

        TextureRegion[][] people_sheet = TextureRegion.split(new Texture(Gdx.files.classpath(sn)), 96, 96);
        east = new Animation(.1f, getTextureArray(people_sheet, 0, 0));
        north = new Animation(.1f, getTextureArray(people_sheet, 0, 1));
        northeast = new Animation(.1f, getTextureArray(people_sheet, 0, 2));
        northwest = new Animation(.1f, getTextureArray(people_sheet, 0, 3));
        south = new Animation(.1f, getTextureArray(people_sheet, 0, 4));
        southeast = new Animation(.1f, getTextureArray(people_sheet, 0, 5));
        southwest = new Animation(.1f, getTextureArray(people_sheet, 0, 6));
        west = new Animation(.1f, getTextureArray(people_sheet, 0, 7));
        
        this.color = color;
    }

    public Animation getAnimation(int direction) {

        Animation anim = south;
        switch (direction) {
            case Actor.NORTH:
                anim = north;
                break;
            case Actor.NORTH_WEST:
                anim = northwest;
                break;
            case Actor.NORTH_EAST:
                anim = northeast;
                break;
            case Actor.SOUTH:
                anim = south;
                break;
            case Actor.SOUTH_EAST:
                anim = southeast;
                break;
            case Actor.SOUTH_WEST:
                anim = southwest;
                break;
            case Actor.EAST:
                anim = east;
                break;
            case Actor.WEST:
                anim = west;
                break;
        }
        return anim;

    }
    
    public Color getColor() {
        return this.color;
    }

    private Array<TextureRegion> getTextureArray(TextureRegion[][] tr, int x, int y) {
        Array<TextureRegion> arr = new Array<>();
        arr.add(tr[x][y]);
        arr.add(tr[x + 1][y]);
        arr.add(tr[x + 2][y]);
        arr.add(tr[x + 3][y]);
        arr.add(tr[x + 4][y]);
        arr.add(tr[x + 5][y]);
        arr.add(tr[x + 6][y]);
        arr.add(tr[x + 7][y]);
        return arr;
    }

}
