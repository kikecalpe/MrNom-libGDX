package es.dokansoft.gdx.serp.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Renderer {
	OrthographicCamera camera;
	SpriteBatch batch;
	
	public Renderer(){
		// create the camera and the SpriteBatch
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	      batch = new SpriteBatch();
	}

	public abstract void render();
	public abstract void dispose();
}
