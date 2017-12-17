package game.engine.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Button {
	private int x, y;		//Stores x and y position
	Image state1, state2;	//Stores images for on and off state
	
	public Button(int x, int y, SpriteSheet sheet) {
		this.x = x;
		this.y = y;
		
		//Loads separate images from sprite sheet
		state1 = sheet.getSprite(0, 0);
		state2 = sheet.getSprite(1,0);
	}
	
	//Checks to see if the mouse is over the button
	public boolean onButton() {
		int xPos = Mouse.getX();
		int yPos = Display.getHeight() - Mouse.getY();
		
		if(xPos > x && yPos > y && xPos < x + state1.getWidth() && yPos < y + state2.getHeight())
			return true;
		
		return false;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	//Returns image based on current state
	public Image getImage() {
		if(onButton())
			return state2;
		
		return state1;
	}
}
