package game.engine;

import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game.TileID;

public class Chunk {
	private final int CHUNK_X, CHUNK_Y, CHUNK_LENGTH, TILE_LENGTH;					//Constants storing chunk position data and chunk tile and pixel length data
	private Player player;															//Stores the player object														
	private List<TileID> tiles;														//List storing tile data of chunk
	private List<GameObject> objects;												//List storing gameobjects that are in the chunk
	private HashMap<TileID, Image> tileTextures = new HashMap<TileID, Image>();		//HashMap of tile textures !TEMPORARY!
	
	
	//Constructor that initializes all fields
	public Chunk(Player player, int x, int y, int chunkLength, int tileLength, List<TileID> tiles, List<GameObject> objects)
	{
		this.player = player;
		this.CHUNK_X = x;
		this.CHUNK_Y = y;
		this.CHUNK_LENGTH = chunkLength;
		this.TILE_LENGTH = tileLength;
		this.tiles = tiles;
		this.objects = objects;
		
		//Loads the tile texture images
		try {
			loadTextures();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	//Renders the tiles to the screen
	public void renderTiles(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		//Calculates the graphical position relative to the player
		int xPos = (int)player.getxPos() - CHUNK_X*CHUNK_LENGTH*TILE_LENGTH;
		int yPos = (int)player.getyPos() - CHUNK_Y*CHUNK_LENGTH*TILE_LENGTH;
		
		//Cycles through all the tiles and renders them
		for(int i = 0; i < this.CHUNK_LENGTH * this.CHUNK_LENGTH; i++)
		{
			//Tile postions relative to the chunk
			int tempX = (container.getWidth()/2) + ((i%this.CHUNK_LENGTH)*TILE_LENGTH) - xPos;
			int tempY = (container.getHeight()/2) + ((i/this.CHUNK_LENGTH)*TILE_LENGTH) - yPos;
			
			//Checks if tile is visible
			if(tempX > -(TILE_LENGTH) && tempY > -(TILE_LENGTH) && tempX < container.getWidth() && tempY < container.getHeight())
			{
				g.drawImage(tileTextures.get(tiles.get(i)), tempX, tempY);
			}
		}
	}
	
	//Renders objects to the screen
	public void renderObjects(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		//Calculates the graphical position relative to the player
		int xPos = (int)player.getxPos() - CHUNK_X*CHUNK_LENGTH*TILE_LENGTH;
		int yPos =(int) player.getyPos() - CHUNK_Y*CHUNK_LENGTH*TILE_LENGTH;
		
		//Renders each object relative to the player
		for(GameObject object : objects) {
			g.drawImage(object.getImage(), (container.getWidth()/2) + (object.getxPos()) - xPos, (container.getHeight()/2) + (object.getyPos()) - yPos);
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
		//Calls update method of each object in chunk
		for(GameObject object: objects) {
			object.update(container, game, delta);
		}
	}

	//Loads tile texture files
	public void loadTextures() throws SlickException {
		tileTextures.put(TileID.GRASS, new Image("res/grass.png"));
		tileTextures.put(TileID.SAND, new Image("res/sand.png"));
		tileTextures.put(TileID.WATER, new Image("res/water.png"));
	}
}
