package de.hoemar.platform;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

public class GameMain extends ApplicationAdapter implements MapRenderer  {
	
	TiledMap map;
	MapRenderer mapRenderer;
	OrthographicCamera camera;
	int a = 0;
	GameCharacter character;
	Batch batch;
	CollisionDetection collision;
	
	@Override
	public void create () {
		map = new TmxMapLoader().load("tilemap.tmx");
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		float unitScale = 1 / 32f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(map, unitScale);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 25, 25);
		mapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
		batch = renderer.getBatch();
		character = new GameCharacter();
		collision = new CollisionDetection(map);
	}

	@Override
	public void render () {        
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		
//		a++;
		camera.position.x = 13;
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();

		if (collision.checkCollision(character, 0)){
			character.changePosition(0);
		}
		
		batch.begin();
		character.render(batch, deltaTime);
		batch.end();
        
	}

	@Override
	public void render(int[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setView(OrthographicCamera arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setView(Matrix4 arg0, float arg1, float arg2, float arg3, float arg4) {
		// TODO Auto-generated method stub
		
	}
}
