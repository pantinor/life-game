package org.antinori.game;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingUtilities;


public class StatusProgressPanel extends javax.swing.JPanel {
	static Font FONT = new java.awt.Font("Berlin Sans FB Demi", 0, 14);
	boolean started = false;
	ArrayList<Player> players = null;

    public StatusProgressPanel() {
    }
    
	public void setText(ArrayList<Player> p) {
		
		this.players = p;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (!started) initComponents();
				started = true;

				if (players.size() > 0) {
					jProgressBar1.setString(players.get(0).toShortString());
					jProgressBar1.setForeground(players.get(0).getPlayerColor());
					jProgressBar1.setValue(players.get(0).getMoney());
				}
				
				if (players.size() > 1) {
					jProgressBar2.setString(players.get(1).toShortString());
					jProgressBar2.setForeground(players.get(1).getPlayerColor());
					jProgressBar2.setValue(players.get(1).getMoney());
				} else
					jProgressBar2.setVisible(false);
				
				if (players.size() > 2) {
					jProgressBar3.setString(players.get(2).toShortString());
					jProgressBar3.setForeground(players.get(2).getPlayerColor());
					jProgressBar3.setValue(players.get(2).getMoney());
				} else
					jProgressBar3.setVisible(false);
				
				if (players.size() > 3) {
					jProgressBar4.setString(players.get(3).toShortString());
					jProgressBar4.setForeground(players.get(3).getPlayerColor());
					jProgressBar4.setValue(players.get(3).getMoney());
				} else
					jProgressBar4.setVisible(false);
				
				if (players.size() > 4) {
					jProgressBar5.setString(players.get(4).toShortString());
					jProgressBar5.setForeground(players.get(4).getPlayerColor());
					jProgressBar5.setValue(players.get(4).getMoney());
				} else
					jProgressBar5.setVisible(false);
				
				if (players.size() > 5) {
					jProgressBar6.setString(players.get(5).toShortString());
					jProgressBar6.setForeground(players.get(5).getPlayerColor());
					jProgressBar6.setValue(players.get(5).getMoney());
				} else
					jProgressBar6.setVisible(false);
			}
		});

	}

    private void initComponents() {
    	
    	setBackground(Tile.PANEL_BLUE);

        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jProgressBar6 = new javax.swing.JProgressBar();

        jProgressBar1.setMaximum(1000000);
        jProgressBar2.setMaximum(1000000);
        jProgressBar3.setMaximum(1000000);
        jProgressBar4.setMaximum(1000000);
        jProgressBar5.setMaximum(1000000);
        jProgressBar6.setMaximum(1000000);
        
        jProgressBar1.setFont(FONT); // NOI18N
        jProgressBar2.setFont(FONT); // NOI18N
        jProgressBar3.setFont(FONT); // NOI18N
        jProgressBar4.setFont(FONT); // NOI18N
        jProgressBar5.setFont(FONT); // NOI18N
        jProgressBar6.setFont(FONT); // NOI18N
		
        jProgressBar1.setString("");
        jProgressBar2.setString("");
        jProgressBar3.setString("");
        jProgressBar4.setString("");
        jProgressBar5.setString("");
        jProgressBar6.setString("");
        
        jProgressBar1.setStringPainted(true);
        jProgressBar2.setStringPainted(true);
        jProgressBar3.setStringPainted(true);
        jProgressBar4.setStringPainted(true);
        jProgressBar5.setStringPainted(true);
        jProgressBar6.setStringPainted(true);



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jProgressBar6, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>
    // Variables declaration - do not modify
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    // End of variables declaration
}
