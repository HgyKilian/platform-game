package de.hoemar.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameCharacter extends Character {

	Sprite sprite;
	
	public GameCharacter() {
		texture = new Texture("Figur.png");
		sprite = new Sprite(texture);
		sprite.setBounds(100 - (GameMain.width/2), y - (GameMain.height/2), 32, 32);
	}

	@Override
	public void render(Batch batch, float deltaTime) {
		sprite.draw(batch);
	}
	
	public void changePosition(int direction) {
		switch (direction) {
			case 0: x++; break;
			case 1: y--; break;
			case 2: x--; break;
			case 3: y++; break;
			default: break;
		}
		sprite.setPosition(100 - (GameMain.width/2), y - (GameMain.height/2));
			
	}

	
}
