package de.hoemar.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Character {

	Sprite sprite;
	Rectangle rectangle;
	
	public Enemy() {
		texture = new Texture("Figur.png");
		sprite = new Sprite(texture);
		sprite.setBounds((float) (Math.random()*450) - 400, y - (GameMain.height/2), 32, 32);
		rectangle = new Rectangle ((float) (sprite.getX()+400), y - (GameMain.height/2), 32, 32);
	}
	
	@Override
	public void render(Batch batch, float deltaTime) {
		sprite.draw(batch);
	}
	
	public void updatePosition(int x) {
		sprite.setX(sprite.getX() - x);
	}
	
	public void changePosition(int direction, int amount) {
		
	}

}
