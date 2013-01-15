package org.antinori.game.slick;


import java.util.ArrayList;
import org.antinori.game.GameInterface;
import org.antinori.game.Player;
import org.antinori.game.cards.Card;
import org.antinori.game.cards.ShareTheWealthCard;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.ComboBox;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.PopupWindow;
import de.matthiasmann.twl.SimpleDialog;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.SimpleChangableListModel;


public class SpinToWinPopup {
   
	Player player ;
	GameInterface game ;	
	ArrayList<Integer> selection = new ArrayList<Integer>();
	Widget owner;
	PopupWindow popupWindow;
	boolean dismiss = false;
    
	private DialogLayout layout;
	private Button CancelButton;
	private Button OkButton;
	private ToggleButton jCheckBox1;
	private ToggleButton jCheckBox2;
	private ToggleButton jCheckBox3;
	private ToggleButton jCheckBox4;
	private ToggleButton jCheckBox5;
	private ToggleButton jCheckBox6;
	private ToggleButton jCheckBox7;
	private ToggleButton jCheckBox8;
	private ToggleButton jCheckBox9;
	private ToggleButton jCheckBox10;
	private Label jLabel1;
	private Label jLabel2;
	private ComboBox<String> selectionBox;
	private ToggleButton useSTW2CheckBox;
	private ToggleButton useSTW4CheckBox;
	private ComboBox<String> wagerSelection;

    public SpinToWinPopup(Player player, GameInterface game) {
		this.player = player;
		this.game = game;
    }

    public PopupWindow showDialog(Widget owner) {
    	
        if(owner == null) {
            throw new NullPointerException("no owner");
        }
        
        this.owner = owner;
        
        popupWindow = new PopupWindow(owner);

		layout = new DialogLayout();

        
		OkButton = new Button();
		CancelButton = new Button();

		jCheckBox1 = new ToggleButton();
		selectionBox = new ComboBox<String>();
		jCheckBox2 = new ToggleButton();
		jCheckBox3 = new ToggleButton();
		jCheckBox4 = new ToggleButton();
		jCheckBox5 = new ToggleButton();
		jCheckBox6 = new ToggleButton();
		jCheckBox7 = new ToggleButton();
		jCheckBox8 = new ToggleButton();
		jCheckBox9 = new ToggleButton();
		jCheckBox10 = new ToggleButton();
		useSTW2CheckBox = new ToggleButton();
		useSTW4CheckBox = new ToggleButton();
		jLabel1 = new Label();
		jLabel2 = new Label();
		wagerSelection = new ComboBox<String>();

		
		jLabel1.setText("Select a number for SPIN TO WIN");
		jLabel2.setText("How much do you want to wager?");
		
		SimpleChangableListModel<String> m = new SimpleChangableListModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" });
		selectionBox.setModel(m);
		selectionBox.setComputeWidthFromModel(true);
		selectionBox.setSelected(0);
		
        m = new SimpleChangableListModel<String>(new String[] { "5000", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "50000" });
		wagerSelection.setModel(m);
		wagerSelection.setComputeWidthFromModel(true);
		wagerSelection.setSelected(0);


		jCheckBox1.setText("1");
		jCheckBox2.setText("2");
		jCheckBox3.setText("3");
		jCheckBox4.setText("4");
		jCheckBox5.setText("5");
		jCheckBox6.setText("6");
		jCheckBox7.setText("7");
		jCheckBox8.setText("8");
		jCheckBox9.setText("9");
		jCheckBox10.setText("10");
		
		jCheckBox1.setTheme("checkbox");
		jCheckBox2.setTheme("checkbox");
		jCheckBox3.setTheme("checkbox");
		jCheckBox4.setTheme("checkbox");
		jCheckBox5.setTheme("checkbox");
		jCheckBox6.setTheme("checkbox");
		jCheckBox7.setTheme("checkbox");
		jCheckBox8.setTheme("checkbox");
		jCheckBox9.setTheme("checkbox");
		jCheckBox10.setTheme("checkbox");

		useSTW2CheckBox.setText("Use a Spin To Win Card (2 numbers)?");
		useSTW4CheckBox.setText("Use a Spin To Win Card (4 numbers)?");
		useSTW2CheckBox.setTheme("checkbox");
		useSTW4CheckBox.setTheme("checkbox");
		
		useSTW2CheckBox.addCallback(new Runnable() {
			public void run() {
				useSTW2CheckBoxItemStateChanged(useSTW2CheckBox.isActive());
			}
		});
		
		useSTW4CheckBox.addCallback(new Runnable() {
			public void run() {
				useSTW4CheckBoxItemStateChanged(useSTW4CheckBox.isActive());
			}
		});
		

		CancelButton.setText("I don't want to spin");
		OkButton.setText("OK");
		
		OkButton.addCallback(new Runnable() {
			public void run() {
				OkButtonActionPerformed();
			}
		});

		CancelButton.addCallback(new Runnable() {
			public void run() {
				CancelButtonActionPerformed();
			}
		});

		layout.setHorizontalGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup()
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup().addGap(170,170,170).addWidget(OkButton).addWidget(CancelButton))
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
								.addWidget(wagerSelection)
								.addWidget(useSTW2CheckBox)
								.addGroup(layout.createSequentialGroup().addWidget(jLabel1).addWidget(selectionBox))
								.addGroup(layout.createSequentialGroup().addWidget(jCheckBox1).addWidget(jCheckBox2).addWidget(jCheckBox3).addWidget(jCheckBox4).addWidget(jCheckBox5).addWidget(jCheckBox6)
									.addWidget(jCheckBox7).addWidget(jCheckBox8).addWidget(jCheckBox9).addWidget(jCheckBox10)).addWidget(useSTW4CheckBox).addWidget(jLabel2)).addGap()))));

		layout.setVerticalGroup(layout.createParallelGroup().addGroup(
				layout.createSequentialGroup()
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup().addWidget(jLabel1).addWidget(selectionBox))
						.addGap(18, 18, 18)
						.addWidget(useSTW2CheckBox)
						.addWidget(useSTW4CheckBox)
						.addGroup(
								layout.createParallelGroup().addWidget(jCheckBox1).addWidget(jCheckBox2).addWidget(jCheckBox3).addWidget(jCheckBox4).addWidget(jCheckBox5).addWidget(jCheckBox6).addWidget(jCheckBox7).addWidget(jCheckBox8)
										.addWidget(jCheckBox9).addWidget(jCheckBox10)).addGap(30, 30, 30).addWidget(jLabel2).addWidget(wagerSelection).addGroup(layout.createParallelGroup().addWidget(OkButton).addWidget(CancelButton))));
		
		popupWindow.setSize(410,330);
		layout.setSize(410,330);

        popupWindow.setTheme("grey-panel");
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
    
    
	private void useSTW2CheckBoxItemStateChanged(boolean selected) {
		
		if (selected) {
			jCheckBox1.setEnabled(true);
			jCheckBox2.setEnabled(true);
			jCheckBox3.setEnabled(true);
			jCheckBox4.setEnabled(true);
			jCheckBox5.setEnabled(true);
			jCheckBox6.setEnabled(true);
			jCheckBox7.setEnabled(true);
			jCheckBox8.setEnabled(true);
			jCheckBox9.setEnabled(true);
			jCheckBox10.setEnabled(true);

			selectionBox.setEnabled(false);
			useSTW4CheckBox.setEnabled(false);
		} else {
			jCheckBox1.setEnabled(false);
			jCheckBox2.setEnabled(false);
			jCheckBox3.setEnabled(false);
			jCheckBox4.setEnabled(false);
			jCheckBox5.setEnabled(false);
			jCheckBox6.setEnabled(false);
			jCheckBox7.setEnabled(false);
			jCheckBox8.setEnabled(false);
			jCheckBox9.setEnabled(false);
			jCheckBox10.setEnabled(false);

			selectionBox.setEnabled(true);
			if (player.hasSPIN4Card()) {
				useSTW4CheckBox.setEnabled(true);
			}
		}
		
	}

	private void useSTW4CheckBoxItemStateChanged(boolean selected) {
		
		if (selected) {
			jCheckBox1.setEnabled(true);
			jCheckBox2.setEnabled(true);
			jCheckBox3.setEnabled(true);
			jCheckBox4.setEnabled(true);
			jCheckBox5.setEnabled(true);
			jCheckBox6.setEnabled(true);
			jCheckBox7.setEnabled(true);
			jCheckBox8.setEnabled(true);
			jCheckBox9.setEnabled(true);
			jCheckBox10.setEnabled(true);

			selectionBox.setEnabled(false);

			useSTW2CheckBox.setEnabled(false);
		} else {
			jCheckBox1.setEnabled(false);
			jCheckBox2.setEnabled(false);
			jCheckBox3.setEnabled(false);
			jCheckBox4.setEnabled(false);
			jCheckBox5.setEnabled(false);
			jCheckBox6.setEnabled(false);
			jCheckBox7.setEnabled(false);
			jCheckBox8.setEnabled(false);
			jCheckBox9.setEnabled(false);
			jCheckBox10.setEnabled(false);

			selectionBox.setEnabled(true);

			if (player.hasSPIN2Card()) {
				useSTW2CheckBox.setEnabled(true);
			}
		}
	}
	
	
	private void OkButtonActionPerformed() {
		
		int count = 0;
		if (jCheckBox1.isActive()) count ++;
		if (jCheckBox2.isActive()) count ++;
		if (jCheckBox3.isActive()) count ++;
		if (jCheckBox4.isActive()) count ++;
		if (jCheckBox5.isActive()) count ++;
		if (jCheckBox6.isActive()) count ++;
		if (jCheckBox7.isActive()) count ++;
		if (jCheckBox8.isActive()) count ++;
		if (jCheckBox9.isActive()) count ++;
		if (jCheckBox10.isActive()) count ++;

		if (useSTW2CheckBox.isActive() && count > 2) {
			SimpleDialog dialog = new SimpleDialog();
			dialog.setMessage("Only 2 numbers can be selected.");
			dialog.setTheme("grey-panel");
			dialog.showDialog(owner);
			return;
		}
		if (useSTW4CheckBox.isActive() && count > 4) {
			SimpleDialog dialog = new SimpleDialog();
			dialog.setTheme("grey-panel");
			dialog.setMessage("Only 4 numbers can be selected.");
			dialog.showDialog(owner);
			return;
		}
		
		if (useSTW2CheckBox.isActive()) {
			ShareTheWealthCard card = player.useSPINCard(Card.Type.SPIN2);
			if (game != null) game.replaceSTWCard(card);
		}
		if (useSTW4CheckBox.isActive()) {
			ShareTheWealthCard card = player.useSPINCard(Card.Type.SPIN4);
			if (game != null) game.replaceSTWCard(card);
		}
		
		if (useSTW2CheckBox.isActive() || useSTW4CheckBox.isActive()) {
	    	selection.clear();
			if (jCheckBox1.isActive()) selection.add(1);
			if (jCheckBox2.isActive()) selection.add(2);
			if (jCheckBox3.isActive()) selection.add(3);
			if (jCheckBox4.isActive()) selection.add(4);
			if (jCheckBox5.isActive()) selection.add(5);
			if (jCheckBox6.isActive()) selection.add(6);
			if (jCheckBox7.isActive()) selection.add(7);
			if (jCheckBox8.isActive()) selection.add(8);
			if (jCheckBox9.isActive()) selection.add(9);
			if (jCheckBox10.isActive()) selection.add(10);

		} else {
			int entry = selectionBox.getSelected();
            String item = selectionBox.getModel().getEntry(entry);
        	selection.clear();
        	selection.add(Integer.parseInt(item));
		}
		
		int entry = wagerSelection.getSelected();
        String wager = wagerSelection.getModel().getEntry(entry);
		player.setSpintowin_wager(Integer.parseInt(wager));
		
		popupWindow.closePopup();
		dismiss=true;

	}

	private void CancelButtonActionPerformed() {
		selection = null;
		popupWindow.closePopup();
		dismiss=true;
	}



}
