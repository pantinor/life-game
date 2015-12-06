package org.antinori.game.slick;

import org.antinori.game.Player;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.ComboBox;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.PopupWindow;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.SimpleChangableListModel;

public class OptionPopup {

    Widget owner;
    PopupWindow popupWindow;
    boolean dismiss = false;

    private DialogLayout layout;
    private Button OkButton;
    private Button CancelButton;
    private Label jLabel1;
    private ComboBox<String> selectionBox;
    String[] options;
    String text;
    int selection = 0;
    boolean showCancelButton = false;

    public OptionPopup(String[] options, String text, boolean showCancelButton) {
        this.options = options;
        this.text = text;
        this.showCancelButton = showCancelButton;
    }

    public OptionPopup(Player[] players, String text, boolean showCancelButton) {
        this.options = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            this.options[i] = players[i].getPlayerName();
        }
        this.text = text;
        this.showCancelButton = showCancelButton;
    }

    public PopupWindow showDialog(Widget owner) {

        if (owner == null) {
            throw new NullPointerException("no owner");
        }

        this.owner = owner;

        popupWindow = new PopupWindow(owner);

        layout = new DialogLayout();
        OkButton = new Button();
        CancelButton = new Button();

        selectionBox = new ComboBox<String>();
        jLabel1 = new Label();
        jLabel1.setText(this.text);

        SimpleChangableListModel<String> m = new SimpleChangableListModel<String>(this.options);
        selectionBox.setModel(m);
        selectionBox.setComputeWidthFromModel(true);
        selectionBox.setSelected(0);

        OkButton.setText("OK");
        OkButton.addCallback(new Runnable() {
            public void run() {
                OkButtonActionPerformed();
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addCallback(new Runnable() {
            public void run() {
                CancelButtonActionPerformed();
            }
        });

        if (showCancelButton) {
            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup()
                                    .addWidget(jLabel1)
                                    .addWidget(selectionBox)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(75, 75, 75)
                                            .addWidget(OkButton)
                                            .addGap()
                                            .addWidget(CancelButton)
                                            .addGap()))
                            .addGap(10, 10, 10))
            );
        } else {
            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup()
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(200, 200, 200)
                                            .addWidget(OkButton))
                                    .addWidget(jLabel1)
                                    .addWidget(selectionBox))
                            .addGap(10, 10, 10))
            );
        }

        if (showCancelButton) {

            layout.setVerticalGroup(
                    layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addWidget(jLabel1)
                            .addGap(10, 10, 10)
                            .addWidget(selectionBox)
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup()
                                    .addWidget(OkButton)
                                    .addWidget(CancelButton))
                            .addGap(10, 10, 10))
            );

        } else {
            layout.setVerticalGroup(
                    layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addWidget(jLabel1)
                            .addGap(10, 10, 10)
                            .addWidget(selectionBox)
                            .addGap(10, 10, 10)
                            .addWidget(OkButton)
                            .addGap(10, 10, 10))
            );

        }

        popupWindow.setSize(450, 300);
        layout.setSize(450, 300);

        popupWindow.setTheme("black-panel");
        popupWindow.add(layout);
        popupWindow.openPopupCentered();

        while (!dismiss) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {

            }
        }

        return popupWindow;
    }

    private void OkButtonActionPerformed() {

        selection = selectionBox.getSelected();

        popupWindow.closePopup();
        dismiss = true;

    }

    private void CancelButtonActionPerformed() {

        selection = -1;

        popupWindow.closePopup();
        dismiss = true;

    }

}
