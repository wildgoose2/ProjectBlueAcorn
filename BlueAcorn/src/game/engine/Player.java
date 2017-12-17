package game.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends GameObject{
	private int moveSpeed = 4;
	private boolean debug = false;
	
	public Player(int xPos, int yPos, Image image) {
		super(xPos, yPos, image);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyDown(Input.KEY_W))
			yPos -= moveSpeed;
		if(input.isKeyDown(Input.KEY_S))
			yPos += moveSpeed;
		if(input.isKeyDown(Input.KEY_D))
			xPos += moveSpeed;
		if(input.isKeyDown(Input.KEY_A))
			xPos -= moveSpeed;
		if(input.isKeyPressed(Input.KEY_P))
			debug = !debug;
		
	}
	
	public boolean isDebugging() {
		return debug;
	}

}
