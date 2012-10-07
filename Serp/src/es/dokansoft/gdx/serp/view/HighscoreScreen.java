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

public class HighscoreScreen extends SerpScreen {
	float vwidth = 320f; // v from virtual
	float vheight = 480f;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	float width, height = 0;
	Matrix4 matrix;

	Game game;
	SpriteBatch spriteBatch;

	String lines[] = new String[5];

	Preferences settings;
	Preferences highscores;
	
	Texture background;
	Texture mainMenu;
	Texture numbers;
	Texture buttons;

	Sound click;

	public HighscoreScreen(Game game) {
		super(game);
		
		this.game = game;
		
		spriteBatch = new SpriteBatch();

		settings = Settings.settings;
		highscores = Settings.highscores;
		
		background = Assets.background;
		mainMenu = Assets.mainMenu;
		numbers = Assets.numbers;
		buttons = Assets.buttons;
		click = Assets.click;
		
		setLines();

	}
	public HighscoreScreen(Game game, AssetManager assets) {
		super(game,assets);

		settings = Settings.settings;
		highscores = Settings.highscores;
		
		background = assets.get("background.png", Texture.class);
		numbers = assets.get("numbers.png", Texture.class);
		buttons = assets.get("buttons.png", Texture.class);

		click = assets.get("click.ogg", Sound.class);

		for (int i = 0; i<5; i++) {
			lines[i] = ""+ (i + 1) + ". " + highscores.getInteger("" +i);
		}
	}

	@Override
	public void render(float delta){
		inputController();

		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0, width, height); //expand the background in XXL displays
		spriteBatch.draw(mainMenu, 64*ppuX, height - 62*ppuY, 196*ppuX, 42*ppuY, 
				0, 42, 196, 42, false, false);
		spriteBatch.draw(buttons, 256*ppuX, 0, 64*ppuX, 64*ppuY,
				0, 64, 64, 64, false, false);
			
		int y = 120;
		for (int i = 0; i < 5; i++) {
			drawText(lines[i], 40, y);
			y += 60;
		}
			
		spriteBatch.end();
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
		/*
		 * Usar siempre entre spriteBatch.begin() y end()
		 */
		Gdx.app.log("HighscoreScreen", "drawText(), line.isEmpty(): "+line.isEmpty());
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
	public void inputController() {
		if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if (inBounds(touchPos, 0, 416*ppuY, 64*ppuX, 64*ppuY)){
				if (settings.getBoolean("soundOn"))
					click.play(1);
				game.setScreen(new HelpScreen2(game));
				return;
			}
		}
	}
	private void setLines(){
		int i;
		int[] hs = { 100, 80, 50, 30, 10 };
		for (i = 0; i<5; i++){
			lines[i] = ""+highscores.getInteger(""+i, hs[i]);
			Gdx.app.error("HighscoreScreen", "setLines(), lines[i]: "+lines[i]);
		}
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
	}
	@Override
	public void show() {
		//   Auto-generated method stub
		
	}
	@Override
	public void hide() {
		//   Auto-generated method stub
		
	}
	@Override
	public void pause() {
		//   Auto-generated method stub
		
	}
	@Override
	public void resume() {
		//   Auto-generated method stub
		
	}
}
