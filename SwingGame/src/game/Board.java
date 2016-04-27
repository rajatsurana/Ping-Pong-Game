package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Paddle paddle;
	private Com com;
	private Ball ball;
	private String winner;
	private boolean ingame;
	private final int DELAY = 10;
	private final int BOARD_WIDTH = 373;
	private final int BOARD_HEIGHT = 245;

	public Board() {

		initBoard();
	}

	private void initBoard() {

		addKeyListener(new TAdapter());

		setFocusable(true);
		setBackground(Color.BLACK);

		paddle = new Paddle();
		com = new Com();
		ball = new Ball();
		ingame = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (ingame) {
			doDrawing(g);
		} else {
			drawGameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {

		// Graphics2D g2d = (Graphics2D) g;
		// g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

		g.setColor(Color.RED);
		g.fillRect(paddle.getX(), paddle.getY(), 150, 10);
		g.setColor(Color.RED);
		g.fillRect(com.getX(), com.getY(), 150, 10);
		g.setColor(Color.blue);
		g.fillOval(ball.getX(), ball.getY(), 25, 25);
		g.drawString("Lives: " + paddle.getLives(), 5, 245);
		g.drawString("Lives: " + com.getLives(), 5, 15);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		paddle.move();
		updatecom();
		checkCollisions();
		ball.move();
		checkGame();
		repaint();
	}

	private void checkGame() {
		if (com.getLives() == 0) {
			winner = "Player";
			ingame = false;

		}
		if (paddle.getLives() == 0) {
			winner = "Computer";
			ingame = false;
			
		}

	}

	private void inGame() {

		if (!ingame) {
			timer.stop();
		}
	}

	private void updatecom() {
		if (ball.getVX() > 0) {
			if (ball.getX() + 25 > com.getX() + 75)
				com.moveRight();
		} else {
			if (ball.getX() + 25 < com.getX() + 75)
				com.moveLeft();
		}
	}

	private void drawGameOver(Graphics g) {

		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 36);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (BOARD_WIDTH - fm.stringWidth(msg)) / 2,
				BOARD_HEIGHT / 2-25);
		g.drawString(winner + " wins", (BOARD_WIDTH - fm.stringWidth(msg)) / 2,
				BOARD_HEIGHT / 2 + 25);
	}

	private void checkCollisions() {

		Rectangle r3 = paddle.getBounds();
		Rectangle r2 = ball.getBounds();
		Rectangle r1 = com.getBounds();
		if (r2.intersects(r3)) {
			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velY;
			ball.setVel(velX, temp);
		}
		if (r2.intersects(r1)) {
			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velY;
			ball.setVel(velX, temp);
		}
		if (ball.getX() > BOARD_WIDTH || ball.getX() < 0) {
			int temp = -1 * ball.getVX();

			ball.setVel(temp, ball.getVY());

		}
		if (ball.getY() > BOARD_HEIGHT || ball.getY() < 0) {
			int temp = -1 * ball.getVY();
			ball.setVel(ball.getVX(), temp);
			if (ball.getY() < 0) {
				com.reduLives();
			}
			if (ball.getY() > BOARD_HEIGHT) {
				paddle.reduLives();
			}
		}
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);
		}
	}
}
