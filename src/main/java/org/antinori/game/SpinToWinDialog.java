package org.antinori.game;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.antinori.game.cards.Card;
import org.antinori.game.cards.ShareTheWealthCard;

public class SpinToWinDialog extends javax.swing.JDialog {

    ArrayList<Integer> selection = new ArrayList<Integer>();
    Player player;
    GameInterface game;

    public SpinToWinDialog(java.awt.Frame parent, boolean modal, Player player, GameInterface game) {
        super(parent, modal);
        this.player = player;
        this.game = game;
        this.setTitle(player.getPlayerName() + " - SPIN TO WIN");
        LifeMain.setLocationInCenter(this, -400, -300);

    }

    public ArrayList<Integer> showDialog() {
        initComponents();
        setVisible(true);
        return selection;
    }

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        OkButton = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        selectionBox = new javax.swing.JComboBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        useSTW2CheckBox = new javax.swing.JCheckBox();
        useSTW4CheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        wagerSelection = new javax.swing.JComboBox();

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
        useSTW2CheckBox.setEnabled(false);
        useSTW4CheckBox.setEnabled(false);

        if (player.hasSPIN2Card()) {
            useSTW2CheckBox.setEnabled(true);
        }

        if (player.hasSPIN4Card()) {
            useSTW4CheckBox.setEnabled(true);
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        OkButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        OkButton.setText("OK");
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox1.setText("1");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        selectionBox.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        selectionBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        selectionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionBoxActionPerformed(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox2.setText("2");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox3.setText("3");
        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox3ItemStateChanged(evt);
            }
        });

        jCheckBox4.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox4.setText("4");
        jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox4ItemStateChanged(evt);
            }
        });

        jCheckBox5.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox5.setText("5");
        jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox5ItemStateChanged(evt);
            }
        });

        jCheckBox6.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox6.setText("6");
        jCheckBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox6ItemStateChanged(evt);
            }
        });

        jCheckBox7.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox7.setText("7");
        jCheckBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox7ItemStateChanged(evt);
            }
        });

        jCheckBox8.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox8.setText("8");
        jCheckBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox8ItemStateChanged(evt);
            }
        });

        jCheckBox9.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox9.setText("9");
        jCheckBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox9ItemStateChanged(evt);
            }
        });

        useSTW2CheckBox.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        useSTW2CheckBox.setText("Use a Spin To Win Card (2 numbers)?");
        useSTW2CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useSTW2CheckBoxItemStateChanged(evt);
            }
        });

        useSTW4CheckBox.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        useSTW4CheckBox.setText("Use a Spin To Win Card (4 numbers)?");
        useSTW4CheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                useSTW4CheckBoxItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jLabel1.setText("Select a number for SPIN TO WIN");

        CancelButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        CancelButton.setText("I don't want to spin");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jLabel2.setText("How much do you want to wager?");

        wagerSelection.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        wagerSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"5000", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "50000"}));

        jCheckBox10.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jCheckBox10.setText("10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(OkButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CancelButton))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(wagerSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(useSTW2CheckBox)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(selectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jCheckBox1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jCheckBox2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jCheckBox3)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jCheckBox4)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jCheckBox5)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jCheckBox6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jCheckBox7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jCheckBox8)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jCheckBox9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jCheckBox10))
                                                .addComponent(useSTW4CheckBox)
                                                .addComponent(jLabel2))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(selectionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(useSTW2CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(useSTW4CheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBox1)
                                .addComponent(jCheckBox2)
                                .addComponent(jCheckBox3)
                                .addComponent(jCheckBox4)
                                .addComponent(jCheckBox5)
                                .addComponent(jCheckBox6)
                                .addComponent(jCheckBox7)
                                .addComponent(jCheckBox8)
                                .addComponent(jCheckBox9)
                                .addComponent(jCheckBox10))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wagerSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(OkButton)
                                .addComponent(CancelButton))
                        .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void useSTW2CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {

        if (evt.getStateChange() == ItemEvent.SELECTED) {
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

    private void useSTW4CheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {

        if (evt.getStateChange() == ItemEvent.SELECTED) {
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

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox6ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox7ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox8ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void jCheckBox9ItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
    }

    private void selectionBoxActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int count = 0;
        if (jCheckBox1.isSelected()) {
            count++;
        }
        if (jCheckBox2.isSelected()) {
            count++;
        }
        if (jCheckBox3.isSelected()) {
            count++;
        }
        if (jCheckBox4.isSelected()) {
            count++;
        }
        if (jCheckBox5.isSelected()) {
            count++;
        }
        if (jCheckBox6.isSelected()) {
            count++;
        }
        if (jCheckBox7.isSelected()) {
            count++;
        }
        if (jCheckBox8.isSelected()) {
            count++;
        }
        if (jCheckBox9.isSelected()) {
            count++;
        }
        if (jCheckBox10.isSelected()) {
            count++;
        }

        if (useSTW2CheckBox.isSelected() && count > 2) {
            JOptionPane.showMessageDialog(null, "Only 2 numbers can be selected.");
            return;
        }
        if (useSTW4CheckBox.isSelected() && count > 4) {
            JOptionPane.showMessageDialog(null, "Only 4 numbers can be selected.");
            return;
        }

        if (useSTW2CheckBox.isSelected()) {
            ShareTheWealthCard card = player.useSPINCard(Card.Type.SPIN2);
            if (game != null) {
                game.replaceSTWCard(card);
            }
        }
        if (useSTW4CheckBox.isSelected()) {
            ShareTheWealthCard card = player.useSPINCard(Card.Type.SPIN4);
            if (game != null) {
                game.replaceSTWCard(card);
            }
        }

        if (useSTW2CheckBox.isSelected() || useSTW4CheckBox.isSelected()) {
            selection.clear();
            if (jCheckBox1.isSelected()) {
                selection.add(1);
            }
            if (jCheckBox2.isSelected()) {
                selection.add(2);
            }
            if (jCheckBox3.isSelected()) {
                selection.add(3);
            }
            if (jCheckBox4.isSelected()) {
                selection.add(4);
            }
            if (jCheckBox5.isSelected()) {
                selection.add(5);
            }
            if (jCheckBox6.isSelected()) {
                selection.add(6);
            }
            if (jCheckBox7.isSelected()) {
                selection.add(7);
            }
            if (jCheckBox8.isSelected()) {
                selection.add(8);
            }
            if (jCheckBox9.isSelected()) {
                selection.add(9);
            }
            if (jCheckBox10.isSelected()) {
                selection.add(10);
            }

        } else {
            String item = (String) selectionBox.getSelectedItem();
            selection.clear();
            selection.add(Integer.parseInt(item));
        }

        String wager = (String) wagerSelection.getSelectedItem();
        player.setSpintowin_wager(Integer.parseInt(wager));

        dispose();
    }

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selection = null;
        dispose();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {

        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                Player player = new Player("paul", Color.blue, false);
                player.addCard(new ShareTheWealthCard(Card.Type.SPIN2));
                player.addCard(new ShareTheWealthCard(Card.Type.SPIN4));

                SpinToWinDialog dialog = new SpinToWinDialog(new javax.swing.JFrame(), true, player, null);

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                ArrayList<Integer> select = dialog.showDialog();
                System.out.println(select);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton OkButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox selectionBox;
    private javax.swing.JCheckBox useSTW2CheckBox;
    private javax.swing.JCheckBox useSTW4CheckBox;
    private javax.swing.JComboBox wagerSelection;
	// End of variables declaration
}
