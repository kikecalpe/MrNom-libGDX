package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import es.dokansoft.gdx.serp.model.Settings;

public class LoadingScreen extends SerpScreen {

	Preferences settings = Settings.serpSettings;
	Preferences highscores = Settings.serpHighscores;
	
	public LoadingScreen(Game game) {
		super(game);
		Gdx.app.log("LoadingScreen", "Constructor: job done!");

		this.game = game;
		loadGameAssets();
		loadSettings();
		loadHighscores();

	}
	public LoadingScreen(Game game, AssetManager assets){
		super(game, assets);
		Gdx.app.log("LoadingScreen", "Constructor: job done!");
		Gdx.app.log("LoadingScreen", "LoadingScreen(game, assets), this.assets: "+ this.assets);
		this.assets = new AssetManager();
		Gdx.app.log("LoadingScreen", "LoadingScreen(game, assets), this.assets: "+ this.assets);
		loadAssetManager();
		Gdx.app.log("LoadingScreen", "LoadingScreen(game, assets), assets: "+ assets);
		loadSettings();
		loadHighscores();
	}
	
	/*
	 * two way assets loading
	 */
	public void loadGameAssets() {
		Assets.background = new Texture(Gdx.files.internal("background.png"));
		Assets.logo = new Texture(Gdx.files.internal("logo.png"));
		Assets.mainMenu = new Texture(Gdx.files.internal("mainmenu.png"));
		Assets.buttons = new Texture(Gdx.files.internal("buttons.png"));
		Assets.help1 = new Texture(Gdx.files.internal("help1.png"));
		Assets.help2 = new Texture(Gdx.files.internal("help2.png"));
		Assets.help3 = new Texture(Gdx.files.internal("help3.png"));
		Assets.numbers = new Texture(Gdx.files.internal("numbers.png"));
		Assets.ready = new Texture(Gdx.files.internal("ready.png"));
		Assets.pause = new Texture(Gdx.files.internal("pausemenu.png"));
		Assets.gameOver = new Texture(Gdx.files.internal("gameover.png"));
		Assets.headUp = new Texture(Gdx.files.internal("headup.png"));
		Assets.headLeft = new Texture(Gdx.files.internal("headleft.png"));
		Assets.headDown = new Texture(Gdx.files.internal("headdown.png"));
		Assets.headRight = new Texture(Gdx.files.internal("headright.png"));
		Assets.tail = new Texture(Gdx.files.internal("tail.png"));
		Assets.stain1 = new Texture(Gdx.files.internal("stain1.png"));
		Assets.stain2 = new Texture(Gdx.files.internal("stain2.png"));
		Assets.stain3 = new Texture(Gdx.files.internal("stain3.png"));
	    
		Assets.click = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
		Assets.eat = Gdx.audio.newSound(Gdx.files.internal("eat.ogg"));
		Assets.bitten = Gdx.audio.newSound(Gdx.files.internal("bitten.ogg"));
    }
	private void loadAssetManager() {
		// Using assets as an AssetManager
		Gdx.app.log("LoadingScreen", "starting loadGameAssets()");
		assets.load("background.png", Texture.class);
		assets.load("logo.png", Texture.class);
		assets.load("mainmenu.png", Texture.class);
		assets.load("buttons.png", Texture.class);
		assets.load("help1.png", Texture.class);
		assets.load("help2.png", Texture.class);
		assets.load("help3.png", Texture.class);
		assets.load("numbers.png", Texture.class);
		assets.load("ready.png", Texture.class);
		assets.load("pausemenu.png", Texture.class);
		assets.load("gameover.png", Texture.class);
		assets.load("headup.png", Texture.class);
		assets.load("headleft.png", Texture.class);
		assets.load("headdown.png", Texture.class);
		assets.load("headright.png", Texture.class);
		assets.load("tail.png", Texture.class);
		assets.load("stain1.png", Texture.class);
		assets.load("stain2.png", Texture.class);
		assets.load("stain3.png", Texture.class);

		assets.load("click.ogg", Sound.class);
		assets.load("eat.ogg", Sound.class);
		assets.load("bitten.ogg", Sound.class);
		
		assets.finishLoading();
		Gdx.app.log("LoadingScreen", "ended loadGameAssets()");
	}

	/*
	 * Load Settings and Highscores
	 */
	public void loadSettings(){
		Gdx.app.log("LoadingScreen", "loadSettings(), soundOn: "+
				settings.getBoolean("soundOn"));
		settings.getBoolean("soundOn", true);
		settings.flush();
	}
	public void loadHighscores(){
		Gdx.app.log("LoadingScreen", "loadHighscores(), serpHighscore.0: "+
				highscores.getInteger(""+0));
		if (highscores.getInteger(""+0) == 0){
			int[] hs = { 100, 80, 50, 30, 10 };
			
			for (int i = 0; i< hs.length; i++){
				highscores.putInteger(""+i, hs[i]);
			}
			highscores.flush();
		}
	}
	/*
	 * Screen methods
	 */
	@Override
	public void render(float delta) {
		Gdx.app.log("LoadingScreen", "starting render()");
		Gdx.app.log("LoadingScreen", "render(), this.assets: "+ this.assets);
		Gdx.app.log("LoadingScreen", "render(), assets: "+ assets);

		if (assets == null)
			game.setScreen(new MainMenuScreen(game));
		if (assets != null)
			game.setScreen(new MainMenuScreen(game, assets));
		
		Gdx.app.log("LoadingScreen", "ended render()");
	}
	@Override
	public void resize(int width, int height) {
		Gdx.app.log("LoadingScreen", "resize()ing");
	}
	public void show(){
		Gdx.app.log("LoadingScreen", "show()ing");
		
		//Settings.load(game.getFileIO());
		Gdx.app.log("LoadingScreen", "finished show()ing");
	}
	@Override
	public void hide() {
		Gdx.app.log("LoadingScreen", "hide()ing");
	}
	@Override
	public void pause() {
		Gdx.app.log("LoadingScreen", "pause()ing");
	}
	@Override
	public void resume() {
		Gdx.app.log("LoadingScreen", "resume()ing");
	}
	@Override
	public void dispose() {
		Gdx.app.log("LoadingScreen", "dispose()ing");
		//assets.dispose();
	}
	@Override
	public void drawText(String line, int x, int y) {
		Gdx.app.log("LoadingScreen", "drawText()");
	}
}
