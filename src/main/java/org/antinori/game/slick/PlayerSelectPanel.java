package org.antinori.game.slick;

import java.util.HashMap;

import org.newdawn.slick.SpriteSheet;

import de.matthiasmann.twl.ThemeInfo;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.OptionBooleanModel;
import de.matthiasmann.twl.model.SimpleIntegerModel;


public class PlayerSelectPanel extends Widget {
    
    private int numSlotsX;
    private int numSlotsY;
    private final ItemSlot[] slot;
    private int slotSpacing;
    private SimpleIntegerModel optionModel;
    String[] icon_names;
    
    public PlayerSelectPanel(int numSlotsX, int numSlotsY, String[] icon_names, boolean othersPanel, String[] player_names) {
        this.numSlotsX = numSlotsX;
        this.numSlotsY = numSlotsY;
        this.slot = new ItemSlot[numSlotsX * numSlotsY];
        this.icon_names = icon_names;
        
        if (!othersPanel) {
	        optionModel = new SimpleIntegerModel(1, slot.length, 1);
        }
        
        for(int i=0 ; i<slot.length ; i++) {
            slot[i] = new ItemSlot();
            add(slot[i]);
            if (!othersPanel) slot[i].setModel(new OptionBooleanModel(optionModel, i+1));
        }
        
        for(int i=0 ; i<icon_names.length ; i++) {
	        slot[i].setItem(icon_names[i]);
        }
        
        if (player_names != null) {
            for(int i=0 ; i<player_names.length ; i++) {
    	        slot[i].setText(player_names[i]+"\n\n\n\n\n\n");//need multilines in order for top alignment to kick in
            }
            slot[0].setActive(true);
        }
        

    }
    
    public ActorType.Type getSelectedPlayerIcon() {
    	    	
    	ActorType.Type type = ActorType.Type.PLAYER1;
        for(int i=0 ; i<icon_names.length ; i++) {
        	if (slot[i].isActive()) {
        		if (icon_names[i].equals("girl")) type = ActorType.Type.PLAYER1;
        		if (icon_names[i].equals("arno")) type = ActorType.Type.PLAYER2;
        		if (icon_names[i].equals("builder")) type = ActorType.Type.PLAYER3;
        		if (icon_names[i].equals("lady")) type = ActorType.Type.PLAYER4;
        		if (icon_names[i].equals("santa")) type = ActorType.Type.PLAYER5;
        		if (icon_names[i].equals("doctor")) type = ActorType.Type.PLAYER6;
        		if (icon_names[i].equals("pirate")) type = ActorType.Type.PLAYER7;
        	}
        }
        return type;
    }
    
    public ActorType.Type getPlayerIcon(String name) {
    	ActorType.Type type = ActorType.Type.PLAYER1;
		if (name.equals("girl")) type = ActorType.Type.PLAYER1;
		if (name.equals("arno")) type = ActorType.Type.PLAYER2;
		if (name.equals("builder")) type = ActorType.Type.PLAYER3;
		if (name.equals("lady")) type = ActorType.Type.PLAYER4;
		if (name.equals("santa")) type = ActorType.Type.PLAYER5;
		if (name.equals("doctor")) type = ActorType.Type.PLAYER6;
		if (name.equals("pirate")) type = ActorType.Type.PLAYER7;
        return type;
    }
    
    public HashMap<String,ActorType.Type> getSelectedPlayersMap() {
    	HashMap<String,ActorType.Type> map = new HashMap<String,ActorType.Type>();
        for(int i=0 ; i<slot.length ; i++) {
        	if (slot[i].isActive()) {
        		map.put(slot[i].getText().trim(), getPlayerIcon(slot[i].getItem()));
        	}
        }
    	return map;
    }

    @Override
    public int getPreferredInnerWidth() {
        return (slot[0].getPreferredWidth() + slotSpacing)*numSlotsX - slotSpacing;
    }

    @Override
    public int getPreferredInnerHeight() {
        return (slot[0].getPreferredHeight() + slotSpacing)*numSlotsY - slotSpacing;
    }

    @Override
    protected void layout() {
        int slotWidth  = slot[0].getPreferredWidth();
        int slotHeight = slot[0].getPreferredHeight();
        
        for(int row=0,y=getInnerY(),i=0 ; row<numSlotsY ; row++) {
            for(int col=0,x=getInnerX() ; col<numSlotsX ; col++,i++) {
                slot[i].adjustSize();
                slot[i].setPosition(x, y);
                x += slotWidth + slotSpacing;
            }
            y += slotHeight + slotSpacing;
        }
    }

    @Override
    protected void applyTheme(ThemeInfo themeInfo) {
        super.applyTheme(themeInfo);
        slotSpacing = themeInfo.getParameter("slotSpacing", 5);
    }   
 
    
}
