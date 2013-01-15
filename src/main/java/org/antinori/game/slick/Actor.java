package org.antinori.game.slick;

import org.antinori.game.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.navmesh.NavPath;

public class Actor extends Entity {

	private ActorType type;
	private Player player;
	private static final float SPEED = 0.15f;
	private NavPath currentPath;
	private int pathStep;
	private int moveIterations = 0;
	private float dx;
	private float dy;
	private float finalx = -1;
	private float finaly;
	private float tx;
	private float ty;
	private int direction;

	public Actor(ActorType.Type type, int tileX, int tileY) throws SlickException {
			
		super(tileX, tileY);
		
		this.type = new ActorType(type);
				
		stateManager.addAll(new MovingState(this));		

	}

	public ActorType getType() {
		return type;
	}
	
	public void setPlayerObject(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setIsCurrent() {
		LifeWorld w = (LifeWorld)this.world;
		w.currentPlayer = this;
	}
	
	public void waitUntilMovedToDestination(int targetTileX, int targetTileY) {
		
		pathStep = 0;
		this.finalx = targetTileX * LifeWorld.TILE_SIZE;
		this.finaly = targetTileY * LifeWorld.TILE_SIZE;
		
		LifeWorld w = (LifeWorld)this.world;
		if (w == null) {
			setCurrentTile(targetTileX, targetTileY);
			return;
		}
		currentPath = w.map.getPath(this.getCurrentTileX(), this.getCurrentTileY(), targetTileX, targetTileY);
		
		//block until destination reached
		while (currentPath != null) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
			}			
		}
		
		setCurrentTile(targetTileX, targetTileY);

	}
	
	
	public void update(GameContainer container, int delta) throws SlickException {
		
		if (currentPath != null) {
			considerNextStep();
			moveIterations--;
			x += dx;
			y += dy;
			if (moveIterations <= 0) {
				nextStep();
			}
		}

		super.update(container, delta);
	}
	
	
	private void considerNextStep() {
		
		if (currentPath == null) return;
		
		if (pathStep < currentPath.length()-1) {
			
			tx = currentPath.getX(pathStep+1);
			ty = currentPath.getY(pathStep+1);
			
			nextStep();
		}
		
	}
	
	
	private void nextStep() {
		
		if (currentPath == null) return;
		
		pathStep++;
		if (pathStep >= currentPath.length()) {
			currentPath = null;
			dx = 0;
			dy = 0;
			
			MovingState ms = (MovingState)this.stateManager.currentState();
			ms.setDirection(-1); //stop the animation
			
			return;
		}
		
		tx = currentPath.getX(pathStep);
		ty = currentPath.getY(pathStep);
		
		if (pathStep == currentPath.length() - 1) {
			tx = finalx;
			ty = finaly;
		}
		
		dx = (tx - x);
		dy = (ty - y);
		
		moveIterations = (int) (Math.sqrt((dx*dx)+(dy*dy)) / SPEED);
		dx = dx / moveIterations;
		dy = dy / moveIterations;
		
		setDirection(dx,dy);
	}
	
	private void setDirection(float dx, float dy) {
		float ang = (float) Math.toDegrees(Math.atan2(dy, dx));
		if (ang < 0) {
			ang = 360 + ang;
		}
		ang = (ang + 90 + 22.5f) % 360;
		ang /= 45f;
		direction = (int) ang;
		
		MovingState ms = (MovingState)this.stateManager.currentState();
		ms.setDirection(direction);
	}
	
	


}
