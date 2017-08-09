package org.antinori.life.gdx;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import org.antinori.game.cards.Card;
import org.antinori.game.cards.ShareTheWealthCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SpinToWinDialog extends Window {

    boolean cancelHide;
    com.badlogic.gdx.scenes.scene2d.Actor previousKeyboardFocus, previousScrollFocus;
    FocusListener focusListener;
    BaseScreen screen;
    NewGame game;
    CountDownLatch latch;

    public static int WIDTH = 410;
    public static int HEIGHT = 330;

    Table internalTable;

    private final Player player;
    private final List<Integer> selection;

    private final TextButton CancelButton;
    private final TextButton OkButton;
    private final CheckBox jCheckBox1;
    private final CheckBox jCheckBox2;
    private final CheckBox jCheckBox3;
    private final CheckBox jCheckBox4;
    private final CheckBox jCheckBox5;
    private final CheckBox jCheckBox6;
    private final CheckBox jCheckBox7;
    private final CheckBox jCheckBox8;
    private final CheckBox jCheckBox9;
    private final CheckBox jCheckBox10;
    private final Label jLabel1;
    private final Label jLabel2;
    private final SelectBox selectionBox;
    private final CheckBox useSTW2CheckBox;
    private final CheckBox useSTW4CheckBox;
    private final SelectBox wagerSelection;

    public SpinToWinDialog(Player player, List<Integer> selection, NewGame game, BaseScreen screen, CountDownLatch latch) {
        super("", Life.skin.get("dialog", WindowStyle.class));
        setSkin(Life.skin);
        this.screen = screen;
        this.player = player;
        this.game = game;
        this.selection = selection;
        this.latch = latch;

        setModal(true);

        defaults().space(10).pad(5);
        add(internalTable = new Table(Life.skin)).expand().fill();
        row();

        internalTable.defaults().pad(1);

        focusListener = new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusListener.FocusEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor, boolean focused) {
                if (!focused) {
                    focusChanged(event);
                }
            }

            @Override
            public void scrollFocusChanged(FocusListener.FocusEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor, boolean focused) {
                if (!focused) {
                    focusChanged(event);
                }
            }

            private void focusChanged(FocusListener.FocusEvent event) {
                Stage stage = getStage();
                if (isModal() && stage != null && stage.getRoot().getChildren().size > 0 && stage.getRoot().getChildren().peek() == SpinToWinDialog.this) {
                    com.badlogic.gdx.scenes.scene2d.Actor newFocusedActor = event.getRelatedActor();
                    if (newFocusedActor != null && !newFocusedActor.isDescendantOf(SpinToWinDialog.this) && !(newFocusedActor.equals(previousKeyboardFocus) || newFocusedActor.equals(previousScrollFocus))) {
                        event.cancel();
                    }
                }
            }
        };

        OkButton = new TextButton("OK", Life.skin);
        CancelButton = new TextButton("Don't Spin", Life.skin);
        jCheckBox1 = new CheckBox("1", Life.skin);
        jCheckBox2 = new CheckBox("2", Life.skin);
        jCheckBox3 = new CheckBox("3", Life.skin);
        jCheckBox4 = new CheckBox("4", Life.skin);
        jCheckBox5 = new CheckBox("5", Life.skin);
        jCheckBox6 = new CheckBox("6", Life.skin);
        jCheckBox7 = new CheckBox("7", Life.skin);
        jCheckBox8 = new CheckBox("8", Life.skin);
        jCheckBox9 = new CheckBox("9", Life.skin);
        jCheckBox10 = new CheckBox("10", Life.skin);
        useSTW2CheckBox = new CheckBox("1", Life.skin);
        useSTW4CheckBox = new CheckBox("1", Life.skin);
        jLabel1 = new Label("Select a number for SPIN TO WIN", Life.skin);
        jLabel2 = new Label("How much do you want to wager?", Life.skin);

        wagerSelection = new SelectBox(Life.skin);
        selectionBox = new SelectBox(Life.skin);
        selectionBox.setItems(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        wagerSelection.setItems(new String[]{"5000", "10000", "15000", "20000", "25000", "30000", "35000", "40000", "45000", "50000"});

        useSTW2CheckBox.setText("Use a Spin To Win Card (2 numbers)?");
        useSTW4CheckBox.setText("Use a Spin To Win Card (4 numbers)?");

        if (!player.hasSPIN2Card()) {
            useSTW2CheckBox.setChecked(false);
        }
        if (!player.hasSPIN4Card()) {
            useSTW4CheckBox.setChecked(false);
        }

        this.OkButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                OkButtonActionPerformed();
            }
        });

        this.CancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                selection.add(-1);
                hide();
            }
        });

        this.useSTW2CheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                useSTWCheckBoxItemStateChanged(useSTW2CheckBox.isChecked(), 2);
            }
        });

        this.useSTW4CheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                useSTWCheckBoxItemStateChanged(useSTW4CheckBox.isChecked(), 4);
            }
        });

        internalTable.add(jLabel1);
        internalTable.add(selectionBox);
        internalTable.row();
        internalTable.add(jLabel2);
        internalTable.add(wagerSelection);
        internalTable.row();

        if (player.hasSPIN2Card() || player.hasSPIN4Card()) {
            internalTable.add(useSTW2CheckBox);
            internalTable.row();
            internalTable.add(useSTW4CheckBox);
            internalTable.row();
            Table numbersTable = new Table();
            numbersTable.add(jCheckBox1);
            numbersTable.add(jCheckBox2);
            numbersTable.add(jCheckBox3);
            numbersTable.add(jCheckBox4);
            numbersTable.add(jCheckBox5);
            numbersTable.add(jCheckBox6);
            numbersTable.add(jCheckBox7);
            numbersTable.add(jCheckBox8);
            numbersTable.add(jCheckBox9);
            numbersTable.add(jCheckBox10);
            internalTable.add(numbersTable);
            internalTable.row();
        }

        internalTable.add(OkButton).width(100);
        internalTable.add(CancelButton).width(100);

    }

    private void useSTWCheckBoxItemStateChanged(boolean selected, int type) {

        if (selected) {
            selectionBox.setVisible(true);
            if (type == 2) {
                useSTW4CheckBox.setChecked(false);
            } else {
                useSTW2CheckBox.setChecked(false);
            }
        } else {
            selectionBox.setVisible(false);
        }
    }

    private void OkButtonActionPerformed() {

        int count = 0;
        if (jCheckBox1.isChecked()) {
            count++;
        }
        if (jCheckBox2.isChecked()) {
            count++;
        }
        if (jCheckBox3.isChecked()) {
            count++;
        }
        if (jCheckBox4.isChecked()) {
            count++;
        }
        if (jCheckBox5.isChecked()) {
            count++;
        }
        if (jCheckBox6.isChecked()) {
            count++;
        }
        if (jCheckBox7.isChecked()) {
            count++;
        }
        if (jCheckBox8.isChecked()) {
            count++;
        }
        if (jCheckBox9.isChecked()) {
            count++;
        }
        if (jCheckBox10.isChecked()) {
            count++;
        }

        if (useSTW2CheckBox.isChecked()) {
            if (count != 2) {
                Dialog dialog = new Dialog("", Life.skin, "dialog");
                dialog.text("Only 2 numbers can be selected with a Spin to Win 2 card.");
                dialog.button("OK", 0);
                dialog.show(screen.getStage());
                return;
            }
        }
        if (useSTW4CheckBox.isChecked()) {
            if (count != 4) {
                Dialog dialog = new Dialog("", Life.skin, "dialog");
                dialog.text("Only 4 numbers can be selected with a Spin to Win 4 card.");
                dialog.button("OK", 0);
                dialog.show(screen.getStage());
                return;
            }
        }

        if (useSTW2CheckBox.isChecked()) {
            ShareTheWealthCard card = player.useSPINCard(Card.Type.SPIN2);
            if (game != null) {
                game.replaceSTWCard(card);
            }
        }
        if (useSTW4CheckBox.isChecked()) {
            ShareTheWealthCard card = player.useSPINCard(Card.Type.SPIN4);
            if (game != null) {
                game.replaceSTWCard(card);
            }
        }

        if (useSTW2CheckBox.isChecked() || useSTW4CheckBox.isChecked()) {
            selection.clear();
            if (jCheckBox1.isChecked()) {
                selection.add(1);
            }
            if (jCheckBox2.isChecked()) {
                selection.add(2);
            }
            if (jCheckBox3.isChecked()) {
                selection.add(3);
            }
            if (jCheckBox4.isChecked()) {
                selection.add(4);
            }
            if (jCheckBox5.isChecked()) {
                selection.add(5);
            }
            if (jCheckBox6.isChecked()) {
                selection.add(6);
            }
            if (jCheckBox7.isChecked()) {
                selection.add(7);
            }
            if (jCheckBox8.isChecked()) {
                selection.add(8);
            }
            if (jCheckBox9.isChecked()) {
                selection.add(9);
            }
            if (jCheckBox10.isChecked()) {
                selection.add(10);
            }

        } else {
            String entry = (String) selectionBox.getSelected();
            selection.clear();
            selection.add(Integer.parseInt(entry));
        }

        String wager = (String) wagerSelection.getSelected();
        player.setSpintoWinWager(Integer.parseInt(wager));

        hide();

    }

    protected void workspace(Stage stage) {
        if (stage == null) {
            addListener(focusListener);
        } else {
            removeListener(focusListener);
        }
        super.setStage(stage);
    }

    public SpinToWinDialog show(Stage stage, Action action) {
        clearActions();
        removeCaptureListener(ignoreTouchDown);

        previousKeyboardFocus = null;
        com.badlogic.gdx.scenes.scene2d.Actor actor = stage.getKeyboardFocus();
        if (actor != null && !actor.isDescendantOf(this)) {
            previousKeyboardFocus = actor;
        }

        previousScrollFocus = null;
        actor = stage.getScrollFocus();
        if (actor != null && !actor.isDescendantOf(this)) {
            previousScrollFocus = actor;
        }

        pack();
        stage.addActor(this);
        stage.setKeyboardFocus(OkButton);
        stage.setScrollFocus(this);

        if (action != null) {
            addAction(action);
        }

        return this;
    }

    public SpinToWinDialog show(Stage stage) {
        show(stage, sequence(Actions.alpha(0), Actions.fadeIn(0.4f, Interpolation.fade)));
        setPosition(Math.round((stage.getWidth() - getWidth()) / 2), Math.round((stage.getHeight() - getHeight()) / 2));
        return this;
    }

    public void hide(Action action) {

        Stage stage = getStage();
        if (stage != null) {
            removeListener(focusListener);
            if (previousKeyboardFocus != null && previousKeyboardFocus.getStage() == null) {
                previousKeyboardFocus = null;
            }
            com.badlogic.gdx.scenes.scene2d.Actor actor = stage.getKeyboardFocus();
            if (actor == null || actor.isDescendantOf(this)) {
                stage.setKeyboardFocus(previousKeyboardFocus);
            }

            if (previousScrollFocus != null && previousScrollFocus.getStage() == null) {
                previousScrollFocus = null;
            }
            actor = stage.getScrollFocus();
            if (actor == null || actor.isDescendantOf(this)) {
                stage.setScrollFocus(previousScrollFocus);
            }
        }
        if (action != null) {
            addCaptureListener(ignoreTouchDown);
            addAction(sequence(action, Actions.removeListener(ignoreTouchDown, true), Actions.removeActor()));
        } else {
            remove();
        }

        Gdx.input.setInputProcessor(new InputMultiplexer(screen, stage));
        this.latch.countDown();

    }

    public void hide() {
        hide(sequence(fadeOut(0.4f, Interpolation.fade), Actions.removeListener(ignoreTouchDown, true), Actions.removeActor()));
    }

    protected InputListener ignoreTouchDown = new InputListener() {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            event.cancel();
            return false;
        }
    };

}
