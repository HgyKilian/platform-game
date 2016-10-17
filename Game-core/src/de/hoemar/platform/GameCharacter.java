package de.hoemar.platform;

import javax.sql.rowset.CachedRowSet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GameCharacter extends Character {

	public GameCharacter() {
		texture = new Texture("Figur.png");
	}

	@Override
	public void render(Batch batch, float deltaTime) {
		batch.draw(texture, x, y, 32, 32);		
	}
	
	public void changePosition(int direction) {
		switch (direction) {
			case 0: x++; break;
			case 1: y--; break;
			case 2: x--; break;
			case 3: y++; break;
			default: break;
		}
			
	}

	
}
