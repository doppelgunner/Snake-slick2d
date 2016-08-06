package game.state;


import game.Game;
import game.MenuSounds;
import game.color.Colors;
import game.gui.Box;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverState extends BasicGameState {
	private static final Color DEFAULT = Colors.lightGreen;
	
	private static String text = "Game Over";
	private int id;
	private static String textScore;
	private static int gameOverWidth;
	private static int scoreWidth;
	private static Font font;
	private static int highScoreWidth;
	private static String textHighScore;
	
	private static final int RESTART_WIDTH = 150;
	private static final int RESTART_HEIGHT = 60;
	
	private static final int MENU_WIDTH = 130;
	private static final int MENU_HEIGHT = 55;
	
	private Box restartBox;
	private Box menuBox;
	
	private FadeOutTransition out;
	private FadeInTransition in;
	
	public static void setScore(int score) {
		textScore = "Score: " + score;
		scoreWidth = font.getWidth(textScore);
	}
	
	public static void setHighScore(int highScore) {
		textHighScore = "HighScore: " + highScore;
		highScoreWidth = font.getWidth(textHighScore);
	}
	
	public GameOverState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		font = container.getGraphics().getFont();
		gameOverWidth = font.getWidth(text);
		
		restartBox = new Box(
				Game.WIDTH / 2 - RESTART_WIDTH / 2,
				Game.HEIGHT / 2 - RESTART_HEIGHT / 2 + 60,
				RESTART_WIDTH, RESTART_HEIGHT,"Play Again");
		restartBox.init(container.getGraphics());
		restartBox.setHoverForeColor(Colors.lightGreen);
		
		menuBox = new Box(
				Game.WIDTH / 2 - MENU_WIDTH / 2,
				Game.HEIGHT / 2 - MENU_HEIGHT / 2 + 140,
				MENU_WIDTH, MENU_HEIGHT,"Menu Screen");
		menuBox.init(container.getGraphics());
		menuBox.setHoverForeColor(Colors.lightGreen);
		
		out = new FadeOutTransition(Colors.lightGreen, 300);
		in = new FadeInTransition(Colors.lightGreen, 300);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawString(textHighScore,Game.WIDTH / 2 - highScoreWidth / 2, Game.HEIGHT / 2 - 120);
		g.drawString(text,Game.WIDTH / 2 - gameOverWidth / 2, Game.HEIGHT / 2 - 50);
		g.drawString(textScore, Game.WIDTH / 2 - scoreWidth / 2, Game.HEIGHT / 2 - 10);
		
		restartBox.draw(g);
		menuBox.draw(g);
		
		g.setColor(DEFAULT);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		if (restartBox.isHover(input.getMouseX(), input.getMouseY()) && 
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			MenuSounds.select.play();
			//enter play state
			Game.PLAY.init(container, game);
			game.enterState(Game.PLAY.getID());
			
		} else if (menuBox.isHover(input.getMouseX(), input.getMouseY()) &&
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			MenuSounds.select.play();
			//exit
			out.init(Game.GAME_OVER, Game.MENU);
			in.init(Game.GAME_OVER, Game.MENU);
			game.enterState(Game.MENU.getID(), out, in);
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
