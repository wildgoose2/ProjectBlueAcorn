package game.engine.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Button {
	private int x, y;
	Image state1, state2;
	
	public Button(int x, int y, SpriteSheet sheet) {
		this.x = x;
		this.y = y;
		state1 = sheet.getSprite(0, 0);
		state2 = sheet.getSprite(1,0);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		if(onButton())
			return state2;
		
		return state1;
	}
	
	public boolean onButton() {
		int xPos = Mouse.getX();
		int yPos = Display.getHeight() - Mouse.getY();
		
		if(xPos > x && yPos > y && xPos < x + state1.getWidth() && yPos < y + state2.getHeight())
			return true;
		
		return false;
	}
}
