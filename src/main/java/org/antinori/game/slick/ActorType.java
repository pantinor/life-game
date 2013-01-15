package org.antinori.game.slick;

import org.antinori.game.cards.CareerCard;
import org.antinori.game.cards.HouseCard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ActorType {
	
	public enum Type {PLAYER1,PLAYER2,PLAYER3,PLAYER4,PLAYER5,PLAYER6};
		
	private Animation east;
	private Animation west;
	private Animation north;
	private Animation south;
	
	private Animation southeast;
	private Animation southwest;
	private Animation northeast;
	private Animation northwest;
	SpriteSheet homes_sheet;
	SpriteSheet careers_sheet;

	
	public static final int DURATION =  150;
	
	public ActorType(Type type) throws SlickException {
		
		SpriteSheet people_sheet1 = new SpriteSheet("girl-walking.png", 96, 96);
		SpriteSheet people_sheet2 = new SpriteSheet("arno-walking.png", 96, 96);
		SpriteSheet people_sheet3 = new SpriteSheet("builder-walking.png", 96, 96);
		SpriteSheet people_sheet4 = new SpriteSheet("lady-walking.png", 96, 96);
		SpriteSheet people_sheet5 = new SpriteSheet("santa-walking.png", 96, 96);
		SpriteSheet people_sheet6 = new SpriteSheet("doctor-walking.png", 96, 96);
		
		homes_sheet = new SpriteSheet("homes.png", 96, 96);
		careers_sheet = new SpriteSheet("careers.png", 96, 96);
		
		//SpriteSheet map_sprites = new SpriteSheet("./96x96-sprites.png", 96, 96);



		this.east = new Animation();
		this.west = new Animation();
		this.north = new Animation();
		this.south = new Animation();
		this.southeast = new Animation();
		this.southwest = new Animation();
		this.northwest = new Animation();
		this.northeast = new Animation();
		
		/*
		
		switch (type) {
		case PLAYER1: {
			east.addFrame(map_sprites.getSprite(0, 1), DURATION);break;
		}
		case PLAYER2: {
			east.addFrame(map_sprites.getSprite(1, 1), DURATION);break;
		}
		case PLAYER3: {
			east.addFrame(map_sprites.getSprite(2, 1), DURATION);break;
		}
		case PLAYER4: {
			east.addFrame(map_sprites.getSprite(3, 1), DURATION);break;
		}
		case PLAYER5: {
			east.addFrame(map_sprites.getSprite(4, 1), DURATION);break;
		}
		case PLAYER6: {
			east.addFrame(map_sprites.getSprite(0, 1), DURATION);break;
		}
		}
		
		*/
		
		
		switch (type) {
		case PLAYER1: {
			for (int i=0;i<8;i++) east.addFrame(people_sheet1.getSprite(0, i), DURATION);
			for (int i=0;i<8;i++) north.addFrame(people_sheet1.getSprite(1, i), DURATION);
			for (int i=0;i<8;i++) northeast.addFrame(people_sheet1.getSprite(2, i), DURATION);
			for (int i=0;i<8;i++) northwest.addFrame(people_sheet1.getSprite(3, i), DURATION);
			for (int i=0;i<8;i++) south.addFrame(people_sheet1.getSprite(4, i), DURATION);
			for (int i=0;i<8;i++) southeast.addFrame(people_sheet1.getSprite(5, i), DURATION);
			for (int i=0;i<8;i++) southwest.addFrame(people_sheet1.getSprite(6, i), DURATION);
			for (int i=0;i<8;i++) west.addFrame(people_sheet1.getSprite(7, i), DURATION);
			break;
		}
		case PLAYER2: {
			for (int i=0;i<8;i++) east.addFrame(people_sheet2.getSprite(0, i), DURATION);
			for (int i=0;i<8;i++) north.addFrame(people_sheet2.getSprite(1, i), DURATION);
			for (int i=0;i<8;i++) northeast.addFrame(people_sheet2.getSprite(2, i), DURATION);
			for (int i=0;i<8;i++) northwest.addFrame(people_sheet2.getSprite(3, i), DURATION);
			for (int i=0;i<8;i++) south.addFrame(people_sheet2.getSprite(4, i), DURATION);
			for (int i=0;i<8;i++) southeast.addFrame(people_sheet2.getSprite(5, i), DURATION);
			for (int i=0;i<8;i++) southwest.addFrame(people_sheet2.getSprite(6, i), DURATION);
			for (int i=0;i<8;i++) west.addFrame(people_sheet2.getSprite(7, i), DURATION);
			break;
		}
		case PLAYER3: {
			for (int i=0;i<8;i++) east.addFrame(people_sheet3.getSprite(0, i), DURATION);
			for (int i=0;i<8;i++) north.addFrame(people_sheet3.getSprite(1, i), DURATION);
			for (int i=0;i<8;i++) northeast.addFrame(people_sheet3.getSprite(2, i), DURATION);
			for (int i=0;i<8;i++) northwest.addFrame(people_sheet3.getSprite(3, i), DURATION);
			for (int i=0;i<8;i++) south.addFrame(people_sheet3.getSprite(4, i), DURATION);
			for (int i=0;i<8;i++) southeast.addFrame(people_sheet3.getSprite(5, i), DURATION);
			for (int i=0;i<8;i++) southwest.addFrame(people_sheet3.getSprite(6, i), DURATION);
			for (int i=0;i<8;i++) west.addFrame(people_sheet3.getSprite(7, i), DURATION);
			break;
		}
		case PLAYER4: {
			for (int i=0;i<8;i++) east.addFrame(people_sheet4.getSprite(0, i), DURATION);
			for (int i=0;i<8;i++) north.addFrame(people_sheet4.getSprite(1, i), DURATION);
			for (int i=0;i<8;i++) northeast.addFrame(people_sheet4.getSprite(2, i), DURATION);
			for (int i=0;i<8;i++) northwest.addFrame(people_sheet4.getSprite(3, i), DURATION);
			for (int i=0;i<8;i++) south.addFrame(people_sheet4.getSprite(4, i), DURATION);
			for (int i=0;i<8;i++) southeast.addFrame(people_sheet4.getSprite(5, i), DURATION);
			for (int i=0;i<8;i++) southwest.addFrame(people_sheet4.getSprite(6, i), DURATION);
			for (int i=0;i<8;i++) west.addFrame(people_sheet4.getSprite(7, i), DURATION);
			break;
		}
		case PLAYER5: {
			for (int i=0;i<8;i++) east.addFrame(people_sheet5.getSprite(0, i), DURATION);
			for (int i=0;i<8;i++) north.addFrame(people_sheet5.getSprite(1, i), DURATION);
			for (int i=0;i<8;i++) northeast.addFrame(people_sheet5.getSprite(2, i), DURATION);
			for (int i=0;i<8;i++) northwest.addFrame(people_sheet5.getSprite(3, i), DURATION);
			for (int i=0;i<8;i++) south.addFrame(people_sheet5.getSprite(4, i), DURATION);
			for (int i=0;i<8;i++) southeast.addFrame(people_sheet5.getSprite(5, i), DURATION);
			for (int i=0;i<8;i++) southwest.addFrame(people_sheet5.getSprite(6, i), DURATION);
			for (int i=0;i<8;i++) west.addFrame(people_sheet5.getSprite(7, i), DURATION);
			break;
		}
		case PLAYER6: {
			for (int i=0;i<8;i++) east.addFrame(people_sheet6.getSprite(0, i), DURATION);
			for (int i=0;i<8;i++) north.addFrame(people_sheet6.getSprite(1, i), DURATION);
			for (int i=0;i<8;i++) northeast.addFrame(people_sheet6.getSprite(2, i), DURATION);
			for (int i=0;i<8;i++) northwest.addFrame(people_sheet6.getSprite(3, i), DURATION);
			for (int i=0;i<8;i++) south.addFrame(people_sheet6.getSprite(4, i), DURATION);
			for (int i=0;i<8;i++) southeast.addFrame(people_sheet6.getSprite(5, i), DURATION);
			for (int i=0;i<8;i++) southwest.addFrame(people_sheet6.getSprite(6, i), DURATION);
			for (int i=0;i<8;i++) west.addFrame(people_sheet6.getSprite(7, i), DURATION);
			break;
		}

		}
		
		
	}
	
	public Image getHomeImage(HouseCard.HomeType type) {
		Image img = null;
		switch(type) {
		case EC:img = homes_sheet.getSprite(0,0);break;
		case PS:img = homes_sheet.getSprite(4,0);break;
		case LMR:img = homes_sheet.getSprite(0,1);break;
		case M:img = homes_sheet.getSprite(3,0);break;
		case MV:img = homes_sheet.getSprite(1,0);break;
		case DWRV:img = homes_sheet.getSprite(2,0);break;
		case TS:img = homes_sheet.getSprite(1,1);break;
		case MH:img = homes_sheet.getSprite(5,0);break;
		case LC:img = homes_sheet.getSprite(2,1);break;
		case RS:img = homes_sheet.getSprite(3,1);break;
		case C:img = homes_sheet.getSprite(4,1);break;
		case SC:img = homes_sheet.getSprite(5,1);break;
		}
		return img;
		
	}
	
	public Image getCareerImage(CareerCard.CareerType type) {
		
		Image img = null;
		switch(type) {
		case TYPE_SALESPERSON:img = careers_sheet.getSprite(1,1);break;
		case TYPE_MECHANIC:img = careers_sheet.getSprite(5,1);break;
		case TYPE_POLICE_OFFICER:img = careers_sheet.getSprite(5,0);break;
		case TYPE_ENTERTAINER:img = careers_sheet.getSprite(2,0);break;
		case TYPE_ATHLETE:img = careers_sheet.getSprite(1,0);break;
		case TYPE_HAIR_STYLIST:img = careers_sheet.getSprite(0,0);break;
		case TYPE_TEACHER:img = careers_sheet.getSprite(4,1);break;
		case TYPE_VETERINARIAN:img = careers_sheet.getSprite(2,1);break;
		case TYPE_LAWYER:img = careers_sheet.getSprite(3,1);break;
		case TYPE_DOCTOR:img = careers_sheet.getSprite(0,1);break;
		case TYPE_COMPUTER_DESIGNER:img = careers_sheet.getSprite(4,0);break;
		case TYPE_ACCOUNTANT:img = careers_sheet.getSprite(3,0);break;
		}
		return img;
		
	}
	

	
	public Image getImage(int direction) {
		
		//return east.getCurrentFrame();
		
		Animation anim = southwest;
		switch(direction) {
		case MovingState.NORTH:anim = north;break;
		case MovingState.NORTH_WEST:anim = northwest;break;
		case MovingState.NORTH_EAST:anim = northeast;break;
		case MovingState.SOUTH:anim = south;break;
		case MovingState.SOUTH_EAST:anim = southeast;break;
		case MovingState.SOUTH_WEST:anim = southwest;break;
		case MovingState.EAST:anim = east;break;
		case MovingState.WEST:anim = west;break;
		}
		Image image = anim.getCurrentFrame();
		return image;
		
	}
	
	public Animation getAnimation(int direction) {
		//return east;

		
		Animation anim = south;
		switch(direction) {
		case MovingState.NORTH:anim = north;break;
		case MovingState.NORTH_WEST:anim = northwest;break;
		case MovingState.NORTH_EAST:anim = northeast;break;
		case MovingState.SOUTH:anim = south;break;
		case MovingState.SOUTH_EAST:anim = southeast;break;
		case MovingState.SOUTH_WEST:anim = southwest;break;
		case MovingState.EAST:anim = east;break;
		case MovingState.WEST:anim = west;break;
		}
		return anim;
		
	}
	
}
