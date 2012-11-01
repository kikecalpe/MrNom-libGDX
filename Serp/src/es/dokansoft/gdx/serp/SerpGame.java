package es.dokansoft.gdx.serp;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

import es.dokansoft.gdx.serp.view.LoadingScreen;

public class SerpGame extends Game {

	AssetManager assets;

	@Override
	public void create() {
	    Gdx.app.setLogLevel(Application.LOG_ERROR);
		Gdx.app.error("SerpGame", "create(), setLogLevel(LOG_error)");
		Gdx.app.log("SerpGame", "create(), going to LoadingScreen");
		setScreen(new LoadingScreen(this,assets));
		Gdx.app.log("SerpGame", "ended create()");
	}

    
}
