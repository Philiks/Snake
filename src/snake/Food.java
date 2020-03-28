package snake;

import static snake.SnakeScreen.SCREEN_HEIGHT;
import static snake.SnakeScreen.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
	private final int FOOD_DIM = SCREEN_WIDTH / 30;
	private Random rand;
	
	private int posX, posY;
	
	public Food() {
		rand = new Random();
	}
	
	public void createFood() {
		posX = rand.nextInt(SCREEN_WIDTH / FOOD_DIM) * FOOD_DIM;
		posY = rand.nextInt(SCREEN_HEIGHT / FOOD_DIM) * FOOD_DIM;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(posX, posY, FOOD_DIM, FOOD_DIM);
	}
	
	public int getPosX() {	return posX;	}
	public int getPosY() {	return posY;	}
}