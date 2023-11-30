package com.mygdx.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

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




