package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	OrthographicCamera camera; // OrthographicCamera --> 2D camera
	SpriteBatch batch; // SpriteBatch --> object dat weergegeven moet worden
	Sprite sprite; // Sprite --> object dat weergegeven moet worden

	@Override
	public void create () {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //verkrijgen width en height van het scherm
		batch = new SpriteBatch();
		sprite = new Sprite(new Texture("groep3.jpg")); //afbeelding inladen
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2); //positie van afbeelding instellen
		//sprite.setSize(); --> grootte van afbeelding instellen
		 
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 1, 1);

		batch.setProjectionMatrix(camera.combined); //camera instellen STANDAARD

		batch.begin();
		sprite.draw(batch);
		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
