package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Game;

public class MainMenuScreen extends ScreenAdapter {

    private final MyGdxGame game;
    private OrthographicCamera camera;
    private Texture soundonTexture;
    private Texture soundoffTexture;
    private Texture singleplayerTexture;
    private Texture multiplayerTexture;
    private Texture quitTexture;


    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        soundonTexture = new Texture("soundon.png");
        soundoffTexture = new Texture("soundoff.png");
        singleplayerTexture = new Texture("singleplayer.png");
        multiplayerTexture = new Texture("multiplayer.png");
        quitTexture = new Texture("quit.png");
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void dispose() {
        soundonTexture.dispose();
        soundoffTexture.dispose();
        singleplayerTexture.dispose();
        multiplayerTexture.dispose();
        quitTexture.dispose();
    }
}
