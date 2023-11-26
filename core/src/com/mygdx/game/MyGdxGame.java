package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Insects.Insect;
import com.mygdx.game.Insects.Ladybug;

public class MyGdxGame extends ApplicationAdapter {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera; // OrthographicCamera --> 2D camera
	SpriteBatch batch; // SpriteBatch --> object dat weergegeven moet worden
	 Sprite sprite; // Sprite --> object dat weergegeven moet worden

	private float rotationSpeed;
	Insect insect;


	private AssetManager assetManager;

	@Override
	public void create () {
		rotationSpeed = 0.5f; //rotatie zetten op 0.5graden per frame


		camera = new OrthographicCamera(); // verkrijgen width en height van het scherm
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		assetManager = new AssetManager(new InternalFileHandleResolver());

		// 1 malige loading
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("map.tmx", TiledMap.class);
		// Wacht tot alle assets zijn geladen
		assetManager.finishLoading();
		// Haal de TiledMap op nadat deze is geladen
		map = assetManager.get("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		Ladybug Ladybug = new Ladybug(100,100,100);


	}

	@Override
	public void render () {

		//camera update + rendering
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (assetManager.update()){
			//handleInput();
			camera.update();
			renderer.setView(camera);

			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//hier renderen we de objects, de map en de camera



			renderer.render();
			batch.end();





		}

	}
	
	@Override
	public void dispose () {
		map.dispose();

	}


/*
	private void handleInput() {

		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		float moveSpeed = 100f;

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-moveSpeed, 0, 0);
			//If the LEFT Key is pressed, translate the camera -3 units in the X-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(moveSpeed, 0, 0);
			//If the RIGHT Key is pressed, translate the camera 3 units in the X-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -moveSpeed, 0);
			//If the DOWN Key is pressed, translate the camera -3 units in the Y-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, moveSpeed, 0);
			//If the UP Key is pressed, translate the camera 3 units in the Y-Axis
		}


		camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100/camera.viewportWidth);

		float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
		float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

		camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
		camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);


		camera.update();

		System.out.println("Camera Position: (" + camera.position.x + ", " + camera.position.y + ")");
		System.out.println("Camera Zoom: " + camera.zoom);
	}

*/


}


