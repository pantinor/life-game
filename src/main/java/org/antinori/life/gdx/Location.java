package org.antinori.life.gdx;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import org.antinori.game.Tile;

public class Location {

    private boolean blocked;
    private Color color = Color.GRAY;
    private Tile tile;

    private final int index;
    private final int x;
    private final int y;
    private final Array<Connection<Location>> connections;

    public Location(int index, final int x, final int y, final int capacity) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.connections = new Array<>(capacity);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return x * y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location loc = (Location) obj;
            return (loc.getX() == this.x && loc.getY() == this.y);
        } else {
            return false;
        }
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Location [index=" + index + ", x=" + x + ", y=" + y + ", connections=" + connections + " tile=" + tile + "]";
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getIndex() {
        return index;
    }

    public Array<Connection<Location>> getConnections() {
        return connections;
    }

}
