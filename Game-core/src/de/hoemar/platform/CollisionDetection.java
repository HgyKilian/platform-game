package de.hoemar.platform;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class CollisionDetection {

//	public Pool<Rectangle> rectPool = new Pool<Rectangle>() {
//		@Override
//		protected Rectangle newObject () {
//			return new Rectangle();
//		}
//	};
//	public Array<Rectangle> tiles = new Array<Rectangle>();
	
	TiledMapTileLayer layer;
	static CollisionDetection collisionDetection;
	
	public CollisionDetection(TiledMap map) {
		layer = (TiledMapTileLayer)map.getLayers().get(0);
		collisionDetection = this;
		
	}
	
	public boolean checkCollision(Character character, int direction) {
		int x_pos1 = (int) ((direction == 2) ?  Math.ceil(character.x / 32f) : Math.floor(character.x / 32f));
		int y_pos1 = (int) ((direction == 1) ?  Math.ceil(character.y / 32f) : Math.floor(character.y / 32f));
		int x_pos2 = (int) ((direction == 1 || direction == 3 || direction == 2) ?  Math.ceil(character.x / 32f) : Math.floor(character.x / 32f));
		int y_pos2 = (int) ((direction == 0 || direction == 2 || direction ==1) ?  Math.ceil(character.y / 32f) : Math.floor(character.y / 32f));
		switch (direction) {
			case 0: x_pos1++; x_pos2++; break;
			case 1: y_pos1--; y_pos2--; break;
			case 2: x_pos1--; x_pos2--; break;
			case 3: y_pos1++; y_pos2++; break;
			default: break;
		}
   	 	Cell cell1 = layer.getCell(x_pos1, y_pos1);
  	 	Cell cell2 = layer.getCell(x_pos2, y_pos2);
		if (cell1 == null && cell2 == null) {
			return true;
		}
		return false;
		
	}
	
}
