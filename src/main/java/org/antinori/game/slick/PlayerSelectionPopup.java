package org.antinori.game.slick;


import java.util.ArrayList;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.EditField;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.PopupWindow;
import de.matthiasmann.twl.SimpleDialog;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;


public class PlayerSelectionPopup {
	
	ArrayList<String> selection = new ArrayList<String>();
	String playerName;
	Widget owner;
	PopupWindow popupWindow;
	boolean dismiss = false;
   
    
	private DialogLayout layout;
    private Button jButton1;
    private ToggleButton jCheckBox1;
    private ToggleButton jCheckBox2;
    private ToggleButton jCheckBox3;
    private ToggleButton jCheckBox4;
    private ToggleButton jCheckBox5;
    private Label jLabel1;
    private Label jLabel2;
    private EditField jTextField1;
    
   
    public PlayerSelectionPopup() {
    }

    public PopupWindow showDialog(Widget owner) {
    	
        if(owner == null) {
            throw new NullPointerException("no owner");
        }
        
        popupWindow = new PopupWindow(owner);

		layout = new DialogLayout();
		jButton1 = new Button();
        jLabel1 = new Label();
        jLabel2 = new Label();
        jButton1 = new Button();
        jTextField1 = new EditField();
        jCheckBox1 = new ToggleButton();
        jCheckBox2 = new ToggleButton();
        jCheckBox3 = new ToggleButton();
        jCheckBox4 = new ToggleButton();
        jCheckBox5 = new ToggleButton();


        jLabel1.setText("Enter Your Name");
        jLabel2.setText("Select the Other Players");
        jButton1.setText("Start Game");
        jTextField1.setText("Joe");
        jCheckBox1.setText("Ruby");
        jCheckBox2.setText("Ada");
        jCheckBox3.setText("Brandon");
        jCheckBox4.setText("Rosa");
        jCheckBox5.setText("Jessika");
		
		jCheckBox1.setTheme("checkbox");
		jCheckBox2.setTheme("checkbox");
		jCheckBox3.setTheme("checkbox");
		jCheckBox4.setTheme("checkbox");
		jCheckBox5.setTheme("checkbox");
				
		jButton1.addCallback(new Runnable() {
			public void run() {
				OkButtonActionPerformed();
			}
		});



        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(30,30,30)
                    .addGroup(layout.createParallelGroup()
                        .addWidget(jCheckBox5)
                        .addWidget(jTextField1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap()
                            .addWidget(jButton1))
                        .addWidget(jLabel1)
                        .addWidget(jLabel2)
                        .addWidget(jCheckBox1)
                        .addWidget(jCheckBox2)
                        .addWidget(jCheckBox3)
                        .addWidget(jCheckBox4))
                    .addGap(30,30,30))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(5,5,5)
                    .addWidget(jLabel1)
                    .addGap(5,5,5)
                    .addWidget(jTextField1)
                    .addGap(15,15,15)
                    .addWidget(jLabel2)
                    .addGap(5,5,5)
                    .addWidget(jCheckBox1)
                    .addGap(5,5,5)
                    .addWidget(jCheckBox2)
                    .addGap(5,5,5)
                    .addWidget(jCheckBox3)
                    .addGap(5,5,5)
                    .addWidget(jCheckBox4)
                    .addGap(5,5,5)
                    .addWidget(jCheckBox5)
                    .addGap(15,15,15)
                    .addWidget(jButton1)
                    .addGap(5,5,5))
            );
		
		
		popupWindow.setSize(410,330);
		layout.setSize(410,330);

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
		
		if (jCheckBox1.isActive()) selection.add(jCheckBox1.getText());
		if (jCheckBox2.isActive()) selection.add(jCheckBox2.getText());
		if (jCheckBox3.isActive()) selection.add(jCheckBox3.getText());
		if (jCheckBox4.isActive()) selection.add(jCheckBox4.getText());
		if (jCheckBox5.isActive()) selection.add(jCheckBox5.getText());
		
		if (selection.size() < 1) {
			SimpleDialog dialog = new SimpleDialog();
			dialog.setMessage("You must select at least one other player.");
			dialog.setTheme("black-panel");
			dialog.showDialog(owner);
		} else	if (jTextField1.getText().length() < 1) {
			SimpleDialog dialog = new SimpleDialog();
			dialog.setMessage("Please enter your name.");
			dialog.setTheme("black-panel");
			dialog.showDialog(owner);
		} else {
			playerName = jTextField1.getText();
			popupWindow.closePopup();
			dismiss=true;
		}


	}


}
