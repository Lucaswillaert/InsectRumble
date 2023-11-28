package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {
    private final MyGdxGame game;
    private OrthographicCamera camera;
    private Texture soundonTexture;
    private Texture soundoffTexture;
    private Texture singleplayerTexture;
    private Texture multiplayerTexture;
    private Texture quitTexture;

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
    }
}
