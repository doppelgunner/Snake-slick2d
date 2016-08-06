package game.state;

import game.Game;
import game.transition.Transition;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashState extends BasicGameState {

	private final int id;
	private Image splashImage;
	private static final int DELAY = 3000;
	private int counter = 0;
	
	public SplashState(final int id) {
		this.id = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		try {
			this.splashImage = new Image("resources/splash/doppelgunner.png");
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			System.err.println("Error loading image: " + e);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		counter+=delta;
		System.out.println(counter);
		if (counter >= DELAY) {
			
			Game.MENU.init(container, game);
			Transition.out.init(Game.SPLASH, Game.MENU);
			Transition.in.init(Game.SPLASH, Game.MENU);
			
			game.enterState(Game.MENU.getID(), Transition.out, Transition.in);
		}
		
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		if (splashImage != null) {
			this.splashImage.draw(0,0,Game.WIDTH,Game.HEIGHT + 5);
		}
	}

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
}
