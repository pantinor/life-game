package org.antinori.life.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class PlayerSelectionScreen implements Screen {

    private final PlayerSelection[] selections = new PlayerSelection[6];

    private TextButton start;
    private final Table internalTable;
    private final Stage stage;
    private final Life main;
    private final GameScreen gameScreen;

    public PlayerSelectionScreen(Life main, GameScreen gameScreen) {

        this.stage = new Stage();
        this.main = main;
        this.gameScreen = gameScreen;

        start = new TextButton("START", Life.skin);
        this.start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                for (PlayerSelection ps : selections) {
                    if (ps.enabled.isChecked()) {
                        ActorType type = (ActorType) ps.icon.getUserObject();
                        Actor a = new Actor(type);
                        gameScreen.game.addPlayer((String) ps.name.getSelected(), a, ps.auto.isChecked());
                    }
                }
                main.setScreen(gameScreen);
            }
        });

        this.start.setBounds(900, 100, 65, 25);

        this.internalTable = new Table(Life.skin);
        this.internalTable.defaults().pad(5);

        boolean m = true;
        for (int i = 0; i < 6; i++) {
            selections[i] = new PlayerSelection(ActorType.values()[i], m);
            internalTable.add(selections[i]);
            if (i==1||i==3||i==5) internalTable.row();
            m = false;
        }

        this.internalTable.setBounds(400, 200, 600, 600);

        stage.addActor(internalTable);
        stage.addActor(start);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    private class PlayerSelection extends Group {

        final String[] names = {"Brandon", "Sam", "Tom", "Jessika", "Paul", "Fred", "Rosa", "Ada"};
        final SelectBox name;
        final SelectBox icons;
        final Image icon;
        final CheckBox enabled;
        final CheckBox auto;


        final Table table = new Table(Life.skin);

        public PlayerSelection(ActorType type, boolean main) {
            name = new SelectBox(Life.skin);
            name.setItems(names);
            name.setBounds(getX(), getY(), 75, 120);
            name.setSelectedIndex(type.ordinal());

            icons = new SelectBox(Life.skin);
            icons.setItems(ActorType.values());
            icons.setBounds(getX(), getY(), 75, 120);
            icons.setSelectedIndex(type.ordinal());

            icon = new Image((TextureRegion) type.getAnimation(Actor.SOUTH).getKeyFrame(0, true));
            icon.setBounds(getX(), getY(), 96, 96);
            icon.setUserObject(type);

            icons.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                    ActorType type = (ActorType) icons.getSelected();
                    icon.setUserObject(type);
                    icon.setDrawable(new TextureRegionDrawable((TextureRegion) type.getAnimation(Actor.SOUTH).getKeyFrame(0, true)));
                }
            });

            enabled = new CheckBox("Enabled", Life.skin);
            enabled.setChecked(true);
            if (main) {
                enabled.setVisible(false);
            }
            
            auto = new CheckBox("Auto-Play", Life.skin);
            auto.setChecked(true);
            if (main) {
                auto.setVisible(false);
                auto.setChecked(false);
            }

            table.defaults().pad(5);
            table.add(name).align(Align.top);
            table.add(icons).align(Align.top);
            table.add(icon).align(Align.top);
            table.row();
            table.add(enabled).align(Align.top);
            table.add(auto).align(Align.top);

            addActor(table);

            table.setBounds(getX(), getY(), 400, 132);
            setBounds(getX(), getY(), 400, 132);
        }

    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
