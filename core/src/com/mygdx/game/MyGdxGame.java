package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera; // OrthographicCamera --> 2D camera
	SpriteBatch batch; // SpriteBatch --> object dat weergegeven moet worden
	 Sprite sprite; // Sprite --> object dat weergegeven moet worden

	private AssetManager assetManager;

	@Override
	public void create () {
		camera = new OrthographicCamera(); // verkrijgen width en height van het scherm
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (assetManager.update()){
			camera.update();
			renderer.setView(camera);
			renderer.render();
		}



	}
	
	@Override
	public void dispose () {
		map.dispose();

	}
}
