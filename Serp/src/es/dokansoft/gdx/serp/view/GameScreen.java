package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Settings;
import es.dokansoft.gdx.serp.model.Snake;
import es.dokansoft.gdx.serp.model.SnakePart;
import es.dokansoft.gdx.serp.model.Stain;
import es.dokansoft.gdx.serp.model.World;


public class GameScreen extends SerpScreen {
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	private Pixmap pixmap;
	private Texture texture;
	Preferences settings = Settings.settings;
	Preferences highscores = Settings.highscores;
	
	Texture background = assets.get("background.png", Texture.class);
	Texture ready = assets.get("ready.png", Texture.class);
	Texture pause = assets.get("pause.png", Texture.class);
	Texture gameover = assets.get("gameover.png", Texture.class);
	Texture numbers = assets.get("numbers.png", Texture.class);
	Texture buttons = assets.get("buttons.png", Texture.class);
	Texture headup = assets.get("headup.png", Texture.class);
	Texture headleft = assets.get("headleft.png", Texture.class);
	Texture headdown = assets.get("headdown.png", Texture.class);
	Texture headright = assets.get("headright.png", Texture.class);
	Texture tail = assets.get("tail.png", Texture.class);
	Texture stain1 = assets.get("stain1.png", Texture.class);
	Texture stain2 = assets.get("stain2.png", Texture.class);
	Texture stain3 = assets.get("stain3.png", Texture.class);
	
	Sound click = assets.get("click.ogg", Sound.class);
	Sound eat = assets.get("eat.ogg", Sound.class);
	Sound bitten = assets.get("bitten.ogg", Sound.class);
	
	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}
	
	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";
	
	public GameScreen(Game game, AssetManager assets) {
		super(game,assets);
		world = new World();
		ShapeRenderer debugRenderer = new ShapeRenderer();
		// Create an empty dynamic pixmap
		pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				Pixmap.Format.RGBA8888); 
	      
		// Create a texture to contain the pixmap
		texture = new Texture(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				Pixmap.Format.RGBA8888);
	}

	public void update(float delta) {
				
		if (state == GameState.Ready)
			updateReady();
		if (state == GameState.Running)
			updateRunning(delta);
		if (state == GameState.Paused)
			updatePaused();
		if (state == GameState.GameOver)
			updateGameOver();
	}
	
	private void updateReady() {
		if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			state = GameState.Running;
	}
	
	private void updateRunning(float deltaTime) {
		
		if ((Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (inBounds(touchPos, 64, 64, 64, 64)) {
				if (settings.getBoolean("soundOn"))
					click.play(1);
				state = GameState.Paused;
				return;
			} else if (inBounds(touchPos, 64, 416, 64,64)){
				world.snake.turnLeft();
			} else if (inBounds(touchPos, 256, 416, 64,64)){
				world.snake.turnRight();
			}
		}
			
		world.update(deltaTime);
		if (world.gameOver) {
			if (settings.getBoolean("soundOn"))
				bitten.play(1);
			state = GameState.GameOver;
		}
		if (oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
			if (settings.getBoolean("soundOn"))
				eat.play(1);
		}
	}
	
	private void updatePaused() {
		if ((Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (inBounds(touchPos, 80, 100, 160, 48)) {
				if (settings.getBoolean("soundOn"))
					click.play(1);
				state = GameState.Running;
				return;
			} else if (inBounds(touchPos, 80, 149, 160, 48)){
				if (settings.getBoolean("soundOn"))
					click.play(1);
				game.setScreen(new MainMenuScreen(game, assets));
				return;
			}
		}
	}
	
	private void updateGameOver() {
		if ((Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (inBounds(touchPos, 128, 200, 64, 64)) {
				if (settings.getBoolean("soundOn"))
					click.play(1);
				game.setScreen(new MainMenuScreen(game, assets));
				return;
			}
		}
	}

	@Override
	public void render(float delta) {
		Gdx.app.log("GameScreen", "render()ing");
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0);
		drawWorld(world);
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
		
		texture.draw(pixmap, 0, 0);
		drawText(score, Gdx.graphics.getWidth() / 2 - score.length()*20 / 2,
				Gdx.graphics.getHeight() -42);
		spriteBatch.end();
		Gdx.app.log("GameScreen", "ended render()");
	}
	
	private void drawWorld(World world){
		
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		Stain stain = world.stain;
		
		Texture stainPixmap = null;
		if (stain.type == Stain.TYPE_1)
			stainPixmap = stain1;
		if (stain.type == Stain.TYPE_2)
			stainPixmap = stain2;
		if (stain.type == Stain.TYPE_3)
			stainPixmap = stain3;
		int x = stain.x * 32;
		int y = stain.y * 32;
		spriteBatch.draw(stainPixmap, x, y);
		
		int len = snake.parts.size();
		for (int i = 1; i < len; i++){
			SnakePart part = snake.parts.get(i);
			x = part.x * 32;
			y = part.y * 32;
			spriteBatch.draw(tail, x, y);
		}
		
		Texture headPixmap = null;
		if (snake.direction == Snake.UP)
			headPixmap = headup;
		if (snake.direction == Snake.LEFT)
			headPixmap = headleft;
		if (snake.direction == Snake.DOWN)
			headPixmap = headdown;
		if (snake.direction == Snake.RIGHT)
			headPixmap = headright;
		x = head.x * 32 + 16;
		y = head.y * 32 + 16;
		spriteBatch.draw(headPixmap, x - headPixmap.getWidth() / 2, 
				y - headPixmap.getHeight() /2);
	}
	
	private void drawReadyUI() {
				
		spriteBatch.draw(ready, 47, 100);
		pixmap.drawLine(0, 416, 480, 416);
	}
	
	private void drawRunningUI() {
				
		spriteBatch.draw(buttons, 0, 0, 64, 128, 64, 64);
		pixmap.drawLine(0, 416, 480, 416);
		spriteBatch.draw(buttons, 0, 416, 64, 64, 64, 64);
		spriteBatch.draw(buttons, 256, 416, 0, 64, 64, 64);
	}
	
	private void drawPausedUI() {
		
		spriteBatch.draw(pause, 80, 100);
		pixmap.drawLine(0, 416, 480, 416);
	}
	

	private void drawGameOverUI() {
		
		spriteBatch.draw(gameover, 62, 100);
		spriteBatch.draw(buttons, 128, 200, 0, 128, 64, 64);
		pixmap.drawLine(0, 416, 480, 416);
	}
	

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause()ing");
		if (state == GameState.Running)
			state = GameState.Paused;
		
		if (world.gameOver){
			Settings.addScore(world.score);
			
		}
	}

	@Override
	public void resume() {
		// Auto-generated method stub
		Gdx.app.log("GameScreen", "resume()ing");
	}

	@Override
	public void dispose() {
		// Auto-generated method stub
		Gdx.app.log("GameScreen", "dispose()ing");
	}

	@Override
	public void resize(int width, int height) {
		
		Gdx.app.log("GameScreen", "resize()ing");
		float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(2f * aspectRatio, 2f);
        Gdx.app.log("GameScreen", "going to camera.update()");
	    camera.update();
	    Gdx.app.log("GameScreen", "ended resize()");

	}

	@Override
	public void show() {
		// Auto-generated method stub
		Gdx.app.log("GameScreen", "show()ing");
	}

	@Override
	public void hide() {
		// Auto-generated method stub
		Gdx.app.log("GameScreen", "hide()ing");
	}

	@Override
	public void drawText(String line, int x, int y) {
		// Auto-generated method stub
		Gdx.app.log("GameScreen", "drawText()");
	}

}
