package com.mygdx.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Insects.Insect;
import com.mygdx.game.Insects.Ladybug;
import com.mygdx.game.PowerUp.HealthUp;
import com.mygdx.game.PowerUp.SpeedUp;
import com.mygdx.game.PowerUp.StrengthUp;

public class MyGdxGame extends Game {
	//gamescreen declareren
	SpriteBatch batch;

	private GameScreen gameScreen;
	private MenuScreen menuScreen;


	@Override
	public void create() {
		//maak de gamescreens aan
		batch = new SpriteBatch();

		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen((Screen) menuScreen);

	}

	public void switchToGameScreen() {
		setScreen(gameScreen);
	}

	public void switchToMenuScreen() {
		setScreen( menuScreen);
	}


	@Override
	public void dispose() {
		batch.dispose();
		gameScreen.dispose();
		menuScreen.dispose();
	}

}




