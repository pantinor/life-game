package org.antinori.life.gdx;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface BaseScreen extends Screen, InputProcessor {

    public void setMapPixelCoords(float x, float y);

    public Stage getStage();
    
    public void move(org.antinori.life.gdx.Actor actor, int x, int y) ;

}
