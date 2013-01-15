package org.antinori.game.slick;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class MovingState implements State {

	private Actor actor;
	
	public static final int NORTH = 0;
	public static final int NORTH_EAST = 1;
	public static final int EAST = 2;
	public static final int SOUTH_EAST = 3;
	public static final int SOUTH = 4;
	public static final int SOUTH_WEST = 5;
	public static final int WEST = 6;
	public static final int NORTH_WEST = 7;
	
	private int direction = -1;

	public MovingState(Entity e) {
		this.actor = (Actor)e;
	}
	
	public void init() {
		
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void render(Graphics g) {
				
		if (direction != -1) {
			Animation anim = actor.getType().getAnimation(direction);
			anim.draw(actor.x, actor.y);
		} else {
			g.drawImage(actor.getType().getImage(direction),actor.x, actor.y);		
		}
		
	}

	public void update(GameContainer container, int delta) {
		

	}






}
