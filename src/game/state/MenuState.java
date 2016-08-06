package game.state;

import game.Game;
import game.MenuSounds;
import game.color.Colors;
import game.gui.Box;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
	
	private static final Color DEFAULT = Colors.normalWhite;
	private final int id;
	
	private static final int PLAY_WIDTH = 150;
	private static final int PLAY_HEIGHT = 60;
	
	private static final int EXIT_WIDTH = 130;
	private static final int EXIT_HEIGHT = 55;
	
	private Box playBox;
	private Box exitBox;
	
	public MenuState(int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		//set background
		container.getGraphics().setBackground(Colors.darkGreen);
		
		playBox = new Box(
				Game.WIDTH / 2 - PLAY_WIDTH / 2,
				Game.HEIGHT / 2 - PLAY_HEIGHT / 2 - 40,
				PLAY_WIDTH, PLAY_HEIGHT,"Play Snake");
		playBox.init(container.getGraphics());
		playBox.setHoverForeColor(Colors.lightGreen);
		
		exitBox = new Box(
				Game.WIDTH / 2 - EXIT_WIDTH / 2,
				Game.HEIGHT / 2 - EXIT_HEIGHT / 2 + 40,
				EXIT_WIDTH, EXIT_HEIGHT,"Exit Game");
		exitBox.init(container.getGraphics());
		exitBox.setHoverForeColor(Colors.lightGreen);
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		playBox.draw(g);
		exitBox.draw(g);
		g.setColor(DEFAULT);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		
		if (playBox.isHover(input.getMouseX(), input.getMouseY()) && 
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			MenuSounds.select.play();
			//enter play state
			Game.PLAY.init(container, game);
			game.enterState(Game.PLAY.getID());
			
		} else if (exitBox.isHover(input.getMouseX(), input.getMouseY()) &&
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			
			MenuSounds.select.play();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//exit
			container.exit();
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
