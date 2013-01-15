package org.antinori.game.cards;


public class HouseCard extends Card {
	
	public enum HomeType {EC,PS,LMR,M,MV,DWRV,TS,MH,LC,RS,C,SC};
	
	private int value = 0;
	private int sell_for = 0;
	private HomeType homeType ;
	
	public HouseCard(Type base_type, HomeType type, int value, int sell) {
		
		this.type = base_type;
		this.homeType = type;
		this.sell_for = sell;
		this.value = value;
		
		switch(type) {

		case TS: title = "TUDOR STYLE"; break;
		case MH: title = "MOBILE HOME"; break;
		case LC: title = "LOG CABIN"; break;
		case RS: title = "RANCH STYLE"; break;
		case C: title = "CONDO"; break;
		case SC: title = "SMALL CAPE"; break;
		
		case EC: title = "EXECUTIVE CAPE"; break;
		case PS: title = "PENTHOUSE SUITE"; break;
		case LMR: title = "LUXURY MOUNTAIN RETREAT"; break;
		case M: title = "MANSION"; break;
		case MV: title = "MODERN VICTORIAN"; break;
		case DWRV: title = "DOUBLE WIDE RV"; break;
		
		}

		description = title;
	}

	
	public int getValue() {
		return value;
	}
	
	public int getSellFor() {
		return sell_for;
	}
	
	public HomeType getHomeType() {
		return homeType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			return (this.description == (String)obj);			
		}
		if (obj instanceof HouseCard) {
			return (this.homeType == ((HouseCard)obj).getHomeType());			
		}
		return super.equals(obj);
	}


}
