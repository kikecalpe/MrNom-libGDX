package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Settings;

public class MainMenuScreen extends SerpScreen {
	float vwidth = 320f; // v from virtual
	float vheight = 480f;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	float width, height = 0;
	
	SpriteBatch spriteBatch;
	//InputAdapter input; // not necessary at the moment
	
	Texture background;
	Texture logo;
	Texture mainMenu;
	Texture buttons;
	Sound click;
	Preferences settings;
	
	public MainMenuScreen(Game game) {
		super(game);
		Gdx.app.error("MainMenuScreen", "Constructor: super(game) job done!");
		Gdx.app.log("MainMenuScreen", "Constructor: going forward constructing...");
		Gdx.app.log("MainMenuScreen", "MainMenuScreen(game), this.assets: "+ this.assets);
		
		spriteBatch = new SpriteBatch();
		
		//input = new InputAdapter(); //not necessary at the moment
		
		// Using static assets 
		background = Assets.background;
		logo = Assets.logo;
		mainMenu = Assets.mainMenu;
		buttons = Assets.buttons;
		click = Assets.click;
		
		settings = Settings.serpSettings;
		
		Gdx.app.log("MainMenuScreen", "Constructor: job done!");
	}
	public MainMenuScreen(Game game, AssetManager assets) {
		super(game,assets);
		Gdx.app.error("MainMenuScreen", "Constructor: super(game,assets) job done!");
		Gdx.app.log("MainMenuScreen", "Constructor: going forward constructing...");
		Gdx.app.log("MainMenuScreen", "MainMenuScreen(game, assets), this.assets: "+ this.assets);
		Gdx.app.log("MainMenuScreen", "MainMenuScreen(game, assets), assets: "+ assets);
		
		spriteBatch = new SpriteBatch();
		//this line to setup projection without cameras
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, 320, 480);
		
		//input = new InputAdapter(); //not necessary at the moment
		
		// Using an AssetManager
		background = assets.get("background.png", Texture.class);
		logo = assets.get("logo.png", Texture.class);
		mainMenu = assets.get("mainmenu.png", Texture.class);
		buttons = assets.get("buttons.png", Texture.class);
		click = assets.get("click.ogg", Sound.class);
		
		settings = Settings.serpSettings;
		
		Gdx.app.log("MainMenuScreen", "Constructor: job done!");
	}

	@Override
	public void render(float delta) { 
		Gdx.app.log("MainMenuScreen", "starting render()");

		Gdx.app.log("MainMenuScreen", "render(), processInput()");
		inputController();
	    
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

	    Gdx.app.log("MainMenuScreen", "render(), spriteBatch.begin()");
		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0, width, height); //expand the background in XXL displays
		// 0,0 is bottom-left so to anchor upper: height - 20*ppuY - logo.getHeight()*ppuY
		spriteBatch.draw(logo, 32*ppuX, height - 20*ppuY - logo.getHeight()*ppuY,
				256*ppuX, 160*ppuY); 
		spriteBatch.draw(mainMenu, 64*ppuX, 132*ppuY,192*ppuX, 128*ppuY); // -220 - 128 = (menu height)
		if (settings.getBoolean("soundOn"))
			spriteBatch.draw(buttons, 0, 0, 64*ppuX, 64*ppuY,
					0, 0, 64, 64, false, false);
		else
			spriteBatch.draw(buttons, 0, 0, 64*ppuX, 64*ppuY, 
					64, 0, 64, 64, false, false);
		spriteBatch.end();
		Gdx.app.log("MainMenuScreen", "ended render()");
	}

	private void inputController(){
		Gdx.app.log("MainMenuScreen", "inputController(), starting to process input");

		if (Gdx.input.justTouched()){
			Gdx.app.log("MainMenuScreen", "inputController(), touched!");
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			
			Gdx.app.log("MainMenuScreen", "inputController(), touchPos = " +
					touchPos.toString());
			if (inBounds(touchPos, 0, 416*ppuY, 64*ppuX, 64*ppuY)){
				Gdx.app.log("MainMenuScreen", "inputController(),  sound button pressed...");
				if (!settings.getBoolean("soundOn")){
					Gdx.app.log("MainMenuScreen", "inputController(), ... sound enabled");
					settings.putBoolean("soundOn", true);
					settings.flush();
					click.play(1);
					return;
				} else {
					Gdx.app.log("MainMenuScreen", "inputController(), ...sound disabled");
					settings.putBoolean("soundOn",false);
					settings.flush();
					return;
				}
			}
			if (inBounds(touchPos, 64*ppuX, 220*ppuY, 192*ppuX, 42*ppuY)) {
				Gdx.app.log("MainMenuScreen", "inputController(), play touched!");
				if (settings.getBoolean("soundOn"))
					click.play(1);
				Gdx.app.log("MainMenuScreen", "inputController(), going to GameScreen");
				if (this.assets == null)
					game.setScreen(new GameScreen(game));
				if (this.assets != null)
					game.setScreen(new GameScreen(game,assets));
				return;
			}
			if (inBounds(touchPos, 64*ppuX, 262*ppuY, 192*ppuX, 42*ppuY)) { // y = 220 + 42 = 262
				Gdx.app.log("MainMenuScreen", "inputController(), highscores touched!");
				if (settings.getBoolean("soundOn"))
					click.play(1);
				Gdx.app.log("MainMenuScreen", "inputController(), going to HighscoreScreen");
				if (this.assets == null)
					game.setScreen(new HighscoreScreen(game));
				if (this.assets != null)
					game.setScreen(new HighscoreScreen(game,assets));
				return;
			}
			if (inBounds(touchPos, 64*ppuX, 304*ppuY, 192*ppuX, 42*ppuY)) { // y = 262 + 42 =304
				Gdx.app.log("MainMenuScreen", "inputController(), help touched!");
				if (settings.getBoolean("soundOn"))
					click.play(1);
				Gdx.app.log("MainMenuScreen", "inputController(), going to HelpScreen");
				if (this.assets == null)
					game.setScreen(new HelpScreen(game));
				if (this.assets != null)
					game.setScreen(new HelpScreen(game,assets));
				return;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
			Gdx.app.log("MainMenuScreen", "inputController(), ANY_KEY pressed");
			return;
		}
		Gdx.app.log("MainMenuScreen", "inputController(), ended");
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("MainMenuScreen", "resize()ing");
		this.width = width;
		this.height = height;
		//this.ratio = this.width/this.height;
		ppuX = (float)width / vwidth;
		Gdx.app.log("Stress", "resize().ppuX: "+ppuX);
		ppuY = (float)height / vheight;
		Gdx.app.log("Stress", "resize().ppuY: "+ppuY);
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

	    Gdx.app.log("MainMenuScreen", "ended resize()");
	}
	@Override
	public void dispose() {
		Gdx.app.log("MainMenuScreen", "dispose()ing");
		spriteBatch.dispose();
		assets.dispose();
		Gdx.app.log("MainMenuScreen", "ended dispose()");
	}
	public void drawText(String line, int x, int y){
		/*
		 * Usar siempre entre spriteBatch.begin() y end()
		 */
		Gdx.app.log("MainMenuScreen", "starting drawText(), will get numbers.png now");
	}

	@Override
	public void show() {
		// Auto-generated method stub
		Gdx.app.log("MainMenuScreen", "show()ing");
	}
	@Override
	public void hide() {
		// Auto-generated method stub
		Gdx.app.log("MainMenuScreen", "hide()ing");
	}
	@Override
	public void pause() {
		// Auto-generated method stub
		Gdx.app.log("MainMenuScreen", "pause()ing");
	}
	@Override
	public void resume() {
		// Auto-generated method stub
		Gdx.app.log("MainMenuScreen", "resume()ing");
	}
}
