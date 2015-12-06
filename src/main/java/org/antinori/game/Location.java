package org.antinori.game;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Location implements Serializable {

    private final int x;
    private final int y;
    private int height;
    private boolean blocked;
    private Color color = Color.gray;
    private Tile tile = new Tile();
    private int pixelX = 0;
    private int pixelY = 0;
    private Location nextLocation = null;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Location [" + x + "][" + y + "] " + tile.toString();
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getPixelX() {
        return pixelX;
    }

    public void setPixelX(double pixelX) {
        this.pixelX = (int) pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public void setPixelY(double pixelY) {
        this.pixelY = (int) pixelY;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

}
