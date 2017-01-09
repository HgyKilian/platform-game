package de.hoemar.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Character {

	Sprite sprite;
	Rectangle rectangle;
	final int startPosition;
	final int returnPosition;
	boolean direction = false;
	
	public Enemy(int startPositon, int returnPosition, int yPosition) {
		texture = new Texture("ene.png");
		sprite = new Sprite(texture);
		x = startPositon*32-400;
		y = yPosition-300;
		sprite.setBounds(x, y, 32, 32);
		rectangle = new Rectangle (x+400, y, 32, 32);
		this.startPosition = startPositon;
		this.returnPosition = returnPosition;
	}
	
	@Override
	public void render(Batch batch, float deltaTime) {
		sprite.draw(batch);
	}
	
	public void updatePosition(int totalX) {
		sprite.setX(sprite.getX() - totalX);
	}
	
	public void changePosition(int amount) {
		if (direction) {
			x+=amount;
			if (Math.floor((x+400)/32f)+1 >= startPosition) {
				x-= amount;
				direction = false;
			}
		} else {
			x-=amount;
			if (Math.floor((x+400)/32f) <= returnPosition-1) {
				x+= amount;
				direction = true;
			}
		}
		sprite.setPosition(x, y);
		rectangle.set(x+400, y, 32, 32);
		
	}

}
