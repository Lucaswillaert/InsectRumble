package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
public class MenuScreen extends ScreenAdapter {
    private final MyGdxGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture singleplayerTexture;
    private Texture multiplayerTexture;
    private Texture quitTexture;
    private Texture backgroundTexture;
    private  Texture titleTexture;
    private Music backgroundMusic;

    public MenuScreen(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();

        camera.setToOrtho(false, 800, 480);
        singleplayerTexture = new Texture("singleplayer.png");
        multiplayerTexture = new Texture("multiplayer.png");
        quitTexture = new Texture("quit.png");
        backgroundTexture = new Texture("background.jpg");
        titleTexture = new Texture("Title.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Background_music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

    }
    @Override
    public void render(float delta) {
        // Logica voor het renderen van het menu
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        // Schaal de afbeelding zodat deze het volledige scherm bedekt
        float scaleX = Gdx.graphics.getWidth() / (float) backgroundTexture.getWidth();
        float scaleY = Gdx.graphics.getHeight() / (float) backgroundTexture.getHeight();

        // Pas de camera aan om de afbeelding in het midden te centreren
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
        camera.update();

        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // titelafbeelding
        float titleX = (Gdx.graphics.getWidth() - titleTexture.getWidth()) / 2f;
        float titleY = Gdx.graphics.getHeight() - titleTexture.getHeight() - 20f; // Afstand van boven (20 pixels in dit voorbeeld)
        game.batch.draw(titleTexture, titleX, titleY);

        // knoppen onder elkaar te zetten
        float buttonHeight = singleplayerTexture.getHeight();
        float verticalSpace = 20f; // Meer ruimte tussen de knoppen
        float totalButtonHeight = 3 * buttonHeight + 2 * verticalSpace; // Hier 3 keer de knophoogte en 2 keer de ruimte ertussen
        float startY = (Gdx.graphics.getHeight() - totalButtonHeight) / 2f;

        game.batch.draw(quitTexture, (Gdx.graphics.getWidth() - quitTexture.getWidth()) / 2f, startY);
        game.batch.draw(multiplayerTexture, (Gdx.graphics.getWidth() - multiplayerTexture.getWidth()) / 2f, startY + buttonHeight + verticalSpace);
        game.batch.draw(singleplayerTexture, (Gdx.graphics.getWidth() - singleplayerTexture.getWidth()) / 2f, startY + 2 * (buttonHeight + verticalSpace));
        game.batch.end();
        handleInput();
    }
    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            //bij het drukken van de spatiebalk start het spel
            game.switchToGameScreen();
        }
        if (Gdx.input.isTouched()) {
            // omzetten naar schermcoördinaten
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // knoppen aangeraakt --> uitvoeren
            if (isButtonPressed(quitTexture, touchX, touchY)) {
                Gdx.app.exit(); // Beëindig de applicatie als "Quit" wordt aangeraakt
            } else if (isButtonPressed(multiplayerTexture, touchX, touchY)) {
                Gdx.app.exit();
            } else if (isButtonPressed(singleplayerTexture, touchX, touchY)) {
                game.switchToGameScreen(); // Start het spel als "Singleplayer" wordt aangeraakt
                backgroundMusic.stop();
            }
        }
    }
    private boolean isButtonPressed(Texture buttonTexture, float touchX, float touchY) {
        float buttonX = (Gdx.graphics.getWidth() - buttonTexture.getWidth()) / 2f;
        float buttonY = (Gdx.graphics.getHeight() - buttonTexture.getHeight()) / 2f;
        return touchX >= buttonX && touchX <= buttonX + buttonTexture.getWidth() &&
                touchY >= buttonY && touchY <= buttonY + buttonTexture.getHeight();
    }
    @Override
    public void dispose() {
        backgroundMusic.dispose();
        singleplayerTexture.dispose();
        multiplayerTexture.dispose();
        quitTexture.dispose();
        backgroundTexture.dispose();
    }

}
