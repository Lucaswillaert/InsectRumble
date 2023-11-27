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
	private AssetManager assetManager;

	private Ladybug player;

	private float playerX = 200;
	private float playerY = 200;
	@Override
	public void create () {



		camera = new OrthographicCamera(); // verkrijgen width en height van het scherm
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		assetManager = new AssetManager(new InternalFileHandleResolver());


		// 1 malige loading
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("map.tmx", TiledMap.class);
		// Wacht tot alle assets zijn geladen
		assetManager.finishLoading();


		//creeëren van instantie Ladybug als speler en zet initiële positie
		player = new Ladybug(100, 100, 100);
		player.setPosition(Gdx.graphics.getWidth()/2 - player.getWidth()/2, Gdx.graphics.getHeight() / 2 - player.getHeight() / 2);


		// Haal de TiledMap op nadat deze is geladen
		map = assetManager.get("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		centerCameraOnPlayer();
	}

	@Override
	public void render () {

		//camera update + rendering
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (assetManager.update()){
			handleInput();
			camera.update();
			renderer.setView(camera);

			batch.setProjectionMatrix(camera.combined);

			//hier renderen we de objects, de map en de camera
			batch.begin();
			renderer.render();
			player.draw(batch,1);

			batch.end();

		}

	}
	
	@Override
	public void dispose () {
		map.dispose();
		batch.dispose();
		assetManager.dispose();

	}


	//movement logicatie van de speler
	public void handleInput() {
		float moveSpeed = 100f;

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.setX(player.getX() - moveSpeed * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.setX(player.getX() + moveSpeed * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.setY(player.getY() - moveSpeed * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.setY(player.getY() + moveSpeed * Gdx.graphics.getDeltaTime());
		}

		// Centreren van de camera op de speler na beweging
		centerCameraOnPlayer();
	}
	//centreren van de speler op de camera
	 private void centerCameraOnPlayer(){
		 camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		 camera.update();

	 }

}


