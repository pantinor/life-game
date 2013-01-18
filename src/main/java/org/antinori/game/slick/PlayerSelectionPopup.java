package org.antinori.game.slick;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.PopupWindow;
import de.matthiasmann.twl.SimpleDialog;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.OptionBooleanModel;
import de.matthiasmann.twl.model.SimpleIntegerModel;


public class PlayerSelectionPopup {
	
	HashMap<String,ActorType.Type> selection = new HashMap<String,ActorType.Type>();
	String playerName;
	ActorType.Type selected_icon;
	Widget owner;
	PopupWindow popupWindow;
	boolean dismiss = false;
   
    
	private DialogLayout layout;
    private Button jButton1;
    private Label jLabel1;
    private Label selectOthersLabel;
    private EditField jTextField1;
    
    private PlayerSelectPanel playerSelectTable;
    private PlayerSelectPanel otherPlayerTable; 
   
    public PlayerSelectionPopup() {
    }

    public PopupWindow showDialog(Widget owner) {
    	
        if(owner == null) {
            throw new NullPointerException("no owner");
        }
        
        this.owner = owner;
        
        popupWindow = new PopupWindow(owner);

		layout = new DialogLayout();
		jButton1 = new Button();
        jLabel1 = new Label();
        selectOthersLabel = new Label();
        jButton1 = new Button();
        jTextField1 = new EditField();

        jLabel1.setText("Enter Your Name and Select Your Icon");
        selectOthersLabel.setText("Select the Other Computer Players");
        jButton1.setText("Start Game");
        jTextField1.setText("Joe");
        
    	String[] names = {"arno","builder","girl","lady","pirate","doctor","santa","boy"};
        playerSelectTable = new PlayerSelectPanel(4,2,names,false,null);

    	String[] others = {"builder","girl","lady","pirate","doctor"};
    	String[] players = {"Ruby","Rosa","Ada","Brandon","Jessika"};
        otherPlayerTable = new PlayerSelectPanel(5,1,others,true,players);

        
        
		jButton1.addCallback(new Runnable() {
			public void run() {
				OkButtonActionPerformed();
			}
		});


	       layout.setHorizontalGroup(
	               layout.createParallelGroup()
	               .addGroup(layout.createSequentialGroup()
	                   .addGap()
	                   .addGroup(layout.createParallelGroup()
	                       .addGroup(layout.createSequentialGroup()
	                           .addGroup(layout.createParallelGroup()
	                               .addWidget(jButton1)
	                               .addWidget(selectOthersLabel))
	                           .addGap(15,15,15))
	                       .addGroup(layout.createSequentialGroup()
	                           .addGroup(layout.createParallelGroup()
	                               .addWidget(otherPlayerTable)
	                               .addWidget(playerSelectTable)
	                               .addGroup(layout.createSequentialGroup()
	                                   .addGroup(layout.createParallelGroup()
	                                       .addWidget(jLabel1)
	                                       .addWidget(jTextField1))
	                                   .addGap()))
	                           .addGap())))
	           );
	           
	           
	           
	                   layout.setVerticalGroup(
	   	            layout.createParallelGroup()
	   	            .addGroup(layout.createSequentialGroup()
	   	                .addGap(20,20,20)
	   	                .addWidget(jLabel1)
	   	                .addGap(10, 10,10)
	   	                .addWidget(jTextField1)
	   	                .addGap(10,10,10)
	   	                .addWidget(playerSelectTable)
	   	                .addGap(30,30,30)
	   	                .addWidget(selectOthersLabel)
	   	                .addGap(10,10,10)
	   	                .addWidget(otherPlayerTable)
	   	                .addGap(10,10,10)
	   	                .addWidget(jButton1)
	   	                .addGap(20,20,20))
	           );
		
		
		popupWindow.setSize(410,330);
		layout.setSize(410,330);
        playerSelectTable.setSize(420,210);
        otherPlayerTable.setSize(520,110);


        popupWindow.setTheme("black-panel");
        popupWindow.add(layout);
        popupWindow.openPopupCentered();
        
        while(!dismiss) {
        	try {
        		Thread.sleep(200);
        	} catch(Exception e) {
        		
        	}
        }

        return popupWindow;
    }
    
	private void OkButtonActionPerformed() {
		
		
		selected_icon = playerSelectTable.getSelectedPlayerIcon();
		
		selection = otherPlayerTable.getSelectedPlayersMap();

		
		if (selection.size() < 1) {
			SimpleDialog dialog = new SimpleDialog();
			dialog.setMessage("You must select at least one other player.");
			dialog.setTheme("simpledialog");
			dialog.showDialog(owner);
		} else	if (jTextField1.getText().length() < 1) {
			SimpleDialog dialog = new SimpleDialog();
			dialog.setMessage("Please enter your name.");
			dialog.setTheme("simpledialog");
			dialog.showDialog(owner);
		} else {
			playerName = jTextField1.getText();
			popupWindow.closePopup();
			dismiss=true;
		}


	}


}
