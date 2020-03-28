package snake;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameApp {
	private static JFrame frame;
	
	private GameApp() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("SNAKE GAME CHALLENGE");
		
		start();
	}
	
	public static void start() {
		System.out.println("CHANGED");
		SnakeScreen snakeScreen = new SnakeScreen();
		frame.setContentPane(snakeScreen);
		frame.validate();
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		snakeScreen.requestFocusInWindow();
	}
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameApp();
			}
		});
	}
}