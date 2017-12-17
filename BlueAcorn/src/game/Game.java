package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	public static final String gamename = "BlueAcorn";	//Name of game on window
	public static final int menu = 0;					//State ID of main menu
	public static final int play = 1;					//State ID of play state
	
	public Game(String name) {
		super(name);
		this.addState(new Menu(0));
		this.addState(new Play(1));
	}
	
	//Main method that initializes a new game with graphics settings
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(1680, 1050, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}
	}

	//Initializes the list of game states and then enters the first state
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}

}
