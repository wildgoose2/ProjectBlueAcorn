package game;

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
	
	private Button play; //Play Button
	
	public Menu(int gamestate) {
		
	}
	
	//Initialization method that loads the play button spritesheet and then initializes the button
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		SpriteSheet sheet = new SpriteSheet("res/button_sheet.png", 200, 100);
		play = new Button(container.getWidth()/2 - 100, container.getHeight()/2 - 50, sheet);
	}
	
	//Render method that draws all menu items
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setBackground(Color.green);
		
		g.drawImage(play.getImage(), play.getX(), play.getY());
		
		Image title = new Image("res/title1.png");
		g.drawImage(title, container.getWidth()/2 - title.getWidth()/2, container.getHeight()/4 - title.getHeight()/2); 

	}
	
	//Update method that checks to see if the player is pressing a button
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isMouseButtonDown(0) && play.onButton())
			game.enterState(1);
	}
	
	//Return state ID
	public int getID() {
		return 0;
	}

}
