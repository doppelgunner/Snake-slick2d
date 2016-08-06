package game;

import game.state.GameOverState;
import game.state.MenuState;
import game.state.PlayState;
import game.state.SplashState;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	
	public static final GameState SPLASH = new SplashState(0);
	public static final GameState MENU = new MenuState(1);
	public static final GameState PLAY = new PlayState(2);
	public static final GameState GAME_OVER = new GameOverState(3);
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int FPS = 60;

	
	public Game(String title) throws SlickException {
		super(title);
		MenuSounds.select = new Sound("sound/select.wav");
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(SPLASH);
		addState(MENU);
		addState(PLAY);
		addState(GAME_OVER);
	}

	public static void main (String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("Snake"));
		app.setDisplayMode(WIDTH,HEIGHT, false);
		app.setTargetFrameRate(FPS);
		app.setShowFPS(false);
		app.setIcons(new String[] {"resources/icon/icon-64.png", "resources/icon/icon-32.png"});
		app.start();
	}

}
