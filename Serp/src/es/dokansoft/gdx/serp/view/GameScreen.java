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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Settings;
import es.dokansoft.gdx.serp.model.Snake;
import es.dokansoft.gdx.serp.model.SnakePart;
import es.dokansoft.gdx.serp.model.Stain;
import es.dokansoft.gdx.serp.model.World;


public class GameScreen extends SerpScreen {
	float vwidth = 320f; // v from virtual
	float vheight = 480f;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	float width, height = 0;
	Matrix4 matrix;

	SpriteBatch spriteBatch;
	ShapeRenderer shaperenderer;

	Preferences settings;
	Preferences highscores;
	
	Texture background;
	Texture ready;
	Texture pause;
	Texture gameover;
	Texture numbers;
	Texture buttons;
	Texture headup;
	Texture headleft;
	Texture headdown;
	Texture headright;
	Texture tail;
	Texture stain1;
	Texture stain2;
	Texture stain3;
	
	Sound click;
	Sound eat;
	Sound bitten;
	
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

	public GameScreen(Game game){
		super(game);
		this.game = game;
		world = new World();
		
		spriteBatch = new SpriteBatch();
		shaperenderer = new ShapeRenderer();
		
		settings = Settings.settings;
		highscores = Settings.highscores;
		
		background = Assets.background;
		ready = Assets.ready;
		pause = Assets.pause;
		gameover = Assets.gameOver;
		numbers = Assets.numbers;
		buttons = Assets.buttons;
		headup = Assets.headUp;
		headleft = Assets.headLeft;
		headdown = Assets.headDown;
		headright = Assets.headRight;
		tail = Assets.tail;
		stain1 = Assets.stain1;
		stain2 = Assets.stain2;
		stain3 = Assets.stain3;
		
		click = Assets.click;
		eat = Assets.eat;
		bitten = Assets.bitten;

	}
	public GameScreen(Game game, AssetManager assets) {
		super(game,assets);
		world = new World();

		spriteBatch = new SpriteBatch();
		shaperenderer = new ShapeRenderer();
		
		settings = Settings.settings;
		highscores = Settings.highscores;
	}

	/*
	 * Screen methods
	 */
	@Override
	public void render(float delta) {
		Gdx.app.log("GameScreen", "render()ing");
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0, width, height);

		drawWorld(world);
		
		if (state == GameState.Ready){
			inputReady();
			drawReadyUI();
		}
		if (state == GameState.Running){
			inputRunning(delta);
			drawRunningUI();
		}
		if (state == GameState.Paused){
			inputPaused();
			drawPausedUI();
		}
		if (state == GameState.GameOver){
			inputGameOver();
			drawGameOverUI();
		}
		
		drawText(score, (int)width / 2 - score.length()*20 / 2, 10);
		spriteBatch.end();
		Gdx.app.log("GameScreen", "ended render()");
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
	public void dispose() {
		Gdx.app.log("GameScreen", "dispose()ing");
		spriteBatch.dispose();
	}
	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resize()ing");
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
	public void resume() {
		// Auto-generated method stub
		Gdx.app.log("GameScreen", "resume()ing");
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
		/*
		 * Usar siempre entre spriteBatch.begin() y end()
		 */
		Gdx.app.log("GameScreen", "drawText()");
        int len = line.length();
        
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
    
            if (character == ' ') {
                x += 20;
                continue;
            }
    
            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
    
            /*spriteBatch.draw(numbers, x*ppuX, y*ppuY, srcWidth/2, 16, srcWidth*ppuX, 32*ppuY, 
            		0, 0, 0, srcX, 0, srcWidth, 32, false, false);
            spriteBatch.draw(numbers, x, y, srcX, 0, srcWidth, 32);*/
            spriteBatch.draw(numbers, x*ppuX, y*ppuY, srcWidth*ppuX, 32*ppuY, 
            		srcX, 0, srcWidth, 32, false, false);
            x += srcWidth;
        }
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
	/*
	 * state.ready
	 */
	private void inputReady() {
		if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			state = GameState.Running;
	}
	private void drawReadyUI() {
				
		spriteBatch.draw(ready, width/2 - 112*ppuX, height/2 - 48*ppuY, 225*ppuX, 96*ppuY);
		shaperenderer.line(0, 52*ppuY, width, 52*ppuY);
	}
	/*
	 * state.Running
	 */
	private void inputRunning(float deltaTime) {
		
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
	private void drawRunningUI() {
				
		spriteBatch.draw(buttons, 0, height-64*ppuY, 64*ppuX, 64*ppuY,
				64, 128, 64, 64, false, false);
		shaperenderer.line(0, 52*ppuY, width, 52*ppuY);
		spriteBatch.draw(buttons, 0, 0, 64*ppuX, 64*ppuY,
				64, 64, 64, 64, false, false);
		spriteBatch.draw(buttons, width -64*ppuX, 0, 64*ppuX, 64*ppuY,
				0, 64, 64, 64, false, false);
	}
	/*
	 * state.paused
	 */
	private void inputPaused() {
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
	private void drawPausedUI() {
		
		spriteBatch.draw(pause, width/2 - 80*ppuX, height/2 - 48*ppuY, 225*ppuX, 96*ppuY);
		shaperenderer.line(0, 52*ppuY, width, 52*ppuY);
	}
	/*
	 * state.GameOver
	 */
	private void inputGameOver() {
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
	private void drawGameOverUI() {
		
		spriteBatch.draw(gameover, width/2 - 112, (height/3)*2 - 48, 196*ppuX, 50*ppuY);
		spriteBatch.draw(buttons, width/2 - 32*ppuX, height/3 -32*ppuY, 64*ppuX, 64*ppuY,
				0, 128, 64, 64, false, false);
		shaperenderer.line(0, 52*ppuY, width, 52*ppuY);
	}
	
}
