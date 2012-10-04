package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import es.dokansoft.gdx.serp.model.Settings;

public class TestMenuScreen extends SerpScreen{
	static final int WIDTH = 480;
	static final int HEIGHT = 320;
	
	Rectangle glViewport;
	Mesh mesh;
	
	Game game;
	AssetManager assets;

	SpriteBatch spriteBatch;
	OrthographicCamera camera;
		
	Texture background;
	Texture logo;
	Texture mainMenu;
	Texture buttons;
	Sound click;
	Preferences settings;
	
	public TestMenuScreen(Game game){
		super(game);
		Gdx.app.log("TestMenuScreen", "Constructor: super() job done!");
		Gdx.app.log("TestMenuScreen", "Constructor: going forward constructing...");
		Gdx.app.log("TestMenuScreen", "Constructor: creating and setting mesh");
		mesh = new Mesh(true, 4, 6, new VertexAttribute(Usage.Position, 2, "a_pos"), new VertexAttribute(Usage.Color, 4, "a_col"));
		mesh.setVertices(new float[] {0, 0, 1, 0, 0, 1, WIDTH, 0, 0, 1, 0, 1, WIDTH, HEIGHT, 0, 0, 1, 1, 0, HEIGHT, 1, 0, 1, 1});
		mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
		
		Gdx.app.log("TestMenuScreen", "constructor: creating and setting camera");
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
		Gdx.app.log("TestMenuScreen", "constructor: setting glViewport");
		glViewport = calculateGLViewport(WIDTH, HEIGHT);

		Gdx.app.log("TestMenuScreen", "Constructor: job done!");
	}
	public TestMenuScreen(Game game, AssetManager assets) {
		super(game, assets);
		Gdx.app.log("TestMenuScreen", "Constructor: super() job done!");
		Gdx.app.log("TestMenuScreen", "Constructor: going forward constructing...");
		
		this.game = game;
		this.assets = assets;
		
		background = assets.get("background.png", Texture.class);
		logo = assets.get("logo.png", Texture.class);
		mainMenu = assets.get("mainmenu.png", Texture.class);
		buttons = assets.get("buttons.png", Texture.class);
		
		click = assets.get("click.ogg", Sound.class);
		
		settings = Settings.settings;	
		
		Gdx.app.log("TestMenuScreen", "Constructor: job done!");
	}
	
	private Rectangle calculateGLViewport (int desiredWidth, int desiredHeight) {
		Rectangle viewport = new Rectangle();
		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
			float aspect = (float)Gdx.graphics.getHeight() / desiredHeight;
			viewport.width = desiredWidth * aspect;
			viewport.height = Gdx.graphics.getHeight();
			viewport.x = Gdx.graphics.getWidth() / 2 - viewport.width / 2;
			viewport.y = 0;
		} else {
			float aspect = (float)Gdx.graphics.getWidth() / desiredWidth;
			viewport.width = Gdx.graphics.getWidth();
			viewport.height = desiredHeight * aspect;
			viewport.x = 0;
			viewport.y = Gdx.graphics.getHeight() / 2 - viewport.height / 2;
		}
		return viewport;
	}

	@Override
	public void render(float delta) {
		Gdx.app.log("TestMenuScreen", "render()ing");/*
	    if (camera != null){
	    	Gdx.app.log("TestMenuScreen", "camera != null");
	    	Gdx.app.log("TestMenuScreen", camera.toString());
	    } else {
	    	Gdx.app.log("TestMenuScreen", "camera == null WTF!!!");
	    	//camera = super.camera;
	    	Gdx.app.log("TestMenuScreen", camera.toString());
	    }*/
		GL10 gl = Gdx.gl10;
		Gdx.app.log("TestMenuScreen", "render(), going to glClear");
		Gdx.gl.glClearColor(0, 1, 0.2f, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gl.glViewport((int)glViewport.x, (int)glViewport.y, (int)glViewport.width, (int)glViewport.height);

	    Gdx.app.log("TestMenuScreen", "render(), going to camera.update()");
        camera.update();
	    Gdx.app.log("TestMenuScreen", "render(), going to camera.apply(gl)");
	    camera.apply(gl);
	    Gdx.app.log("TestMenuScreen", "render(), going to mesh.render()");
	    mesh.render(GL10.GL_TRIANGLES);
	    Gdx.app.log("TestMenuScreen", "ended render()");
	}

	@Override
	public void show() {
		Gdx.app.log("TestMenuScreen", "show()ing");


		//Gdx.app.log("TestMenuScreen", "ended show()");
	}
	
	@Override
	public void resize(int width, int height) {
		Gdx.app.log("TestMenuScreen", "resize()ing");
		/*
	    if (camera != null){
	    	Gdx.app.log("TestMenuScreen", "cam != null");
	    	Gdx.app.log("TestMenuScreen", camera.toString());
	    } else {
	    	Gdx.app.log("TestMenuScreen", "cam == null WTF!!!");
	    	Gdx.app.log("TestMenuScreen", camera.toString());
	    }*/

		Gdx.app.log("TestMenuScreen", "resize(): setting glViewport");
		glViewport = calculateGLViewport(WIDTH, HEIGHT);
		
		Gdx.app.log("TestMenuScreen", "ended resize()");
	}

	@Override
	public void dispose() {
		Gdx.app.log("TestMenuScreen", "dispose()ing");
		
	}

	@Override
	public void drawText(String line, int x, int y) {
		Gdx.app.log("TestMenuScreen", "drawText()");
		
	}

	@Override
	public void hide() {
		Gdx.app.log("TestMenuScreen", "hide()ing");
		
	}

	@Override
	public void pause() {
		Gdx.app.log("TestMenuScreen", "pause()ing");
		
	}

	@Override
	public void resume() {
		Gdx.app.log("TestMenuScreen", "resume()ing");
		
	}
}
