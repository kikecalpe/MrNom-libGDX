package es.dokansoft.gdx.serp.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
	public static Preferences serpSettings = Gdx.app.getPreferences("serpSettings");
	public static Preferences serpHighscores = Gdx.app.getPreferences("serpHighscores");

	public static void addScore(int score) {
		int k;
        for (int i = 0; i < 5; i++) {
            if (serpHighscores.getInteger("" + i) < score) {
                for (int j = 4; j > i; j--){
                	k = j -1;
                	serpHighscores.putInteger("" + j, serpHighscores.getInteger("" + k));
                }
            	serpHighscores.putInteger("" + i, score);
                break;
            }
        }
        serpHighscores.flush();
		Gdx.app.log("settings", "addScore(), score: "+ score);
        for (int i = 0; i< 5; i++){
    		Gdx.app.log("settings", "serpHighscore["+i+"]: "+ serpHighscores.getInteger(""+i));
        }
    }
}