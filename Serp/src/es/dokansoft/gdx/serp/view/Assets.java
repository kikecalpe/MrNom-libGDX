package es.dokansoft.gdx.serp.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	public static Texture background = new Texture(Gdx.files.internal("background.png"));
	public static Texture logo = new Texture(Gdx.files.internal("logo.png"));
    public static Texture mainMenu = new Texture(Gdx.files.internal("mainmenu.png"));
    public static Texture buttons = new Texture(Gdx.files.internal("buttons.png"));
    public static Texture help1 = new Texture(Gdx.files.internal("help1.png"));
    public static Texture help2 = new Texture(Gdx.files.internal("help2.png"));
    public static Texture help3 = new Texture(Gdx.files.internal("help3.png"));
    public static Texture numbers = new Texture(Gdx.files.internal("numbers.png"));
    public static Texture ready = new Texture(Gdx.files.internal("ready.png"));
    public static Texture pause = new Texture(Gdx.files.internal("pausemenu.png"));
    public static Texture gameOver = new Texture(Gdx.files.internal("gameover.png"));
    public static Texture headUp = new Texture(Gdx.files.internal("headup.png"));
    public static Texture headLeft = new Texture(Gdx.files.internal("headleft.png"));
    public static Texture headDown = new Texture(Gdx.files.internal("headdown.png"));
    public static Texture headRight = new Texture(Gdx.files.internal("headright.png"));
    public static Texture tail = new Texture(Gdx.files.internal("tail.png"));
    public static Texture stain1 = new Texture(Gdx.files.internal("stain1.png"));
    public static Texture stain2 = new Texture(Gdx.files.internal("stain2.png"));
    public static Texture stain3 = new Texture(Gdx.files.internal("stain3.png"));
    
    public static Sound click = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
    public static Sound eat = Gdx.audio.newSound(Gdx.files.internal("eat.ogg"));
    public static Sound bitten = Gdx.audio.newSound(Gdx.files.internal("bitten.ogg"));
}
