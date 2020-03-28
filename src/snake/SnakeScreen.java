package snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeScreen extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	
	public static final int SCREEN_WIDTH = 600, SCREEN_HEIGHT = 600;
	
	private Timer gameLoop;
	
	private int ticks = 200;
	
	private Snake snake;
	private SnakeDirection currentDir;

	private Food food;

	private boolean isPaused = false, isDead = false;
	
	public SnakeScreen() {
		setBackground(Color.BLACK);
		addKeyListener(this);
		
		currentDir = SnakeDirection.right;
		snake = new Snake(this);
		snake.changeDirection(currentDir);
		
		food = new Food();
		food.createFood();
		
		gameLoop = new Timer(ticks, this);
		gameLoop.setInitialDelay(0);
		start();
	}
	
	private void start() {
		gameLoop.start();
	}
	
	public void stop(String action) {
		System.out.println("dedo");
		gameLoop.stop();
		if(action.equalsIgnoreCase("DEAD"))
			isDead = true;
		else if(action.equalsIgnoreCase("PAUSE"))
			isPaused = true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		snake.draw(g);
		food.draw(g);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(snake.getPosX() == food.getPosX() &&
		   snake.getPosY() == food.getPosY()) {
			snake.eat();
			food.createFood();
		}	
		
		snake.update();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_UP:
			if(currentDir == SnakeDirection.up || currentDir == SnakeDirection.down)	return;
			else 	currentDir = SnakeDirection.up;
			break;
			
		case KeyEvent.VK_DOWN:
			if(currentDir == SnakeDirection.down || currentDir == SnakeDirection.up)	return;
			else	currentDir = SnakeDirection.down;
			break;
			
		case KeyEvent.VK_LEFT:
			if(currentDir == SnakeDirection.left || currentDir == SnakeDirection.right)	return;
			else	currentDir = SnakeDirection.left;
			break;
			
		case KeyEvent.VK_RIGHT:
			if(currentDir == SnakeDirection.right || currentDir == SnakeDirection.left)	return;
			else	currentDir = SnakeDirection.right;
			break;
			
		case KeyEvent.VK_P:
			stop("PAUSE");
			break;
		
		case KeyEvent.VK_S:
			if(!isPaused) return;
			gameLoop.start();
			isPaused = false;
			break;
		
		case KeyEvent.VK_R:
			if(!isPaused && !isDead) return;
			
			GameApp.start();
			break;
			
		default:
			return;
		}		
				
		snake.changeDirection(currentDir);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {	}
}