package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Settings;

public class HelpScreen3 extends SerpScreen {
	float vwidth = 320f; // v from virtual
	float vheight = 480f;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	float width, height = 0;

	SpriteBatch spriteBatch;

	Preferences settings;
		
	Texture background;
	Texture buttons;
	Texture help3;
	Sound click;

	public HelpScreen3(Game game) {
		super(game);
		Gdx.app.error("HelpScreen3", "Constructor: super(game) job done!");
		
		spriteBatch = new SpriteBatch();

		settings = Settings.serpSettings;
		
		background = Assets.background;
		buttons = Assets.buttons;
		help3 = Assets.help3;
		click = Assets.click;

	}
	public HelpScreen3(Game game, AssetManager assets) {
		super(game,assets);
		Gdx.app.error("HelpScreen3", "Constructor: super(game,assets) job done!");
		
		spriteBatch = new SpriteBatch();

		settings = Settings.serpSettings;
		
		background = assets.get("background.png", Texture.class);
		buttons = assets.get("buttons.png", Texture.class);
		help3 = assets.get("help3.png", Texture.class);
		click = assets.get("click.ogg", Sound.class);

	}

	@Override
	public void render(float delta) {
		inputController();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0, width, height); //expand the background in XXL displays
		spriteBatch.draw(help3, 64*ppuX, height - 100*ppuY - help3.getHeight()*ppuY,
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
				if (this.assets == null)
					game.setScreen(new MainMenuScreen(game));
				if (this.assets != null)
					game.setScreen(new MainMenuScreen(game,assets));
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
		
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
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