package game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game.ObjectID;
import game.Rock;
import game.TileID;
import game.Tree;

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
				
				chunks.get(x).get(y).renderTiles(container, game, g);
				count++;
			}
		}
		for(int x = xMin; x <= xMax; x++)
			for(int y = yMin; y <= yMax; y++)	
				chunks.get(x).get(y).renderObjects(container, game, g);
		
		if(player.isDebugging()) {
			g.drawString("Chunks: " + count, 50, 100);
			g.drawString("xMAX: " + xMax + " yMAX: " + yMax + " xMIN: " + xMin + " yMIN: " + yMin, 50, 150);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		xMax = ((int)player.getxPos()+container.getWidth()/2)/(chunkLength*tileLength);
		yMax = ((int)player.getyPos()+container.getHeight()/2)/(chunkLength*tileLength);
		xMin = ((int)player.getxPos()-container.getWidth()/2)/(chunkLength*tileLength)-2;
		yMin = ((int)player.getyPos()-container.getHeight()/2)/(chunkLength*tileLength)-2;
		
		for(int x = xMin; x <= xMax; x++) {
			if(chunks.get(x) == null)
				chunks.put(x, new HashMap<Integer, Chunk>());
			
			for(int y = yMin; y <= yMax; y++) {
				if(chunks.get(x).get(y) == null)
					chunks.get(x).put(y, generateChunk(x,y,chunkLength,tileLength));
				
				chunks.get(x).get(y).update(container, game, delta);
			}
		}
	}
	private Chunk generateChunk(int x, int y, int chunkLength, int tileLength) throws SlickException{
		List<TileID> tiles = new ArrayList<TileID>();
		for(int i = 0; i < chunkLength*chunkLength; i++) {
			TileID tile;
			if(Math.random() > 0.5)
				tile = TileID.GRASS;
			else
				tile = TileID.SAND;
			tiles.add(tile);
		}
		
		List<GameObject> objects = new ArrayList<GameObject>();
		for(int i = 0; i < (int)(Math.random()*20); i++)
		{
			GameObject object;
			if(Math.random() > 0.5)
				object = new Tree((int)(Math.random()*chunkLength*tileLength),(int)(Math.random()*chunkLength*tileLength),new Image("res/tree2.png"), ObjectID.TREE);
			else
				object = new Rock((int)(Math.random()*chunkLength*tileLength),(int)(Math.random()*chunkLength*tileLength),new Image("res/rock2.png"), ObjectID.ROCK);
			objects.add(object);
		}
		
		return new Chunk(player, x, y, chunkLength, tileLength, tiles, objects);	
	}
}
