package de.hoemar.platform;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

public class GameMain extends ApplicationAdapter {
	
	TiledMap map;
	MapRenderer mapRenderer;
	OrthographicCamera camera;
	int a = 0;
	GameCharacter character;
	CollisionDetection collision;
	SpriteBatch batch;
	static int width = 800;
	static int height = 600;
	
	@Override
	public void create () {
		map = new TmxMapLoader().load("tilemap.tmx");
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, 25*32, 25*24);
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		batch = new SpriteBatch();
		character = new GameCharacter();
		collision = new CollisionDetection(map);
	}

	@Override
	public void render () {        
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		
//		a++;
		camera.position.x = 400 - 100 + character.x;
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

		getKeyboardInput();
		
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
		character.render(batch, deltaTime);
		batch.end();
        
	}
	
	public void getKeyboardInput() {
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)){ 
			if (collision.checkCollision(character, 0)){
				character.changePosition(0);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			if (collision.checkCollision(character, 2)){
				character.changePosition(2);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			if (collision.checkCollision(character, 3)){
				character.changePosition(3);
			}
		} else {
			if (collision.checkCollision(character, 1)){
				character.changePosition(1);
			}
		}
		
	}
}
