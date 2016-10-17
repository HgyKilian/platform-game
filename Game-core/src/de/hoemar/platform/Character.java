package de.hoemar.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Character {

	int x = 100;
	int y = 32 * 8;
	Texture texture;
	
	public abstract void render(Batch batch, float deltaTime);
	
	
}
