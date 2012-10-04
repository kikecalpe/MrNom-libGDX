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

public class HelpScreen extends SerpScreen {
	SpriteBatch spriteBatch;
	OrthographicCamera camera;

	Preferences settings = Settings.settings;
		
	Texture background = assets.get("background.png", Texture.class);
	Texture mainMenu = assets.get("mainmenu.png", Texture.class);
	Texture numbers = assets.get("numbers.png", Texture.class);
	Texture buttons = assets.get("buttons.png", Texture.class);
	Texture help1 = assets.get("help1.png", Texture.class);
	Sound click = assets.get("click.ogg", Sound.class);

	public HelpScreen(Game game, AssetManager assets) {
		super(game,assets);
		
	}

	@Override
	public void render(float delta) {
		processInput();
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0);
		spriteBatch.draw(help1, 64, 100);
		spriteBatch.draw(buttons, 256, 416, 0, 64, 64, 64);
	}
	
	public void processInput() {
		if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (inBounds(touchPos, 256, 416, 64, 64)){
				if (settings.getBoolean("soundOn"))
					click.play(1);
				game.setScreen(new HelpScreen2(game,assets));
				return;
			}
		}
	}

	@Override
	public void pause() {
		//  Auto-generated method stub
		
	}

	@Override
	public void resume() {
		//  Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//  Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
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
	
}
