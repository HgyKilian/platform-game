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
	
	public CollisionDetection(TiledMap map) {
		layer = (TiledMapTileLayer)map.getLayers().get(0);
		
	}
	
	public boolean checkCollision(Character character, int direction) {
		int x_pos = (int) Math.floor(character.x / 32f);
		int y_pos = (int) Math.floor(character.y / 32f);
		switch (direction) {
			case 0: x_pos++; break;
			case 1: break;
			case 2: break;
			case 3: y_pos++; break;
			default: break;
		}
		Cell cell = layer.getCell(x_pos, y_pos);
		if (cell != null) {
			return false;
		}
		return true;
		
	}
	
}
