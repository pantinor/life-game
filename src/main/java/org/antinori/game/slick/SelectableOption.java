package org.antinori.game.slick;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.RoundedRectangle;

public class SelectableOption extends TextEntity {

    private boolean renderBorder;
    private boolean enableClick;
    private Color rect_color;
    private int index;

    public SelectableOption(float x, float y, Font font, String text, int index, Color c, boolean enableClick) {
        super(x, y, font, text);
        this.rect_color = c;
        this.index = index;
        this.enableClick = enableClick;
        this.name = "SelectableOption" + index;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        if (enableClick) {
            if (renderBorder) {
                RoundedRectangle r = new RoundedRectangle(x - 15, y, width + 30, height + 2, 8);
                //rect_color.a = 0.6f;
                g.setColor(rect_color);
                g.fill(r);
                g.draw(r);
            } else {
                RoundedRectangle r = new RoundedRectangle(x - 15, y, width + 30, height + 2, 8);
                g.setColor(rect_color);
                g.draw(r);
            }
            g.setColor(Color.white);

            super.render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        super.update(container, delta);

        Input input = container.getInput();
        if (collidePoint(input.getMouseX(), input.getMouseY())) {
            renderBorder = true;
        } else {
            renderBorder = false;
        }

        if (enableClick && renderBorder && input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            LifeWorld w = (LifeWorld) this.world;
            //w.selectedOption = this.index;
        }

    }

}
