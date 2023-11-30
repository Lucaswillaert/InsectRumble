package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Insects.Insect;
import com.mygdx.game.Insects.Ladybug;
import com.mygdx.game.PowerUp.HealthUp;
import com.mygdx.game.PowerUp.SpeedUp;
import com.mygdx.game.PowerUp.StrengthUp;

public class GameScreen extends ScreenAdapter {
    private final MyGdxGame game;
    SpriteBatch batch; // SpriteBatch --> object dat sprites kan renderen
    Sprite sprite; // Sprite --> object dat weergegeven moet worden
    private Insect player;
    private float speedBoostDuration = 0;
    private float strengthBoostDuration = 0;
    private static int score = 0;
    private static int health = 100;
    private static int maxHealth = 100;

    //map en camera
    private TiledMap map;
    private static final String COLLISION_LAYER_NAME = "Collisions";
    private OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera; // OrthographicCamera --> 2D camera
    private AssetManager assetManager;


    //score
    //private int score = 0;
    private BitmapFont font;
    //powerups
    Array<HealthUp> healthUps = new Array<>();
    Array<SpeedUp> speedUps = new Array<>();
    Array<StrengthUp> strengthUps = new Array<>();
    private static final int NUM_HEALTH_UP_SPAWNS = 20;
    private static final int NUM_SPEED_UP_SPAWNS = 20;
    private static final int NUM_STRENGTH_UP_SPAWNS = 20;
    private static final float SPAWN_AREA_WIDTH = 800; // Adjust this based on your map dimensions

    public GameScreen(MyGdxGame game) {
        this.game = game;
        player = new Ladybug(100, 100, 100);  // Initialize the player object
        player.setPosition(Gdx.graphics.getWidth() / 2 - player.getWidth() / 2, Gdx.graphics.getHeight() / 2 - player.getHeight() / 2);
        font = new BitmapFont();

    }
    public void create () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch=new SpriteBatch();
        sprite=new Sprite();

        camera = new OrthographicCamera(); // verkrijgen width en height van het scherm
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        assetManager = new AssetManager(new InternalFileHandleResolver());

        // 1 malige loading
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("map.tmx", TiledMap.class);
        // Wacht tot alle assets zijn geladen
        assetManager.finishLoading();
        // Haal de TiledMap op na deze geladen is
        map = assetManager.get("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        centerCameraOnPlayer();



        float mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        // Randomly spawn HealthUps
        for (int i = 0; i < NUM_HEALTH_UP_SPAWNS; i++) {
            float randomX = MathUtils.random(0, SPAWN_AREA_WIDTH);
            float randomY = MathUtils.random(0, mapHeight);

            HealthUp healthUp = new HealthUp();
            healthUp.setPosition(randomX, randomY);
            healthUps.add(healthUp);
        }
        // Randomly spawn SpeedUps
        for (int i = 0; i < NUM_SPEED_UP_SPAWNS; i++) {
            float randomX = MathUtils.random(0, SPAWN_AREA_WIDTH);
            float randomY = MathUtils.random(0, mapHeight);

            SpeedUp speedUp = new SpeedUp();
            speedUp.setPosition(randomX, randomY);
            speedUps.add(speedUp);
        }
        // Randomly spawn StrengthUps
        for (int i = 0; i < NUM_STRENGTH_UP_SPAWNS; i++) {
            float randomX = MathUtils.random(0, SPAWN_AREA_WIDTH);
            float randomY = MathUtils.random(0, mapHeight);

            StrengthUp strengthUp = new StrengthUp(randomX, randomY);
            strengthUps.add(strengthUp);
        }



        randomizePlayerPosition();
    }


    @Override
    public void render (float delta) {
        //camera update + rendering
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (assetManager.update()){
            handleInput();
            camera.update();
            renderer.setView(camera);
            batch.setProjectionMatrix(camera.combined);

            //hier renderen we de objects, de map en de camera
            batch.begin();
            try{

                font.draw(batch, "Health: " + health + "/" + maxHealth, player.getX() - 230, player.getY() - 160);

                // Render the score
                font.draw(batch, "Score: " + score, player.getX() - 230, player.getY() - 180);

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
            }finally {
                batch.end();
                update(Gdx.graphics.getDeltaTime());
            }

        }
    }
    @Override
    public void show(){
        create();
    }
    // Update-methode voor de game-logica
    private void update(float delta) {
        player.update(delta); // Update de speler
        //update de positie van de health bar
        // Update de power-ups
        for (HealthUp healthUp : healthUps) {
            healthUp.update(delta);
            if (player.getBounds().overlaps(healthUp.getBounds())) {
                healthUps.removeValue(healthUp, true);
                player.increaseHealth(20);
                increaseScore(10);
            }
        }
        for (SpeedUp speedUp : speedUps) {
            speedUp.update(delta);
            if (player.getBounds().overlaps(speedUp.getBounds())) {
                speedUps.removeValue(speedUp, true);
                speedBoostDuration = 10; // Stel de duur van de snelheidsboost in op 10 seconden
                player.increaseSpeed(1000);
                increaseScore(10);
            }
        }
        //verlaag de duur van de snelheidsboost bij elke frame
        if (speedBoostDuration > 0) {
            speedBoostDuration -= delta;
            if (speedBoostDuration <= 0) {
                // Reset de snelheid nadat de duur is verstreken
                player.resetSpeed(); // Voeg deze methode toe aan je Insect-klasse
            }
        }
        for (StrengthUp strengthUp : strengthUps) {
            strengthUp.update(delta);
            if (player.getBounds().overlaps(strengthUp.getBounds())) {
                strengthUps.removeValue(strengthUp, true);
                player.increaseStrength(20);
                increaseScore(10);
            }
        }
        if (strengthBoostDuration > 0) {
            strengthBoostDuration -= delta;
            if (strengthBoostDuration <= 0) {
                // Reset de snelheid nadat de duur is verstreken
                player.resetStrength(); // Voeg deze methode toe aan je Insect-klasse
            }
        }

        centerCameraOnPlayer();
    }

    //movementlogica
    public void handleInput() {
        float moveSpeed = 100f;
        //float attackSpeed = 100f;
        float playerX = player.getX();
        float playerY = player.getY();
        float originalY = player.getY(); // Oorspronkelijke Y-positie opslaan.

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !isCollisionInDirection(playerX - moveSpeed, playerY, -1, 0)) {
                player.setX(playerX - moveSpeed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !isCollisionInDirection(playerX, playerY, 1, 0)) {
                player.setX(playerX + moveSpeed * Gdx.graphics.getDeltaTime());

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !isCollisionInDirection(playerX, playerY, 0, -1)  ) {
                player.setY(playerY - moveSpeed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && !isCollisionInDirection(playerX, playerY, 0, 1)) {
                player.setY(playerY + moveSpeed * Gdx.graphics.getDeltaTime());
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
        if (player != null){
            camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
            camera.update();
        }
    }
    // Methode om de spelerpositie te randomizeren
    private void randomizePlayerPosition() {
        float mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        float mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        float randomX = 0, randomY = 0;
        boolean spawnOnCollisionLayer = true;

        // Blijf doorgaan totdat een geldige spawnlocatie is gevonden (niet op een object layer)
        while (spawnOnCollisionLayer) {
            randomX = MathUtils.random(0, mapWidth - player.getWidth());
            randomY = MathUtils.random(0, mapHeight - player.getHeight());

            // Controleer of de gegenereerde positie zich op een object layer bevindt
            spawnOnCollisionLayer = isOnCollisionLayer(randomX, randomY);
        }

        player.setPosition(randomX, randomY);
    }

    // Methode om te controleren of de opgegeven positie zich op een object layer bevindt
    private boolean isOnCollisionLayer(float x, float y) {


        // Vervang "objectLayer" door de naam van je object layer in de TMX-map
        TiledMapTileLayer objectLayer = (TiledMapTileLayer)  map.getLayers().get(COLLISION_LAYER_NAME);
        // objectLayer --> naam van de object layer in de TMX-map, INVULLEN

        if (objectLayer != null) {
            // Convert x en y naar cel coordinaten
            int cellX = (int) (x / objectLayer.getTileWidth());
            int cellY = (int) (y / objectLayer.getTileHeight());


            // Zorg ervoor dat de celcoördinaten binnen de grenzen van de objectlaag blijven
            cellX = MathUtils.clamp(cellX, 0, objectLayer.getWidth() - 1);
            cellY = MathUtils.clamp(cellY, 0, objectLayer.getHeight() - 1);


            // Check if the cell is on the collision layer and not null
            TiledMapTileLayer.Cell cell = objectLayer.getCell(cellX, cellY);
            return cell != null;
        }
        // Return false if the collision layer is not found
        return false;
    }

    private boolean isCollisionInDirection(float x, float y, int deltaX, int deltaY) {
        TiledMapTileLayer objectLayer = (TiledMapTileLayer) map.getLayers().get(COLLISION_LAYER_NAME);

        // X en Y naar celcoördinaten
        int CurrentcellX = (int) (x / objectLayer.getTileWidth());
        int CurrentcellY = (int) (y / objectLayer.getTileHeight());


        // Nieuwe celcoördinaten na beweging
        int targetCellX = (int) ((x + deltaX) / objectLayer.getTileWidth());
        int targetCellY = (int) ((y + deltaY) / objectLayer.getTileHeight());


        // Zorg ervoor dat de doelcelcoördinaten binnen de grenzen van de objectlaag blijven
        targetCellX = MathUtils.clamp(targetCellX, 0, objectLayer.getWidth() - 1);
        targetCellY = MathUtils.clamp(targetCellY, 0, objectLayer.getHeight() - 1);

        // Controleer of de doelcel op de collision layer is en niet leeg
        TiledMapTileLayer.Cell targetCell = objectLayer.getCell(targetCellX, targetCellY);
        return targetCell != null;
    }




    private void restartGame() {

        // Reset de snelheid en de duur van de snelheidsboost
        player.resetSpeed();
        speedBoostDuration = 0;
    }
    private void increaseScore(int points) {
        score += points;
    }
    @Override
    public void dispose () {
        map.dispose();
        batch.dispose();
        assetManager.dispose();

    }

}

