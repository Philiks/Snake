package snake;

import static snake.SnakeScreen.SCREEN_HEIGHT;
import static snake.SnakeScreen.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	private final int SNAKE_DIM = SCREEN_WIDTH / 30;
	
	class Tail {
		int x;
		int y;
		public Tail(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private ArrayList<Tail> body;
	private int size = 1;
	private int velX, velY;

	private SnakeScreen snakeScreen;
	
	public Snake(SnakeScreen snakeScreen) {
		this.snakeScreen = snakeScreen;
		
		body = new ArrayList<>();
		body.add(new Tail(0,0));
		changeDirection(SnakeDirection.right);
	}
	
	public void changeDirection(SnakeDirection dir) {
		int velX, velY;
		
		switch(dir) {
		case up:
			velX = 0;
			velY = -1;
			break;
			
		case down:
			velX = 0;
			velY = 1;
			break;
			
		case left:
			velX = -1;
			velY = 0;
			break;
		case right:
			velX = 1;
			velY = 0;
			break;
			
		default:
			velX = 0;
			velY = 0;
			break;
		}
		
		this.velX = velX;
		this.velY = velY;
	}
	
	public void eat() {
		int x = body.get(size - 1).x;
		int y = body.get(size - 1).y;
		body.add(new Tail(x, y));
		size++;
	}
	
	public void dead() {
		snakeScreen.stop("DEAD");
	}
	
	public void draw(Graphics g) {
		//tail
		g.setColor(Color.CYAN);
		for(int i = 1; i < size; i++) 
			g.fillRect(body.get(i).x, body.get(i).y, SNAKE_DIM, SNAKE_DIM);
		
		//head
		g.setColor(Color.BLUE);
		g.fillRect(getPosX(), getPosY(), SNAKE_DIM, SNAKE_DIM);
	}
	
	public void update() {
		for(int i = size - 1; i > 0; i--) {
			body.get(i).x = body.get(i - 1).x;
			body.get(i).y = body.get(i - 1).y;
		}
		
		body.get(0).x += velX * SNAKE_DIM;
		body.get(0).y += velY * SNAKE_DIM;
		
		//hit boundary
		if(getPosX() == SCREEN_WIDTH || getPosX() == -SNAKE_DIM ||
		   getPosY() == SCREEN_HEIGHT || getPosY() == -SNAKE_DIM)
					dead();
		
		//hit body		
		for(int i = 1; i < size; i++)
			if(getPosX() == body.get(i).x && getPosY() == body.get(i).y)
				dead();
	}
	
	public int getPosX() {	return body.get(0).x;	}
	public int getPosY() {	return body.get(0).y;	}
}