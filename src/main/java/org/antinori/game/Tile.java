package org.antinori.game;

import java.awt.Color;

public class Tile {
	
	public static final Color LIGHT_GREY = new Color(224, 224, 224);
	public static final Color MED_GREY = new Color(206, 206, 206);
	public static final Color LIGHT_BLUE = new Color(68, 220, 240);
	public static final Color SKY_BLUE = new Color(75, 174, 215);
	public static final Color PANEL_BLUE = new Color(0, 153, 255);
	public static final Color LIGHT_GREEN = new Color(68, 189, 49);
	public static final Color PAY_DAY_GREEN = new Color(142, 189, 51);
	public static final Color TILE_YELLOW = new Color(236, 203, 43);
	public static final Color ORANGE = new Color(247, 135, 49);

	
	public static enum Type {NULL, COLLECT, LIFE, PAY, PAYDAY, LAWSUIT, MISS_TURN, MOVE_TO_MARRIED, SPIN_AGAIN, TAX_REFUND, TAXES_DUE, LOSE_JOB, 
		GET_MARRIED, TAKE_STW_CARD, COLLEGE_CAREER_CHOICE, CHANGE_CAREER, RETURN_TO_SCHOOL, BUY_STARTER_HOME, TAKE_FAMILY_PATH,
		BUY_BETTER_HOME,TAKE_RISKY_PATH, SPIN_TO_WIN, LIFE_ADD_CHILDREN, COLLECT_FOR_EACH_CHILD_FROM_OTHERS, PAY_PER_CHILD, PENSION, RETIRE}
	
	private String description = "";
	private Type type = Type.NULL;
	private int multiplication_factor = 1;
	private Color color = LIGHT_GREY;
	private int amount = 0;
	
	public Tile() {
		
	}
	
	public Tile(String desc, int amount, Type type, int factor, Color color) {
		this.description = desc;
		this.amount = amount;
		this.type = type;
		this.multiplication_factor = factor;
		this.color = color;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getMultiplication_factor() {
		return multiplication_factor;
	}
	public void setMultiplication_factor(int multiplication_factor) {
		this.multiplication_factor = multiplication_factor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Tile [description=" + description + ", type=" + type + ", multiplication_factor=" + multiplication_factor + ", color=" + color + ", amount=" + amount + "]";
	}


}
