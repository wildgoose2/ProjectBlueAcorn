package game;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.engine.Player;
import game.engine.Map;

public class Play extends BasicGameState {

	private Player player;	//Variable storing the player
	private Map map;		//Variable storing the current map
	
	public Play(int gamestate) {
		
	}
	
	//Initialization method that initializes the player and the map
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		player = new Player(0,0,new Image("res/stick.png"));
		map = new Map(player, 16, 32);
	}
	
	//Render method that renders the map, the player, and debug information
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		map.render(container, game, g);
		
		g.drawImage(player.getImage(), container.getWidth()/2 - player.getWidth()/2, container.getHeight()/2 - player.getHeight()/2);
		
		if(player.isDebugging()) {
			g.drawString("X: " + player.getxPos() + " Y: " + player.getyPos(), 50, 50);
			g.drawString("Chunk X: " + player.getxPos()/512 + " Chunk Y: " + player.getyPos()/512, 200, 100);
		}
	}
	
	//Update method that calls the update methods in the player and map objects
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {		
		player.update(container, game, delta);
		map.update(container, game, delta);

	}
	
	//Return state ID
	public int getID() {
		return 1;
	}

}
