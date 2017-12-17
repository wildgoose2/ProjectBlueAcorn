package game.engine;

import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game.ObjectID;
import game.TileID;

public class Chunk {
	private final int CHUNK_X, CHUNK_Y, CHUNK_LENGTH, TILE_LENGTH;
	private Player player;
	private List<TileID> tiles;
	private List<GameObject> objects;
	private HashMap<TileID, Image> tileTextures = new HashMap<TileID, Image>();
	
	public Chunk(Player player, int x, int y, int chunkLength, int tileLength, List<TileID> tiles, List<GameObject> objects)
	{
		this.player = player;
		this.CHUNK_X = x;
		this.CHUNK_Y = y;
		this.CHUNK_LENGTH = chunkLength;
		this.TILE_LENGTH = tileLength;
		this.tiles = tiles;
		this.objects = objects;
		
		try {
			loadTextures();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void renderTiles(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		int xPos = (int)player.getxPos() - CHUNK_X*CHUNK_LENGTH*TILE_LENGTH;
		int yPos = (int)player.getyPos() - CHUNK_Y*CHUNK_LENGTH*TILE_LENGTH;
		
		for(int i = 0; i < this.CHUNK_LENGTH * this.CHUNK_LENGTH; i++)
		{
			int tempX = (container.getWidth()/2) + ((i%this.CHUNK_LENGTH)*TILE_LENGTH) - xPos;
			int tempY = (container.getHeight()/2) + ((i/this.CHUNK_LENGTH)*TILE_LENGTH) - yPos;
			
			if(tempX > -(TILE_LENGTH) && tempY > -(TILE_LENGTH) && tempX < container.getWidth() && tempY < container.getHeight())
			{
				g.drawImage(tileTextures.get(tiles.get(i)), tempX, tempY);
			}
		}
	}
	public void renderObjects(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		int xPos = (int)player.getxPos() - CHUNK_X*CHUNK_LENGTH*TILE_LENGTH;
		int yPos =(int) player.getyPos() - CHUNK_Y*CHUNK_LENGTH*TILE_LENGTH;
		
		for(GameObject object : objects) {
			g.drawImage(object.getImage(), (container.getWidth()/2) + (object.getxPos()) - xPos, (container.getHeight()/2) + (object.getyPos()) - yPos);
		}
	}
	public void update(GameContainer container, StateBasedGame game, int delta) {
		for(GameObject object: objects) {
			object.update(container, game, delta);
		}
	}

	
	public void loadTextures() throws SlickException {
		tileTextures.put(TileID.GRASS, new Image("res/grass.png"));
		tileTextures.put(TileID.SAND, new Image("res/sand.png"));
	}
}
