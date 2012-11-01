package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector3;

public abstract class SerpScreen implements Screen{
	
	Game game;
	AssetManager assets;
	
	
	public SerpScreen (Game game) {
		Gdx.app.log("SerpScreen", "starting constructor");
		this.game = game;
	    Gdx.app.log("SerpScreen", "constructor, job done!");
	}

	public SerpScreen (Game game, AssetManager assets){
		Gdx.app.log("SerpScreen", "starting constructor");
		this.game = game;
		this.assets = assets;
		Gdx.app.log("SerpScreen", "constructor, job done!");
	}

	@Override
	public abstract void resize(int width, int height);

	@Override
	public abstract void dispose();

	protected boolean inBounds( Vector3 touchPos, float x, float y, float width, float height) {
		Gdx.app.log("SerpScreen", "starting calculate inBounds()");
		if (touchPos.x > x && touchPos.x < x + width -1 &&
				touchPos.y > y && touchPos.y < y + height -1){
			Gdx.app.log("SerpScreen", "ended calculate inBounds() with true result");
			return true;
		} else{
			Gdx.app.log("SerpScreen", "ended calculate inBounds() with false result");
			return false;
		}
	}

	public abstract void drawText(String line, int x, int y);

	@Override
	public abstract void show();
	@Override
	public abstract void hide();
	@Override
	public abstract void pause();
	@Override
	public abstract void resume();
	@Override
	public abstract void render(float delta);
}
