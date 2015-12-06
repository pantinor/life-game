package org.antinori.game.slick;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.PopupWindow;
import de.matthiasmann.twl.Widget;

public class YesNoPopup {

    Widget owner;
    PopupWindow popupWindow;
    boolean dismiss = false;

    private DialogLayout layout;
    private Button YesButton;
    private Button NoButton;
    private Label jLabel1;
    String[] options = {"No", "Yes"};
    String text;
    boolean selection = false;

    public YesNoPopup(String text) {
        this.text = text;
    }

    public PopupWindow showDialog(Widget owner) {

        if (owner == null) {
            throw new NullPointerException("no owner");
        }

        this.owner = owner;

        popupWindow = new PopupWindow(owner);

        layout = new DialogLayout();
        YesButton = new Button();
        NoButton = new Button();

        jLabel1 = new Label();
        jLabel1.setText(this.text);

        YesButton.setText("Yes");
        YesButton.addCallback(new Runnable() {
            public void run() {
                YesButtonActionPerformed();
            }
        });

        NoButton.setText("No");
        NoButton.addCallback(new Runnable() {
            public void run() {
                NoButtonActionPerformed();
            }
        });

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup()
                                .addWidget(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addWidget(YesButton)
                                        .addGap()
                                        .addWidget(NoButton)
                                        .addGap()))
                        .addGap(10, 10, 10))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addWidget(jLabel1)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup()
                                .addWidget(YesButton)
                                .addWidget(NoButton))
                        .addGap(10, 10, 10))
        );

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

    private void YesButtonActionPerformed() {

        selection = true;

        popupWindow.closePopup();
        dismiss = true;

    }

    private void NoButtonActionPerformed() {

        selection = false;

        popupWindow.closePopup();
        dismiss = true;

    }

}
