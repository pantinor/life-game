package org.antinori.game;

import java.awt.Font;
import java.util.ArrayList;

public class StatusPanel extends javax.swing.JPanel {

    static Font FONT = new java.awt.Font("Berlin Sans FB Demi", 0, 14);
    boolean started = false;

    public StatusPanel() {
    }

    public void setText(ArrayList<Player> players) {
        if (!started) {
            initComponents();
        }
        started = true;

        if (players.size() > 0) {
            jTextField1.setText(players.get(0).toShortString());
            jTextField1.setBackground(players.get(0).getPlayerColor());
        }
        if (players.size() > 1) {
            jTextField2.setText(players.get(1).toShortString());
            jTextField2.setBackground(players.get(1).getPlayerColor());
        } else {
            jTextField2.setVisible(false);
        }
        if (players.size() > 2) {
            jTextField3.setText(players.get(2).toShortString());
            jTextField3.setBackground(players.get(2).getPlayerColor());
        } else {
            jTextField3.setVisible(false);
        }
        if (players.size() > 3) {
            jTextField4.setText(players.get(3).toShortString());
            jTextField4.setBackground(players.get(3).getPlayerColor());
        } else {
            jTextField4.setVisible(false);
        }
        if (players.size() > 4) {
            jTextField5.setText(players.get(4).toShortString());
            jTextField5.setBackground(players.get(4).getPlayerColor());
        } else {
            jTextField5.setVisible(false);
        }
        if (players.size() > 5) {
            jTextField6.setText(players.get(5).toShortString());
            jTextField6.setBackground(players.get(5).getPlayerColor());
        } else {
            jTextField6.setVisible(false);
        }
    }

    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();

        jTextField1.setFont(FONT); // NOI18N
        jTextField2.setFont(FONT); // NOI18N
        jTextField3.setFont(FONT); // NOI18N
        jTextField4.setFont(FONT); // NOI18N
        jTextField5.setFont(FONT); // NOI18N
        jTextField6.setFont(FONT); // NOI18N

        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField1)
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE).addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }// </editor-fold>
    // Variables declaration - do not modify

    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
	// End of variables declaration
}
