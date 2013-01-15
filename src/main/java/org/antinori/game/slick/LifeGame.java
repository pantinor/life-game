package org.antinori.game.slick;

import java.net.URL;

import org.antinori.game.twl.TWLStateBasedGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class LifeGame extends TWLStateBasedGame {
	
	public static final int MENU_STATE = 0;
	public static final int GAME_STATE = 1;
	public static final int END_GAME_STATE = 2;
	public static final int SPIN_TO_WIN_STATE = 3;

	
	public LifeGame(String title) {
		super(title);
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
		LifeWorld game = new LifeWorld(GAME_STATE, container);
		EndGameWorld endgame = new EndGameWorld(END_GAME_STATE);

		addState(game);
		addState(endgame);

	}
	
	@Override
	protected URL getThemeURL() {
		return ResourceLoader.getResource("chutzpah.xml");
	}


	public static void main(String[] argv) {
		try {
			
			//ME.debugEnabled = true;
			//ME.keyFullScreen = Input.KEY_ESCAPE;
						
			AppGameContainer container = new AppGameContainer(new LifeGame("Life"), 640, 480, false);
			container.setDisplayMode(container.getScreenWidth(), container.getScreenHeight(), false);
			container.setShowFPS(false);
			container.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
