package org.antinori.life.gdx;

import java.util.List;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import java.util.Iterator;

public class Hud {

    private class LogEntry {

        private String text;
        private Color color;

        public LogEntry(String text, Color color) {
            this.text = text;
            this.color = color;
        }
    }
    private final List<LogEntry> logs = new FixedSizeArrayList<>(25);

    static final int LOG_AREA_WIDTH = 260;
    static final int LOG_AREA_TOP = 375;
    static final int LOG_X = Life.VIEWPORT_DIM_WIDTH + 105;

    private final GlyphLayout layout = new GlyphLayout(Life.font, "");
    private final GlyphLayout layoutSmall = new GlyphLayout(Life.fontSmall, "");

    public void add(String s, Color color) {
        synchronized (logs) {
            logs.add(new LogEntry(s, color));
        }
    }

    public void render(Batch batch, NewGame game) {

        int y = Life.SCREEN_DIM_HEIGHT - 5;

        Iterator<Player> players = game.getPlayers();
        while (players.hasNext()) {
            Player p = players.next();
            layoutSmall.setText(Life.fontSmall, p.toShortString(), p.equals(game.currentPlayer()) ? Color.SALMON : Color.WHITE, LOG_AREA_WIDTH, Align.left, true);
            Life.fontSmall.draw(batch, layoutSmall, LOG_X, y);
            layoutSmall.setText(Life.fontSmall, p.displaySTW(), p.equals(game.currentPlayer()) ? Color.SALMON : Color.WHITE, LOG_AREA_WIDTH, Align.left, true);
            Life.fontSmall.draw(batch, layoutSmall, LOG_X + 155, y - 20);
            y -= 80;
        }

        y = 20;

        synchronized (logs) {
            ReverseListIterator iter = new ReverseListIterator(logs);
            while (iter.hasNext()) {
                LogEntry next = (LogEntry) iter.next();
                layout.setText(Life.font, next.text, next.color, LOG_AREA_WIDTH, Align.left, true);
                y += layout.height + 12;
                if (y > LOG_AREA_TOP) {
                    break;
                }

                Life.font.draw(batch, layout, LOG_X, y);
            }
        }
    }
}
