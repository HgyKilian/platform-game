package de.hoemar.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Character {

	int x = 0;
	int y = 32 * 6;
	Texture texture;
	
	public abstract void render(Batch batch, float deltaTime);
	
	
}
