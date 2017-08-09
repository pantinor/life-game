/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antinori.life.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import static org.antinori.life.gdx.Life.TILE_DIM;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import static org.antinori.life.gdx.Life.CLASSPTH_RSLVR;
import static org.antinori.life.gdx.Life.TILE_DIM;
import static org.antinori.life.gdx.Life.VIEWPORT_DIM_HEIGHT;
import static org.antinori.life.gdx.Life.VIEWPORT_DIM_WIDTH;

public class GameScreen implements BaseScreen {

    private float time = 0;

    private final Stage stage, mapStage;
    private final Batch batch, mapBatch;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final Viewport mapViewport;
    private final Viewport viewport = new ScreenViewport();

    public int mapPixelHeight;
    private final Vector3 newMapPixelCoords = new Vector3();
    private final Vector2 currentMousePos = new Vector2();

    public final LifeMap lifeMap;
    public final TiledMap tmxMap;

    public final NewGame game;
    private final Life main;
    InputMultiplexer input;

    public GameScreen(Life main) {

        this.main = main;

        stage = new Stage(viewport);
        batch = new SpriteBatch();

        TmxMapLoader loader = new TmxMapLoader(CLASSPTH_RSLVR);
        tmxMap = loader.load("assets/data/bigger-map.tmx");
        lifeMap = new LifeMap();

        drawTextTiles();

        camera = new OrthographicCamera(VIEWPORT_DIM_WIDTH, VIEWPORT_DIM_HEIGHT);

        mapViewport = new ScreenViewport(camera);

        renderer = new OrthogonalTiledMapRenderer(tmxMap, 1f);
        mapBatch = renderer.getBatch();
        mapStage = new Stage(mapViewport, mapBatch);

        mapPixelHeight = TILE_DIM * 15;
        setMapPixelCoords(14, 9);

        game = new NewGame(this);

        input = new InputMultiplexer(this, stage);

    }

    public Stage getStage() {
        return this.stage;
    }

    public Stage getMapStage() {
        return this.mapStage;
    }

    private void drawTextTiles() {
        //draw tile text on textures in map layer
        Batch b = new SpriteBatch();
        OrthographicCamera c = new OrthographicCamera(96, 96);
        c.setToOrtho(true, 96, 96);
        c.update();
        b.setProjectionMatrix(c.combined);
        Label lab = new Label("", Life.skin, "small-font");
        lab.setBounds(10, 10, 84, 84);
        lab.setWrap(true);

        TiledMapTileLayer layer = (TiledMapTileLayer) tmxMap.getLayers().get("text");
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 30; x++) {
                Location l = lifeMap.getNodes()[x][y];
                if (l.getTile() != null) {

                    lab.setText(l.getTile().getDescription());
                    lab.layout();

                    FrameBuffer fbo = new FrameBuffer(Format.RGBA8888, 96, 96, false);

                    fbo.begin();
                    b.begin();
                    lab.draw(b, 1f);
                    b.end();
                    fbo.end();
                    Texture t = fbo.getColorBufferTexture();
                    TextureRegion tr = new TextureRegion(t);

                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    TiledMapTile tmt = new StaticTiledMapTile(tr);
                    tmt.setId(y * 15 + x);
                    cell.setTile(tmt);
                    layer.setCell(x, 15 - 1 - y, cell);
                }
            }
        }
    }

    @Override
    public final void setMapPixelCoords(float x, float y) {
        newMapPixelCoords.x = x * TILE_DIM;
        newMapPixelCoords.y = mapPixelHeight - y * TILE_DIM;
    }

    private void setCurrentMapCoords(Vector3 v) {
        Vector3 tmp = camera.unproject(new Vector3(TILE_DIM * 7, TILE_DIM * 4, 0), TILE_DIM, TILE_DIM, VIEWPORT_DIM_WIDTH, VIEWPORT_DIM_HEIGHT);
        v.set(Math.round(tmp.x / TILE_DIM) - 1, ((mapPixelHeight - Math.round(tmp.y) - TILE_DIM) / TILE_DIM) - 0, 0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(input);

        game.initPlayers();

        Iterator<Player> players = game.getPlayers();
        while (players.hasNext()) {
            Player p = players.next();
            mapStage.addActor(p.getActor());
        }
    }

    @Override
    public void resize(int width, int height) {
        mapViewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        time += delta;

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(
                newMapPixelCoords.x + 0 * TILE_DIM + 48,
                newMapPixelCoords.y - 1 * TILE_DIM + 48,
                0);

        camera.update();

        renderer.setView(camera);

        renderer.setView(camera.combined,
                camera.position.x - TILE_DIM * 7,
                camera.position.y - TILE_DIM * 4,
                VIEWPORT_DIM_WIDTH, VIEWPORT_DIM_HEIGHT);

        renderer.render();

        mapBatch.setProjectionMatrix(camera.combined);
        mapBatch.begin();
        mapBatch.end();

        mapStage.act();
        mapStage.draw();

        batch.begin();

        //grid.draw(batch);
        //TextureRegion tr = (TextureRegion) ActorType.PLAYER1.getAnimation(Actor.NORTH).getKeyFrame(time, true);
        //batch.draw(tr, 7 * TILE_DIM + 0, 4 * TILE_DIM + 0);
        //Vector3 v = new Vector3();
        //setCurrentMapCoords(v);
        //Life.font.draw(batch, String.format("%s, %s\n", v.x, v.y), 20, 20);
        Life.hud.render(batch, game);

        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public boolean keyUp(int keycode) {

        Vector3 v = new Vector3();
        setCurrentMapCoords(v);

        if (keycode == Input.Keys.UP) {
            if (!preMove(v, Actor.NORTH)) {
                return false;
            }
            newMapPixelCoords.y = newMapPixelCoords.y + TILE_DIM;
            v.y -= 1;
        } else if (keycode == Input.Keys.DOWN) {
            if (!preMove(v, Actor.SOUTH)) {
                return false;
            }
            newMapPixelCoords.y = newMapPixelCoords.y - TILE_DIM;
            v.y += 1;
        } else if (keycode == Input.Keys.RIGHT) {
            if (!preMove(v, Actor.EAST)) {
                return false;
            }
            newMapPixelCoords.x = newMapPixelCoords.x + TILE_DIM;
            v.x += 1;
        } else if (keycode == Input.Keys.LEFT) {
            if (!preMove(v, Actor.WEST)) {
                return false;
            }
            newMapPixelCoords.x = newMapPixelCoords.x - TILE_DIM;
            v.x -= 1;
        } else if (keycode == Input.Keys.X) {
                CountDownLatch latch = new CountDownLatch(1);
                SpinToWinDialog dialog = new SpinToWinDialog(game.getPlayers().next(), new ArrayList<Integer>(), game, this, latch);
                dialog.show(getStage());
            
        } else if (keycode == Input.Keys.SPACE) {

            Gdx.input.setInputProcessor(stage);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    game.play();
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            Gdx.input.setInputProcessor(input);
                        }
                    });
                }
            }).start();
        }

        return false;
    }

    private boolean preMove(Vector3 current, int dir) {

        int nx = (int) current.x;
        int ny = (int) current.y;

        if (dir == Actor.NORTH) {
            ny = (int) current.y - 1;
        }
        if (dir == Actor.SOUTH) {
            ny = (int) current.y + 1;
        }
        if (dir == Actor.WEST) {
            nx = (int) current.x - 1;
        }
        if (dir == Actor.EAST) {
            nx = (int) current.x + 1;
        }

        if (nx > 30 - 1 || nx < 0 || ny > 15 - 1 || ny < 0) {
            return false;
        }

        return true;
    }
    
    @Override
    public void move(final org.antinori.life.gdx.Actor actor, int x, int y) {
        final CountDownLatch latch = new CountDownLatch(1);
        actor.addAction(sequence(moveTo(x * TILE_DIM, (TILE_DIM * 15) - (y + 1) * TILE_DIM, 0.5f),
                run(new Runnable() {
                    @Override
                    public void run() {
                        actor.setDirection(-1);
                        latch.countDown();
                    }
                })));
        try {
            latch.await();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
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

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    private static Pixmap createGrid() {

        int imgWidth = TILE_DIM * 13;
        int imgHeight = TILE_DIM * 9;

        Pixmap pix = new Pixmap(imgWidth, imgHeight, Pixmap.Format.RGBA8888);

        pix.setColor(255f, 255f, 0f, 1f);

        for (int x = 0; x < imgWidth; x += TILE_DIM) {
            pix.drawLine(x, 0, x, imgHeight);
        }
        for (int y = 0; y < imgHeight; y += TILE_DIM) {
            pix.drawLine(0, y, imgWidth, y);
        }

        return pix;
    }

}
