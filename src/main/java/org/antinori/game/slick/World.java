package org.antinori.game.slick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antinori.game.twl.BasicTWLGameState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class World extends BasicTWLGameState {

    public static final int BELOW = -1;
    public static final int GAME = 0;
    public static final int ABOVE = 1;

    public GameContainer container = null;

    public int id = 0;

    public int width = 0;
    public int height = 0;

    /**
     * internal list for entities *
     */
    private List<Entity> entities = new ArrayList<Entity>();
    private List<Entity> removable = new ArrayList<Entity>();
    private List<Entity> addable = new ArrayList<Entity>();

    public int renderedEntities;

    /**
     * available commands for world *
     */
    protected Map<String, int[]> commands = new HashMap<String, int[]>();

    public World(int id) {
        this.id = id;
    }

    public World(int id, GameContainer container) {
        this.id = id;
        this.container = container;
    }

    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        this.container = container;
        if (width == 0) {
            width = container.getWidth();
        }
        if (height == 0) {
            height = container.getHeight();
        }
        // this.clear();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderedEntities = 0;

        // render entities
        for (Entity e : entities) {
            if (!e.visible) {
                continue; // next entity. this one stays invisible
            }
            renderEntity(e, g, container);

        }

    }

    private void renderEntity(Entity e, Graphics g, GameContainer container) throws SlickException {
        renderedEntities++;
        e.render(container, g);
    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (container == null) {
            throw new SlickException("no container set");
        }

        // add new entities
        if (addable.size() > 0) {
            for (Entity entity : addable) {
                entities.add(entity);
                entity.addedToWorld();
            }
            addable.clear();
            Collections.sort(entities);
        }

        for (Entity e : entities) {
            if (e.active) {
                e.update(container, delta);
            }
            e.checkWorldBoundaries();
        }

        // remove signed entities
        for (Entity entity : removable) {
            entities.remove(entity);
        }
        removable.clear();

    }

    @Override
    public int getID() {
        return id;
    }

    /**
     * Add entity to world and sort entity in z order
     *
     * @param e entity to add
     */
    public void add(Entity e, int... flags) {
        e.setWorld(this);
        if (flags.length == 1) {
            switch (flags[0]) {
                case BELOW:
                    break;
                case GAME:
                    addable.add(e);
                    break;
                case ABOVE:
                    break;
            }
        } else {
            addable.add(e);
        }
    }

    public void addAll(Collection<Entity> e, int... flags) {
        for (Entity entity : e) {
            this.add(entity, flags);
        }
    }

    /**
     * @return List of entities currently in this world
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     *
     * @param type
     * @return number of entities of the given type in this world
     */
    public int getNrOfEntities(String type) {
        if (entities.size() > 0) {
            int number = 0;
            for (Entity entity : entities) {
                if (entity.isType(type)) {
                    number++;
                }
            }
            return number;
        }
        return 0;
    }

    public List<Entity> getEntities(String type) {
        if (entities.size() > 0) {
            List<Entity> res = new ArrayList<Entity>();
            for (Entity entity : entities) {
                if (entity.isType(type)) {
                    res.add(entity);
                }
            }
            return res;
        }
        return new ArrayList<Entity>();
    }

    /**
     * @param entity to remove from game
     * @return false if entity is already set to be remove
     */
    public boolean remove(Entity entity) {
        if (!removable.contains(entity)) {
            return removable.add(entity);
        }
        return false;
    }

    /**
     * @param name
     * @return null if name is null or if no entity is found in game, entity
     * otherwise
     */
    public Entity find(String name) {
        if (name == null) {
            return null;
        }
        for (Entity entity : entities) {
            if (entity.name != null && entity.name.equalsIgnoreCase(name)) {
                return entity;
            }
        }
        // also look in addable list
        for (Entity entity : addable) {
            if (entity.name != null && entity.name.equalsIgnoreCase(name)) {
                return entity;
            }
        }

        return null;
    }

    /**
     * Remove all entities
     */
    public void clear() {
        for (Entity entity : entities) {
            entity.removedFromWorld();
        }
        entities.clear();
        addable.clear();
        removable.clear();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Entity> findEntityWithType(String type) {
        if (type == null) {
            Log.error("Parameter must be not null");
            return new ArrayList<Entity>();
        }
        List<Entity> result = new ArrayList<Entity>();
        for (Entity entity : entities) {
            if (entity.isType(type)) {
                result.add(entity);
            }
        }
        return result;
    }

    /**
     * @param x
     * @param y
     * @return true if an entity is already in position
     */
    public boolean isEmpty(int x, int y, int depth) {
        Rectangle rect;
        for (Entity entity : entities) {
            rect = new Rectangle(entity.x, entity.y, entity.width, entity.height);
            if (entity.depth == depth && rect.contains(x, y)) {
                return false;
            }
        }
        return true;
    }

    public Entity find(int x, int y) {
        Rectangle rect;
        for (Entity entity : entities) {
            rect = new Rectangle(entity.x, entity.y, entity.width, entity.height);
            if (rect.contains(x, y)) {
                return entity;
            }
        }
        return null;
    }

    /**
     * @return get number of entities in this world
     */
    public int getCount() {
        return entities.size();
    }

    /**
     * define commands to handle inputs
     *
     * @param command name of the command
     * @param keys keys or mouse input from {@link Input} class
     */
    public void define(String command, int... keys) {
        commands.put(command, keys);
    }

    /**
     * Check if a command is down
     *
     * @param key
     * @return
     */
    public boolean check(String command) {
        int[] checked = commands.get(command);
        if (checked == null) {
            return false;
        }
        for (int i = 0; i < checked.length; i++) {
            if (this.container.getInput().isKeyDown(checked[i])) {
                return true;
            } else if (checked[i] < 10) {
                /**
                 * 10 is max number of button on a mouse
                 *
                 * @see Input
                 */
                if (this.container.getInput().isMousePressed(checked[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if a command is pressed
     *
     * @param key
     * @return
     */
    public boolean pressed(String command) {
        int[] checked = commands.get(command);
        if (checked == null) {
            return false;
        }
        for (int i = 0; i < checked.length; i++) {
            if (this.container.getInput().isKeyPressed(checked[i])) {
                return true;
            } else if (checked[i] == Input.MOUSE_LEFT_BUTTON || checked[i] == Input.MOUSE_RIGHT_BUTTON) {
                if (this.container.getInput().isMousePressed(checked[i])) {
                    return true;
                }
            }
        }
        return false;
    }

}
