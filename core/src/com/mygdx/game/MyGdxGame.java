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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Insects.Insect;
import com.mygdx.game.Insects.Ladybug;
import com.mygdx.game.PowerUp.HealthUp;
import com.mygdx.game.PowerUp.SpeedUp;
import com.mygdx.game.PowerUp.StrengthUp;

public class MyGdxGame extends ApplicationAdapter {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera; // OrthographicCamera --> 2D camera
	SpriteBatch batch; // SpriteBatch --> object dat sprites kan renderen
	 Sprite sprite; // Sprite --> object dat weergegeven moet worden
	private AssetManager assetManager;
	private Insect player;

	//powerups
	Array<HealthUp> healthUps = new Array<>();
	Array<SpeedUp> speedUps = new Array<>();
	Array<StrengthUp> strengthUps = new Array<>();

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

		strengthUps.add(new StrengthUp(200,200));


		// Haal de TiledMap op na deze geladen is
		map = assetManager.get("map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		centerCameraOnPlayer();

		//randomizePlayerPosition();
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
			player.draw(batch,1); //1 --> laag/diepte waarop speler getekend is


			// Render de power-ups
			for (HealthUp healthUp : healthUps) {
				batch.draw(healthUp.getTexture(), healthUp.getX(), healthUp.getY());
			}

			for (SpeedUp speedUp : speedUps) {
				batch.draw(speedUp.getTexture(), speedUp.getX(), speedUp.getY());
			}

			for (StrengthUp strengthUp : strengthUps) {
				batch.draw(strengthUp.getTexture(), strengthUp.getX(), strengthUp.getY());
			}

			batch.end();

			update(Gdx.graphics.getDeltaTime());
		}

	}

	// Update-methode voor de game-logica


	private void update(float delta) {
		player.update(delta); // Update de speler

		// Update de power-ups
		for (HealthUp healthUp : healthUps) {
			healthUp.update(delta);

			if (player.getBounds().overlaps(healthUp.getBounds())) {
				healthUps.removeValue(healthUp, true);
				player.increaseHealth(20);
			}
		}

		for (SpeedUp speedUp : speedUps) {
			speedUp.update(delta);

			if (player.getBounds().overlaps(speedUp.getBounds())) {
				speedUps.removeValue(speedUp, true);
				player.increaseSpeed(20);
			}
		}

		for (StrengthUp strengthUp : strengthUps) {
			strengthUp.update(delta);
			if (player.getBounds().overlaps(strengthUp.getBounds())) {
				strengthUps.removeValue(strengthUp, true);
				player.increaseStrength(20);
			}
		}

		centerCameraOnPlayer();
	}

	@Override
	public void dispose () {
		map.dispose();
		batch.dispose();
		assetManager.dispose();

	}
	//movementlogica
	public void handleInput() {
		float moveSpeed = 100f;
		float attackSpeed = 100f;
		float originalY = player.getY(); // Oorspronkelijke Y-positie opslaan.


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

		/*
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			player.setY(player.getY() + attackSpeed * Gdx.graphics.getDeltaTime());

			// Wacht een korte tijd (bijv. 0.2 seconden) om de aanval te simuleren.
			try {
				Thread.sleep(120);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Keer terug naar de oorspronkelijke positie.
			player.setY(originalY);
		}
		*/
		centerCameraOnPlayer();
	}
	//Centreren van de speler
	 private void centerCameraOnPlayer(){
		 camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		 camera.update();

	 }
/*
	// Methode om de spelerpositie te randomizeren
	private void randomizePlayerPosition() {
		float mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
		float mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

		float randomX = 0, randomY = 0;
		boolean spawnOnObject = true;

		// Blijf doorgaan totdat een geldige spawnlocatie is gevonden (niet op een object layer)
		while (spawnOnObject) {
			randomX = MathUtils.random(0, mapWidth - player.getWidth());
			randomY = MathUtils.random(0, mapHeight - player.getHeight());

			// Controleer of de gegenereerde positie zich op een object layer bevindt
			spawnOnObject = isOnObjectLayer(randomX, randomY);
		}

		player.setPosition(randomX, randomY);
	}

	// Methode om te controleren of de opgegeven positie zich op een object layer bevindt
	private boolean isOnObjectLayer(float x, float y) {
		// Vervang "objectLayer" door de naam van je object layer in de TMX-map
		TiledMapTileLayer objectLayer = (TiledMapTileLayer) map.getLayers().get("objectLayer");
																			// objectLayer --> naam van de object layer in de TMX-map, INVULLEN!
		// Converteer x en y naar celcoördinaten
		int cellX = (int) (x / objectLayer.getTileWidth());
		int cellY = (int) (y / objectLayer.getTileHeight());

		// Controleer of de cel op de object layer leeg is (bijvoorbeeld geen object aanwezig)
		TiledMapTileLayer.Cell cell = objectLayer.getCell(cellX, cellY);
		return cell != null;
	}

 */



}




