package es.dokansoft.gdx.serp;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import es.dokansoft.gdx.serp.view.LoadingScreen;

public class SerpGame extends Game {


	@Override
	public void create() {
	    Gdx.app.setLogLevel(Application.LOG_ERROR);
		Gdx.app.error("SerpGame", "create(), setLogLevel(LOG_error)");
		Gdx.app.log("SerpGame", "create(), going to new MenuScreen");
		setScreen(new LoadingScreen(this));
		Gdx.app.log("SerpGame", "ended create()");
	}

    
}
