package de.hoemar.platform;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
			enemys.add(new Enemy());
		}
		collision = new CollisionDetection(map);
	}

	@Override
	public void render () {        
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		float deltaTime = Gdx.graphics.getDeltaTime();
				
		int oldCameraPosition = (int) camera.position.x;
		camera.position.x = 400 - 100 + character.x;
		oldCameraPosition = (int) (camera.position.x - oldCameraPosition);
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

		if (isJumping) {
			jump(deltaTime);
		} else {
			if (collision.checkCollision(character, 1)){
				isFalling = true;
				character.changePosition(1, Math.round(deltaTime*256));
			} else {
				isFalling = false;
			}
		}
		getKeyboardInput(deltaTime);
		for (Enemy e : enemys) {
			if (e.rectangle.overlaps(character.rectangle)) {
				if (!isFalling) {
					//verloren
					enemys.remove(e);
					leben--;
				} else {
					//Gegener besiegt
					enemys.remove(e);
					killedEnemys++;
				}
				break;
			}
		}
		
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
		character.render(batch, deltaTime);
		for (int i=0 ; i<enemys.size() ; i++) {
			enemys.get(i).updatePosition(oldCameraPosition);
			enemys.get(i).render(batch, deltaTime);
		}
		font.draw(batch, "Leben: " + leben, -380, 280);
		font.draw(batch, "Feinde: " + killedEnemys + "/" + EnemysToKill, -380, 250);
		batch.end();
        
	}
	
	private void jump(float deltaTime) {
		if (collision.checkCollision(character, 3)){
			int amount = Math.round(deltaTime*256);
			jumpingHeight+=amount;
			if (jumpingHeight >= 128) {
				amount-= (jumpingHeight-128);
				jumpingHeight = 0;
				isJumping = false;
			}
			character.changePosition(3, amount);
		} else {
			isJumping = false;
			jumpingHeight = 0;
		}
		
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
				isJumping = true;
				isFalling = false;
			}
		}
		
	}
}
