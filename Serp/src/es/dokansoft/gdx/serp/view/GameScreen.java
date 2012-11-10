package es.dokansoft.gdx.serp.view;

import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

import es.dokansoft.gdx.serp.model.Poo;
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
	Texture poo;
	
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
		Gdx.app.error("GameScreen", "Constructor: super(game) job done!");

		world = new World();
		
		spriteBatch = new SpriteBatch();
		
		shaperenderer = new ShapeRenderer();
		shaperenderer.setColor(Color.BLACK);
		
		settings = Settings.serpSettings;
		highscores = Settings.serpHighscores;
		
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
		poo = Assets.poo;
		
		click = Assets.click;
		eat = Assets.eat;
		bitten = Assets.bitten;

	}
	public GameScreen(Game game, AssetManager assets) {
		super(game,assets);
		Gdx.app.error("GameScreen", "Constructor: super(game,assets) job done!");

		world = new World();

		spriteBatch = new SpriteBatch();
		
		shaperenderer = new ShapeRenderer();
		shaperenderer.setColor(Color.BLACK);
		
		settings = Settings.serpSettings;
		highscores = Settings.serpHighscores;

		background = assets.get("background.png", Texture.class);
		ready = assets.get("ready.png", Texture.class);
		pause = assets.get("pausemenu.png", Texture.class);
		gameover = assets.get("gameover.png", Texture.class);
		numbers = assets.get("numbers.png", Texture.class);
		buttons = assets.get("buttons.png", Texture.class);
		headup = assets.get("headup.png", Texture.class);
		headleft = assets.get("headleft.png", Texture.class);
		headdown = assets.get("headdown.png", Texture.class);
		headright = assets.get("headright.png", Texture.class);
		tail = assets.get("tail.png", Texture.class);
		stain1 = assets.get("stain1.png", Texture.class);
		stain2 = assets.get("stain2.png", Texture.class);
		stain3 = assets.get("stain3.png", Texture.class);
		poo = assets.get("poo.png", Texture.class);
		
		click = assets.get("click.ogg", Sound.class);
		eat = assets.get("eat.ogg", Sound.class);
		bitten = assets.get("bitten.ogg", Sound.class);
	}

	/*
	 * Screen methods
	 */
	@Override
	public void render(float delta) {
		Gdx.app.log("GameScreen", "render()ing");
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
		drawText(score, ((int)vwidth / 2 - score.length()*10), 10);
		
		spriteBatch.end();
		shaperenderer.begin(ShapeType.Line);
		shaperenderer.line(0, 64*ppuY, width, 64*ppuY);
		shaperenderer.end();

		Gdx.app.log("GameScreen", "ended render()");
	}
	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause()ing");
		if (state == GameState.Running)
			state = GameState.Paused;
	}
	@Override
	public void dispose() {
		Gdx.app.log("GameScreen", "dispose()ing");
		spriteBatch.dispose();
		shaperenderer.dispose();
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
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

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
    
            spriteBatch.draw(numbers, x*ppuX, y*ppuY, srcWidth*ppuX, 32*ppuY, 
            		srcX, 0, srcWidth, 32, false, false);
            x += srcWidth;
        }
	}

	private void drawWorld(World world){
		
		Snake snake = world.getSnake();
		SnakePart head = snake.parts.get(0);
		Stain stain = world.getStain();
		List<Poo> poos = world.getPoos();
		
		int x,y;
		
		if (!poos.isEmpty()){
			for (Poo p: poos){
				x = p.x * 32;
				y = p.y * 32;
				spriteBatch.draw(poo, x*ppuX, y*ppuY, 32*ppuX, 32*ppuY);
			}
		}
		Texture stainPixmap = null;
		if (stain.type == Stain.TYPE_1)
			stainPixmap = stain1;
		if (stain.type == Stain.TYPE_2)
			stainPixmap = stain2;
		if (stain.type == Stain.TYPE_3)
			stainPixmap = stain3;
		x = stain.x * 32;
		y = stain.y * 32;
		spriteBatch.draw(stainPixmap, x*ppuX, y*ppuY, 32*ppuX, 32*ppuY);
		
		int len = snake.parts.size();
		for (int i = 1; i < len; i++){
			SnakePart part = snake.parts.get(i);
			x = part.x * 32;
			y = part.y * 32;
			spriteBatch.draw(tail, x*ppuX, y*ppuY, 32*ppuX, 32*ppuY);
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
		if (headPixmap == null)
			Gdx.app.error("GameScreen", "drawWorld(), headPixmap == null");
		spriteBatch.draw(headPixmap, x*ppuX - (headPixmap.getWidth() / 2)*ppuX, 
				y*ppuY - (headPixmap.getHeight() /2)*ppuY, 42*ppuX, 42*ppuY);
	}
	/*
	 * state.ready
	 */
	private void inputReady() {
		if (Gdx.input.justTouched())
			state = GameState.Running;
	}
	private void drawReadyUI() {
				
		spriteBatch.draw(ready, width/2 - 112*ppuX, height/2 - 48*ppuY, 225*ppuX, 96*ppuY);
	}
	/*
	 * state.Running
	 */
	private void inputRunning(float deltaTime) {
		Snake snake = world.getSnake();
		if ((Gdx.input.justTouched())){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (inBounds(touchPos, 0, 0, 64*ppuX, 64*ppuY)) {
				if (settings.getBoolean("soundOn"))
					click.play(1);
				state = GameState.Paused;
				return;
			} else if (inBounds(touchPos, 0, height-64*ppuY, 64*ppuX, 64*ppuY)){
				snake.turnLeft();
			} else if (inBounds(touchPos, width-64*ppuX, height-64*ppuY, 64*ppuX, 64*ppuY)){
				snake.turnRight();
			}
		}
			
		world.update(deltaTime);
		if (world.isGameOver()) {
			if (settings.getBoolean("soundOn"))
				bitten.play(1);
			state = GameState.GameOver;
			if (highscores.getInteger("4")< world.getScore())
				Settings.addScore(world.getScore());
		}
		if (oldScore != world.getScore()) {
			oldScore = world.getScore();
			score = "" + oldScore;
			if (settings.getBoolean("soundOn"))
				eat.play(1);
		}
	}
	private void drawRunningUI() {
		// pause button
		spriteBatch.draw(buttons, 0, height-64*ppuY, 64*ppuX, 64*ppuY,
				64, 128, 64, 64, false, false);
		// turn left button
		spriteBatch.draw(buttons, 0, 0, 64*ppuX, 64*ppuY,
				64, 64, 64, 64, false, false); 
		// turn right button
		spriteBatch.draw(buttons, width -64*ppuX, 0, 64*ppuX, 64*ppuY,
				0, 64, 64, 64, false, false);  
	}
	/*
	 * state.paused
	 */
	private void inputPaused() {
		if ((Gdx.input.justTouched())){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (inBounds(touchPos, width/2 - 80*ppuX, height/2 - 48*ppuY, 160*ppuX, 48*ppuY)) {
				if (settings.getBoolean("soundOn"))
					click.play(1);
				state = GameState.Running;
				return;
			} else if (inBounds(touchPos, width/2 - 80*ppuX, height/2, 160*ppuX, 48*ppuY)){
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
	private void drawPausedUI() {
		
		spriteBatch.draw(pause, width/2 - 80*ppuX, height/2 - 48*ppuY, 160*ppuX, 96*ppuY);
	}
	/*
	 * state.GameOver
	 */
	private void inputGameOver() {
		if ((Gdx.input.justTouched())){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (inBounds(touchPos, width/2 -32*ppuX, (height/3)*2 -32*ppuY, 64*ppuX, 64*ppuY)) {
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
	private void drawGameOverUI() {
		
		spriteBatch.draw(gameover, width/2 - 98*ppuX, (height/3)*2 - 25*ppuY, 196*ppuX, 50*ppuY);
		spriteBatch.draw(buttons, width/2 - 32*ppuX, height/3 -32*ppuY, 64*ppuX, 64*ppuY,
				0, 128, 64, 64, false, false);
	}
	
}
