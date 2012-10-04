package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.dokansoft.gdx.serp.model.Settings;

public class MenuScreen extends SerpScreen {
	
	SpriteBatch spritebatch;
	Preferences settings = Settings.settings;

	public MenuScreen(Game game) {
		super(game);
		
		Gdx.app.log("MainMenuScreen", "Constructor: super() job done!");
		Gdx.app.log("MainMenuScreen", "Constructor: going forward constructing...");
		
		this.game = game;
		spritebatch = new SpriteBatch();
		//this line to setup projection without cameras
		spritebatch.getProjectionMatrix().setToOrtho2D(0, 0, 320, 480);
		/* To work with cameras
		camera = new OrthographicCamera(320, 480);
		camera.setToOrtho(false, 320, 480);
		camera.update();*/
		
		Gdx.app.log("MainMenuScreen", "Constructor: job done!");
	}

	@Override
	public void render(float delta) { 
		Gdx.app.log("MainMenuScreen", "starting render()");

		//Gdx.app.log("MainMenuScreen", "render(), processInput()");
		//processInput();

		/* To work with cameras
		//camera.update();
		//spritebatch.setProjectionMatrix(camera.combined);*/
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spritebatch.begin();
		spritebatch.draw(Assets.background, 0, 0);
		spritebatch.draw(Assets.logo, 32, 300);// 0,0 is bottom-left so...
		spritebatch.draw(Assets.mainMenu, 64, 132);// 480 -220 - 128 (menu height)
		if (settings.getBoolean("soundOn"))
			spritebatch.draw(Assets.buttons, 0, 0, 0, 0, 64, 64);
		else
			spritebatch.draw(Assets.buttons, 0, 0, 64, 0, 64, 64);
		spritebatch.end();

		Gdx.app.log("MainMenuScreen", "ended render()");
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("MainMenuScreen", "resize()ing");
		// not necessary
		//Gdx.gl.glViewport(0,0,width, height);
	    Gdx.app.log("MainMenuScreen", "ended resize()");
	}
	@Override
	public void dispose() {
		Gdx.app.log("MainMenuScreen", "dispose()ing");
		assets.dispose();
		Gdx.app.log("MainMenuScreen", "ended dispose()");
	}
	
	public void drawText(String line, int x, int y){
		Gdx.app.log("MainMenuScreen", "drawText()");
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
