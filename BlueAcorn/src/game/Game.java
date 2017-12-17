package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	public static final String gamename = "BlueAcorn";
	public static final int menu = 0;
	public static final int play = 1;

	public Game(String name) {
		super(name);
		this.addState(new Menu(0));
		this.addState(new Play(1));
	}

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

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}

}
