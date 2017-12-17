package game.engine;

import org.newdawn.slick.Image;

public abstract class GameObject {
	protected int xPos, yPos, width, height;
	protected Image image;
	
	public GameObject(int xPos, int yPos, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	public GameObject(int xPos, int yPos, Image image, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = image;
		this.width = width;
		this.height = height;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
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
	
	
	
}
