package game.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import game.ObjectID;

public abstract class GameObject {
	protected int width, height;
	protected float xPos, yPos;
	protected Image image;
	protected ObjectID id;
	
	public GameObject(float xPos, float yPos, Image image, ObjectID id) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.id = id;
	}
	public GameObject(float xPos, float yPos, Image image, ObjectID id, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = image;
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	abstract public void update(GameContainer container, StateBasedGame game, int delta);
	
	public float getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public ObjectID getId() {
		return id;
	}
	public void setId(ObjectID id) {
		this.id = id;
	}
	
	
	
	
}
