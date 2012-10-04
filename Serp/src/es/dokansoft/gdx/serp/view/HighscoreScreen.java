package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Settings;

public class HighscoreScreen extends SerpScreen {
	SpriteBatch spriteBatch;
	OrthographicCamera camera;

	String lines[] = new String[5];

	Preferences settings = Settings.settings;
	Preferences highscores = Settings.highscores;
	
	Texture background = assets.get("background.png", Texture.class);
	Texture mainMenu = assets.get("mainmenu.png", Texture.class);
	Texture numbers = assets.get("numbers.png", Texture.class);
	Texture buttons = assets.get("buttons.png", Texture.class);

	Sound click = assets.get("click.ogg", Sound.class);

	public HighscoreScreen(Game game, AssetManager assets) {
		super(game,assets);

		for (int i = 0; i<5; i++) {
			lines[i] = ""+ (i + 1) + ". " + highscores.getInteger("" +i);
		}
	}

	@Override
	public void render(float delta){
		processInput();
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0);
		spriteBatch.draw(mainMenu, 64, 20, 0, 42, 196, 42);
			
		int y = 100;
		for (int i = 0; i < 5; i++) {
			drawText(lines[i], 20, y);
			y += 50;
		}
			
		spriteBatch.draw(buttons, 0, 416, 64, 64, 64, 64);
		spriteBatch.end();
	}
	
	public void processInput() {
		if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (inBounds(touchPos, 0, 416, 64, 64)){
				if (settings.getBoolean("soundOn"))
					click.play(1);
				game.setScreen(new MainMenuScreen(game,assets));
				return;
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawText(String line, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
