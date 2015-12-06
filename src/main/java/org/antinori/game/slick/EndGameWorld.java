package org.antinori.game.slick;

import java.util.ArrayList;

import org.antinori.game.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;

public class EndGameWorld extends World {

    public EndGameWorld(int id) {
        super(id);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
        displayGameOver(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);
    }

    public void displayGameOver(GameContainer container, StateBasedGame game, Graphics g) {

        LifeWorld world = (LifeWorld) game.getState(LifeGame.GAME_STATE);
        ArrayList<Actor> actors = world.actors;

        int x = container.getWidth() / 2 - 150;
        int y = container.getHeight() / 2 - 100;
        int width = 300;
        int height = 50;
        int spacing = 10;

        RoundedRectangle r = new RoundedRectangle(
                x, //x
                y, //y
                width + spacing * 4, //width
                (height) * actors.size() + (spacing) * actors.size() + spacing, //height
                15); //radius on corner

        g.setColor(Color.green.darker());
        g.fill(r);
        g.draw(r);

        int inner_y = y + spacing;

        for (int i = 0; i < actors.size(); i++) {

            RoundedRectangle inner_rect = new RoundedRectangle(
                    x + spacing, //x
                    inner_y, //y
                    width + spacing * 2, //width
                    height, //height
                    6); //radius on corner

            inner_y += spacing;

            g.setColor(Color.green);
            g.fill(inner_rect);
            g.draw(inner_rect);

        }

        inner_y = y + spacing;

        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            Player p = actor.getPlayer();

            Color color = Color.black;

            g.setColor(color);
            g.setFont(world.font_16);
            g.drawString(p.getPlayerName() + " $" + p.getMoney(), x + spacing + 3, inner_y + i * height + 3);

        }

    }

}
