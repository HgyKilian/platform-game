package de.hoemar.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GameCharacter extends Character {

	Sprite sprite;
	Rectangle rectangle;
	float velocity = 0;
	
	public GameCharacter() {
		texture = new Texture("chara.png");
		sprite = new Sprite(texture);
		sprite.setBounds(100 - (GameMain.width/2), y - (GameMain.height/2), 32, 32);
		rectangle = new Rectangle(x, y - (GameMain.height/2), 32, 32);
	}

	@Override
	public void render(Batch batch, float deltaTime) {
		sprite.draw(batch);
	}
	
	public void changePosition(int direction, int amount) {
		boolean testagain = false;
		switch (direction) {
			case 0: x+=amount; testagain = (Math.floor(x/32) == Math.floor((x-amount)/32)) ? false : true; break;
			case 1: y-=amount; testagain = (Math.floor(y/32) == Math.floor((y+amount)/32)) ? false : true; break;
			case 2: x-=amount; testagain = (Math.floor(x/32) == Math.floor((x+amount)/32)) ? false : true; break;
			case 3: y+=amount; testagain = (Math.floor(y/32) == Math.floor((y-amount)/32)) ? false : true; break;
			default: break;
		}
		
		if (testagain) {
			if (!CollisionDetection.collisionDetection.checkCollision(this, direction)) {
				switch (direction) {
					case 0: x = ((int) Math.floor(x/32f)) * 32 ; break;
					case 1: y = ((int) Math.ceil(y/32f)) * 32; break;
					case 2: x = ((int) Math.ceil(x/32f)) * 32; break;
					case 3: y = ((int) Math.floor(y/32f)) * 32; break;
					default: break;
				}
			}
		}
		
		sprite.setPosition(100 - (GameMain.width/2), y - (GameMain.height/2));
		rectangle.set(x, y - (GameMain.height/2), 32, 32);
			
	}
	
	public void resetPosition() {
		x=100;
		y=32*8;
		velocity=0;
		sprite.setBounds(100 - (GameMain.width/2), y - (GameMain.height/2), 32, 32);
		rectangle = new Rectangle(x, y - (GameMain.height/2), 32, 32);
	}

	
}
