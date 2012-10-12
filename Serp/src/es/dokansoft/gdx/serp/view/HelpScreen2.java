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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Settings;

public class HelpScreen2 extends SerpScreen {
	float vwidth = 320f; // v from virtual
	float vheight = 480f;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	float width, height = 0;
	Matrix4 matrix;

	Game game;
	SpriteBatch spriteBatch;

	Preferences settings;
		
	Texture background;
	Texture buttons;
	Texture help2;
	Sound click;

	public HelpScreen2(Game game) {
		super(game);
		
		this.game = game;
		
		spriteBatch = new SpriteBatch();

		settings = Settings.settings;
		
		background = Assets.background;
		buttons = Assets.buttons;
		help2 = Assets.help2;
		click = Assets.click;

	}
	public HelpScreen2(Game game, AssetManager assets) {
		super(game,assets);
		
		settings = Settings.settings;
		
		background = assets.get("background.png", Texture.class);
		buttons = assets.get("buttons.png", Texture.class);
		help2 = assets.get("help2.png", Texture.class);
		click = assets.get("click.ogg", Sound.class);

	}

	@Override
	public void render(float delta) {
		inputController();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0, width, height); //expand the background in XXL displays
		spriteBatch.draw(help2, 64*ppuX, height - 100*ppuY - help2.getHeight()*ppuY,
				192*ppuX, 256*ppuY);
		spriteBatch.draw(buttons, 256*ppuX, 0, 64*ppuX, 64*ppuY,
				0, 64, 64, 64, false, false);
		spriteBatch.end();
	}
	
	public void inputController() {
		if (Gdx.input.justTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (inBounds(touchPos, 256*ppuX, 416*ppuY, 64*ppuX, 64*ppuY)){
				if (settings.getBoolean("soundOn"))
					click.play(1);
				game.setScreen(new HelpScreen3(game));
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
		spriteBatch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		//this.ratio = this.width/this.height;
		ppuX = (float)width / vwidth;
		Gdx.app.log("Stress", "resize().ppuX: "+ppuX);
		ppuY = (float)height / vheight;
		Gdx.app.log("Stress", "resize().ppuY: "+ppuY);
		matrix = spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		
	}

	@Override
	public void drawText(String line, int x, int y) {
		// Auto-generated method stub
		
	}

	@Override
	public void show() {
		// Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// Auto-generated method stub
		
	}
	
}
