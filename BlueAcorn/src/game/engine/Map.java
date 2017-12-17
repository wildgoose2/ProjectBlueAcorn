package game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Map {
	private Player player;
	private int xMax,yMax,xMin,yMin;
	private int chunkLength,tileLength;
	
	private HashMap<Integer, HashMap<Integer, Chunk>> chunks = new HashMap<Integer, HashMap<Integer, Chunk>>();
	
	
	public Map(Player player, int chunkLength, int tileLength) {
		this.player = player;
		this.chunkLength = chunkLength;
		this.tileLength = tileLength;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		int count = 0;
		
		for(int x = xMin; x <= xMax; x++) {
			if(chunks.get(x) == null)
				chunks.put(x, new HashMap<Integer, Chunk>());
			
			for(int y = yMin; y <= yMax; y++) {
				if(chunks.get(x).get(y) == null)
					chunks.get(x).put(y, generateChunk(x,y,chunkLength,tileLength));
				
				chunks.get(x).get(y).render(container, game, g);
				count++;
			}
		}
		
		if(player.isDebugging()) {
			g.drawString("Chunks: " + count, 50, 100);
			g.drawString("xMAX: " + xMax + " yMAX: " + yMax + " xMIN: " + xMin + " yMIN: " + yMin, 50, 150);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		xMax = (player.getxPos()+container.getWidth()/2)/(chunkLength*tileLength);
		yMax = (player.getyPos()+container.getHeight()/2)/(chunkLength*tileLength);
		xMin = (player.getxPos()-container.getWidth()/2)/(chunkLength*tileLength)-1;
		yMin = (player.getyPos()-container.getHeight()/2)/(chunkLength*tileLength)-1;
	}
	private Chunk generateChunk(int x, int y, int chunkLength, int tileLength) {
		List<TileID> tiles = new ArrayList<TileID>();
		for(int i = 0; i < chunkLength*chunkLength; i++) {
			TileID tile;
			if(Math.random() > 0.5)
				tile = TileID.GRASS;
			else
				tile = TileID.SAND;
			tiles.add(tile);
		}
		
		HashMap<Integer, ObjectID> objects = new HashMap<Integer, ObjectID>();
		for(int i = 0; i < (int)(Math.random()*20); i++)
		{
			ObjectID object;
			if(Math.random() > 0.5)
				object = ObjectID.ROCK;
			else
				object = ObjectID.TREE;
			objects.put((int)(Math.random()*chunkLength*chunkLength*tileLength*tileLength), object);
		}
		
		return new Chunk(player, x, y, chunkLength, tileLength, tiles, objects);	
	}
}
