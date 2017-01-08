package de.hoemar.platform;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMain extends ApplicationAdapter {
	
	TiledMap map;
	MapRenderer mapRenderer;
	OrthographicCamera camera;
	int a = 0;
	GameCharacter character;
	ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	CollisionDetection collision;
	SpriteBatch batch;
	boolean isJumping = false;
	boolean isFalling = false;
	int jumpingHeight = 0;
	static int width = 800;
	static int height = 600;
	BitmapFont font;
	int leben = 5;
	int killedEnemys = 0;
	final int EnemysToKill = 20;
	int win = 0;
	Sprite overlay;
	final int gravity = 2048;
	int oldPositionY;
	
	
	@Override
	public void create () {
		map = new TmxMapLoader().load("tilemap.tmx");
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, 25*32, 25*24);
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		batch = new SpriteBatch();
		character = new GameCharacter();
		for (int i=0 ; i<10 ; i++) {
			enemys.add(new Enemy(EnemyPosition.level1[i][0], EnemyPosition.level1[i][1]));
		}
		collision = new CollisionDetection(map);
	}

	@Override
	public void render () {        
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		float deltaTime = Gdx.graphics.getDeltaTime();
				
		int totalX = (int) (camera.position.x - 400);
		camera.position.x = 300 + character.x;
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

		if (win == 0) {
//			if (isJumping) {
//				jump(deltaTime);
//			} else {
//				if (collision.checkCollision(character, 1)){
//					isFalling = true;
//					character.changePosition(1, Math.round(deltaTime*256));
//				} else {
//					isFalling = false;
//				}
//			}
			jump(deltaTime);
			getKeyboardInput(deltaTime);
			for (Enemy e : enemys) {
				if (e.rectangle.overlaps(character.rectangle)) {
					if (!isFalling) {
						//verloren
						enemys.remove(e);
						leben--;
						if (leben <= 0) {
							overlay = new Sprite(new Texture("loose.png"));
							overlay.setBounds(-400, -300, 800, 600);
							win = -1;
						}
						
					} else {
						//Gegener besiegt
						enemys.remove(e);
						killedEnemys++;
//						win = 1;
//						overlay.setTexture(new Texture("win.png"));
						
					}
					break;
				}
			}
			for (Enemy e : enemys) {
				e.changePosition(Math.round(deltaTime*32));
			}
		}
		
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
		if (win == 0) {
			character.render(batch, deltaTime);
			for (int i=0 ; i<enemys.size() ; i++) {
				enemys.get(i).updatePosition(totalX);
				enemys.get(i).render(batch, deltaTime);
			}
			font.draw(batch, "Leben: " + leben, -380, 280);
			font.draw(batch, "Feinde: " + killedEnemys + "/" + EnemysToKill, -380, 250);
		} else {
			overlay.draw(batch);
		}
		batch.end();
        
	}
	
	private void jump(float deltaTime) {
		System.out.println(character.y);
//		character.jumpTime+=deltaTime;
		character.velocity = character.velocity - gravity*deltaTime;
//		character.jumpTime = character.jumpTime - gravity*deltaTime;
//		int amount = (int) Math.round(character.jumpStartVelocity*character.jumpTime-0.5*gravity*Math.pow(character.jumpTime,2)-character.brakeVelocity*character.jumpTime);
		int amount = Math.round(character.velocity*deltaTime);
		if (amount >= 0) {//hoch
			if (collision.checkCollision(character, 3)){
//				jumpingHeight+=amount;
//				if (jumpingHeight >= 128) {
//					amount-= (jumpingHeight-128);
//					jumpingHeight = 0;
//					isJumping = false;
//				}
				character.changePosition(3, Math.abs(amount));
				
				
			} else {
//				character.jumpTime = 0;
				character.velocity = 0;
				isFalling = true;
//				character.brakeVelocity = Math.round(character.jumpStartVelocity - gravity*character.jumpTime);
			}
		} else {//runter
			if (collision.checkCollision(character, 1)){
				if (!isFalling) {
					isFalling = true;
//					character.jumpStartPosition = character.y;
//					character.jumpStartVelocity = 512;
				}	
//				jumpingHeight+=amount;
//				if (jumpingHeight >= 128) {
//					amount-= (jumpingHeight-128);
//					jumpingHeight = 0;
//					isJumping = false;
//				}
				character.changePosition(1, Math.abs(amount));
			} else {
				character.velocity=0;
				if (isFalling) {
//					character.jumpTime = 0;
//					character.jumpStartPosition = 0;
//					character.jumpStartVelocity = 0;
					
					isFalling = false;
				}
				
			}
		}
		
//			else {
//			isJumping = false;
//			jumpingHeight = 0;
//		}
		
	}

	public void getKeyboardInput(float deltaTime) {
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){ 
			if (collision.checkCollision(character, 0)){
				character.changePosition(0, Math.round(deltaTime*256));
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			if (collision.checkCollision(character, 2)){
				character.changePosition(2, Math.round(deltaTime*256));
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			if (collision.checkCollision(character, 3) && !collision.checkCollision(character, 1)){
//				character.jumpTime = 0;
//				character.jumpStartPosition = character.y;
//				character.jumpStartVelocity = 512;
				character.velocity = 768;
				isFalling = false;
//				isJumping = true;
//				isFalling = false;
			}
		}
		
	}
}
