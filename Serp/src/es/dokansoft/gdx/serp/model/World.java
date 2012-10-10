package es.dokansoft.gdx.serp.model;

import java.util.Random;

public class World {
	static final int WORLD_WIDTH = 10;
	static final int WORLD_HEIGHT = 13;
	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = 0.4f;
	static final float TICK_DECREMENT = 0.15f;
	
	private Snake snake;
	private Stain stain;
	private boolean gameOver = false;
	private int score = 0;
	
	private boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
	private Random random = new Random();
	private float tickTime = 0;
	private static float tick;
	
	public World(){
		tick = TICK_INITIAL;
		snake = new Snake();
		placeStain();
	}
	
	public void placeStain(){
		for (int x=0; x < WORLD_WIDTH; x++){
			for (int y = 0; y < WORLD_HEIGHT; y++){
				fields[x][y] = false;
			}
		}
		
		int len = snake.parts.size();
		for (int i = 0; i < len; i++) {
			SnakePart part = snake.parts.get(i);
			fields[part.x][part.y] = true;
		}
		
		int stainX = random.nextInt(WORLD_WIDTH);
		int stainY = random.nextInt(WORLD_HEIGHT);
		while (true) {
			if (fields[stainX][stainY] == false)
				break;
			stainX +=1;
			if (stainX >= WORLD_WIDTH) {
				stainX = 0;
				stainY += 1;
				if (stainY >= WORLD_HEIGHT){
					stainY = 0;
				}
			}
		}
		stain = new Stain(stainX, stainY, random.nextInt(3));
	}
	
	public void update(float deltaTime) {
		if (gameOver) {
			return;
		}
		
		tickTime += deltaTime;
		
		while (tickTime > tick){
			tickTime -= tick;
			snake.advance();
			if (snake.checkBitten()) {
				gameOver = true;
				return;
			}
			SnakePart head = snake.parts.get(0);
			if (head.x == stain.x && head.y == stain.y){
				score += SCORE_INCREMENT;
				snake.eat();
				if (snake.parts.size() == WORLD_HEIGHT * WORLD_WIDTH) {
					gameOver = true;
					return;
				} else {
					placeStain();
				}
				
				if (score % 40 == 0 && tick - TICK_DECREMENT > 0) {
					tick -= TICK_DECREMENT;
				}
			}
		}
	}



	/**
	 * @return the snake
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * @param snake the snake to set
	 */
	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	/**
	 * @return the stain
	 */
	public Stain getStain() {
		return stain;
	}

	/**
	 * @param stain the stain to set
	 */
	public void setStain(Stain stain) {
		this.stain = stain;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the tick
	 */
	public static float getTick() {
		return tick;
	}

	/**
	 * @param tick the tick to set
	 */
	public static void setTick(float tick) {
		World.tick = tick;
	}
}
