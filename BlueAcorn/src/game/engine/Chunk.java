package game.engine;

import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Chunk {
	private final int CHUNK_X, CHUNK_Y, CHUNK_LENGTH, TILE_LENGTH;
	private Player player;
	private List<TileID> tiles;
	private HashMap<Integer, ObjectID> objects;
	private HashMap<TileID, Image> tileTextures = new HashMap<TileID, Image>();
	private HashMap<ObjectID, Image> objectTextures = new HashMap<ObjectID, Image>();
	
	public Chunk(Player player, int x, int y, int chunkLength, int tileLength, List<TileID> tiles, HashMap<Integer, ObjectID> objects)
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
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		int xPos = player.getxPos() - CHUNK_X*CHUNK_LENGTH*TILE_LENGTH;
		int yPos = player.getyPos() - CHUNK_Y*CHUNK_LENGTH*TILE_LENGTH;
		
		for(int i = 0; i < this.CHUNK_LENGTH * this.CHUNK_LENGTH; i++)
		{
			int tempX = (container.getWidth()/2) + ((i%this.CHUNK_LENGTH)*TILE_LENGTH) - xPos;
			int tempY = (container.getHeight()/2) + ((i/this.CHUNK_LENGTH)*TILE_LENGTH) - yPos;
			
			if(tempX > -(TILE_LENGTH) && tempY > -(TILE_LENGTH) && tempX < container.getWidth() && tempY < container.getHeight())
			{
				g.drawImage(tileTextures.get(tiles.get(i)), tempX, tempY);
			}
		}
		for(Integer i : objects.keySet()) {
			g.drawImage(objectTextures.get(objects.get(i)), (container.getWidth()/2) + (i%(CHUNK_LENGTH*TILE_LENGTH)) - xPos, (container.getHeight()/2) + (i/(CHUNK_LENGTH*TILE_LENGTH)) - yPos);
		}
		
	}

	
	public void loadTextures() throws SlickException {
		tileTextures.put(TileID.GRASS, new Image("res/grass.png"));
		tileTextures.put(TileID.SAND, new Image("res/sand.png"));
		objectTextures.put(ObjectID.TREE, new Image("res/tree.png"));
		objectTextures.put(ObjectID.ROCK, new Image("res/rock.png"));
	}
}
