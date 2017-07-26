package org.antinori.life.gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Actor extends com.badlogic.gdx.scenes.scene2d.Actor {

    public static final int NORTH = 0;
    public static final int NORTH_EAST = 1;
    public static final int EAST = 2;
    public static final int SOUTH_EAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTH_WEST = 5;
    public static final int WEST = 6;
    public static final int NORTH_WEST = 7;

    private final ActorType type;

    private float time;
    private int direction = -1;
    private Player player;
    private TextureRegion tr;

    public Actor(ActorType type) {
        this.type = type;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }
    
    public ActorType getType() {
        return this.type;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        if (tr == null) {
            tr = (TextureRegion) type.getAnimation(SOUTH).getKeyFrame(0, true);
        }

        if (this.direction != -1) {
            tr = (TextureRegion) type.getAnimation(direction).getKeyFrame(time, true);
        }

        batch.draw(tr, getX(), getY());
        
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
    }

}
