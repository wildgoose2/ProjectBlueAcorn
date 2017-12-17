package game.engine;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game.ObjectID;

public class Player extends GameObject{
	private float moveSpeed = 4;
	private boolean debug = false;
	
	public Player(float xPos, float yPos, Image image) {
		super(xPos, yPos, image, ObjectID.PLAYER);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		
		moveSpeed += Mouse.getDWheel()/100;
		
		double tempSpeed;
		
		if((input.isKeyDown(Input.KEY_W) && ((input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)))) || (input.isKeyDown(Input.KEY_S) && ((input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D))))) {
			tempSpeed = moveSpeed/(Math.sqrt(2));
		}
		else{
			tempSpeed = moveSpeed;
		}
		
		if(input.isKeyDown(Input.KEY_W))
			yPos -= tempSpeed;
		if(input.isKeyDown(Input.KEY_S))
			yPos += tempSpeed;
		if(input.isKeyDown(Input.KEY_D))
			xPos += tempSpeed;
		if(input.isKeyDown(Input.KEY_A))
			xPos -= tempSpeed;
		if(input.isKeyPressed(Input.KEY_P))
			debug = !debug;
		
	}
	
	public boolean isDebugging() {
		return debug;
	}

}
