package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import game.engine.GameObject;
import game.engine.Interactable;

public class Rock extends GameObject implements Interactable{

	public Rock(float xPos, float yPos, Image image, ObjectID id) {
		super(xPos, yPos, image, id);
	}


	public void onInteract() {
	}

	public void onHover() {
	}


	public void update(GameContainer container, StateBasedGame game, int delta) {
	}

}
