package es.dokansoft.gdx.serp.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
	public static Preferences settings = Gdx.app.getPreferences("serpSettings");
	public static Preferences highscores = Gdx.app.getPreferences("serpHighscores");

	public Settings(){
		//create settings
		settings.getBoolean("soundOn", true);
		settings.flush();
		
		// create highscores
		setHighscores();
		
	}
	
	public static void addScore(int score) {
		int k;
        for (int i = 0; i < 5; i++) {
            if (highscores.getInteger("" + i) < score) {
                for (int j = 4; j > i; j--){
                	k = j -1;
                    highscores.putInteger("" + j, highscores.getInteger("" + k));
                    highscores.putInteger("" + i, score);
                }
                break;
            }
        }
        highscores.flush();
    }
	private void setHighscores(){
		
		int[] hs = { 100, 80, 50, 30, 10 };
		for (int i: hs){
			hs[i] = highscores.getInteger("" + i, hs[i]);
		}
		highscores.flush();
	}
}