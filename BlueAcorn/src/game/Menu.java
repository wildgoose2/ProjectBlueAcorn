package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.engine.gui.Button;

public class Menu extends BasicGameState {
	
	private Button play;
	public Menu(int gamestate) {
		
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		SpriteSheet sheet = new SpriteSheet("res/button_sheet.png", 200, 100);
		play = new Button(container.getWidth()/2 - 100, container.getHeight()/2 - 50, sheet);
	}
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setBackground(Color.green);
		g.drawImage(play.getImage(), play.getX(), play.getY());
		
		Image title = new Image("res/title1.png");
		g.drawImage(title, container.getWidth()/2 - title.getWidth()/2, container.getHeight()/4 - title.getHeight()/2); 

	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isMouseButtonDown(0) && play.onButton())
			game.enterState(1);
	}
	
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
