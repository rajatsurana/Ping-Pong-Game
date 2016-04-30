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
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.game.saurabh.Power;

public class Board2 extends JPanel implements ActionListener {

	private Timer timer;
	private Paddle paddle;
	private PaddleL paddlel;
	private PaddleR paddler;
	private int playercount;
	private Paddleup paddleup;
	// private Com com;
	private Ball ball;
	private int ball_width;
	private String winner;
	private Power power;
	private boolean ingame;
	private String hitby;
	// when paddels expanded
	private boolean paddleExpand;
	private long paddleExtime;
	private boolean paddlelExpand;
	private long paddlelExtime;
	private boolean paddlerExpand;
	private long paddlerExtime;
	private boolean paddleupExpand;
	private long paddleupExtime;

	// private boolean comExpand;
	// private long comExtime;
	private final int DELAY = 50;
	private final int BOARD_WIDTH_R = 600;
	private final int BOARD_WIDTH_L = 100;
	private final int BOARD_HEIGHT_U = 75;
	private final int BOARD_HEIGHT_D = 575;
	/*
	 * private int hole1x = 100; private int hole2x = 600; private int hole3x =
	 * 100; private int hole4x = 600; private int hole1y = 75; private int
	 * hole2y = 575; private int hole3y = 75; private int hole4y = 575;
	 */
	// private boolean pause;
	// private long pausetime;
	// JButton button;
	private Ball ball2;
	private boolean ball2_visi;
	private long balltimer;

	public Board2() {

		initBoard2();
	}

	private void initBoard2() {

		addKeyListener(new TAdapter());

		setFocusable(true);
		setBackground(Color.BLACK);

		paddle = new Paddle();
		paddlel = new PaddleL();
		paddler = new PaddleR();
		paddleup = new Paddleup();
		// com = new Com();
		ball = new Ball();
		ball_width = 8;
		ingame = true;
		// pause = true;
		power = new Power();
		// init paddle expand
		paddleExpand = false;
		paddleExtime = 0;
		paddlerExpand = false;
		paddlerExtime = 0;
		paddlelExpand = false;
		paddlelExtime = 0;
		paddleupExpand = false;
		paddleupExtime = 0;
		// pausetime = System.currentTimeMillis();
		// comExpand = false;
		// comExtime = 0;
		ball2_visi = false;
		hitby = "None";

		playercount = 4;

		timer = new Timer(DELAY, this);
		timer.start();
		// button = new JButton("Pause");

		// panel.add(single);

		// button.setBounds(600, 0, 50, 50);
		// add(button);
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

	/*
	 * @Override public void mouseClicked(MouseEvent e) {
	 * System.out.println("me"); pause = !pause; if(pause = true){ pausetime =
	 * System.currentTimeMillis(); }else{ pausetime = 0; } }
	 */

	private void doDrawing(Graphics g) {

		// Graphics2D g2d = (Graphics2D) g;
		// g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
		g.setColor(Color.white);
		g.drawRect(100, 75, 500, 500);
		g.setColor(Color.white);
		g.drawRect(150, 75, 400, 500);
		g.setColor(Color.white);
		g.drawRect(100, 125, 500, 400);

		drawPaddels(g);
		/*
		 * com player if (comExpand == false) { g.setColor(Color.yellow);
		 * g.fillRect(com.getX(), com.getY(), 150, 5); } else {
		 * g.setColor(Color.magenta); g.fillRect(com.getX(), com.getY(), 200,
		 * 5); }
		 */
		if (ball2_visi) {
			g.setColor(Color.blue);
			g.fillOval(ball2.getX(), ball2.getY(), ball_width, ball_width);
		}

		g.setColor(Color.blue);
		g.fillOval(ball.getX(), ball.getY(), ball_width, ball_width);

		drawLives(g);
		drawPowers(g);

	}

	private void drawPaddels(Graphics g) {
		if (paddle.getvis()) {
			if (paddleExpand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddle.getX(), paddle.getY(), 150, 5);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddle.getX(), paddle.getY(), 200, 5);
			}
		}
		if (paddler.getvis()) {
			if (paddlerExpand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddler.getX(), paddler.getY(), 5, 150);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddler.getX(), paddler.getY(), 5, 200);
			}
		}
		if (paddlel.getvis()) {
			if (paddlelExpand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddlel.getX(), paddlel.getY(), 5, 150);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddlel.getX(), paddlel.getY(), 5, 200);
			}
		}
		if (paddleup.getvis()) {
			if (paddleupExpand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddleup.getX(), paddleup.getY(), 150, 5);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddleup.getX(), paddleup.getY(), 200, 5);
			}
		}
	}

	private void drawLives(Graphics g) {
		Font small = new Font("Helvetica", Font.BOLD, 24);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString("Lives: " + paddle.getLives(), 325, 625);
		g.drawString("Lives: " + paddleup.getLives(), 325, 25);
		g.drawString("Lives: " + paddlel.getLives(), 0, 300);
		g.drawString("Lives: " + paddler.getLives(), 610, 300);
	}

	private void drawPowers(Graphics g) {
		if (power.getVisible() == true) {

			if (power.getId() == 0) {
				g.setColor(Color.white);
				g.fillRect(power.getX(), power.getY(), 25, 25);
			} else if (power.getId() == 1) {
				g.setColor(Color.green);
				g.fillRect(power.getX(), power.getY(), 25, 25);
			} else if (power.getId() == 2) {

				g.setColor(Color.red);
				g.fillRect(power.getX(), power.getY(), 25, 25);

			} else {
				g.setColor(Color.PINK);
				g.fillRect(power.getX(), power.getY(), 25, 25);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if (!pause) {

		inGame();
		if (paddle.getvis()) {
			paddle.move();
		}
		if (paddler.getvis()) {
			paddler.move();
		}
		if (paddlel.getvis()) {
			paddlel.move();
		}
		if (paddleup.getvis()) {
			paddleup.move();
		}
		// updatecom();
		// checkhole();
		checkCollisions();
		updatepower();
		checkpaddle();
		ball.move();
		if (ball2_visi) {
			ball2.move();
		}
		checkGame();
		repaint();
		// }

		// processPause();
	}

	/*
	 * private void processPause() { button.addMouseListener(new
	 * java.awt.event.MouseAdapter() { public void
	 * mousePressed(java.awt.event.MouseEvent evt) { // setContentPane(gp);
	 * System.out.println("hi" + pause); if(pause){ pause = false;
	 * 
	 * }else{ pause = true; } System.out.println("hi" + pause); if(pause =
	 * true){ pausetime = System.currentTimeMillis(); }else{ pausetime = 0; } }
	 * });
	 * 
	 * }
	 */

	/*
	 * private void checkhole() { int x = ball.getX(); int y = ball.getY(); if(x
	 * - hole1x <= 12 ){ if(y - hole1y <= 12){ ball = new Ball(); } } if( x-
	 * hole3x <= 12 ){ if( hole3y - y <= 12){ ball = new Ball(); } } if( hole2x
	 * - x <= 12 ){ if(y - hole2y <= 12){ ball = new Ball(); } } if( hole4x - x
	 * <= 12 ){ if( hole4y - y <= 12){ ball = new Ball(); } }
	 * 
	 * }
	 */

	private void checkpaddle() {
		if (paddleExpand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddleExtime) > 45000) {
				paddleExpand = false;
				paddle.setEx(false);
			}
		}
		if (paddleupExpand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddleupExtime) > 45000) {
				paddleupExpand = false;
				paddleup.setEx(false);
			}
		}
		if (paddlerExpand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddlerExtime) > 45000) {
				paddlerExpand = false;
				paddler.setEx(false);
			}
		}
		if (paddlelExpand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddlelExtime) > 45000) {
				paddlelExpand = false;
				paddlel.setEx(false);
			}
		}

	}

	private void updatepower() {
		long temp = System.currentTimeMillis();
		if (temp - (power.getStarttime()) >= 30000 && ball2_visi == false) {
			power = new Power();
		}
		if (ball2_visi) {
			if (temp - balltimer >= 10000) {
				ball2_visi = false;
			}
		}

	}

	private void checkGame() {
		if (paddleup.getvis()) {
			if (paddleup.getLives() == 0) {

				playercount--;
				//System.out.println("paddleup " + playercount);
				paddleup.setNvis();
				if (playercount == 1) {
					ingame = false;
				} else if (playercount == 1) {
					if (paddler.getLives() != 0) {
						winner = "Player1";
					} else if (paddlel.getLives() != 0) {
						winner = "Player3";
					} else if (paddle.getLives() != 0) {
						winner = "Player";
					}
				}

			}
		}
		if (paddle.getvis()) {
			if (paddle.getLives() == 0) {
				playercount -= 1;
				paddle.setNvis();
				//System.out.println("paddle " + playercount);
				if (playercount == 1) {
					ingame = false;

					if (paddler.getLives() != 0) {
						winner = "Player1";
					} else if (paddlel.getLives() != 0) {
						winner = "Player3";
					} else if (paddleup.getLives() != 0) {
						winner = "Player2";
					}
				}

			}
		}
		if (paddler.getvis()) {
			if (paddler.getLives() == 0) {
				playercount -= 1;
				paddler.setNvis();
				//System.out.println("paddler " + playercount);
				if (playercount == 1) {
					ingame = false;

					if (paddle.getLives() != 0) {
						winner = "Player";
					} else if (paddlel.getLives() != 0) {
						winner = "Player3";
					} else if (paddleup.getLives() != 0) {
						winner = "Player2";
					}
				}

			}
		}
		if (paddlel.getvis()) {
			if (paddlel.getLives() == 0) {
				playercount -= 1;
				paddlel.setNvis();
				//System.out.println("paddlel " + playercount);
				if (playercount == 1) {
					ingame = false;

					if (paddler.getLives() != 0) {
						winner = "Player1";
					} else if (paddle.getLives() != 0) {
						winner = "Player";
					} else if (paddleup.getLives() != 0) {
						winner = "Player2";
					}
				}

			}
		}
	}

	private void inGame() {

		if (!ingame) {
			timer.stop();
		}
	}

	/*
	 * private void updatecom() { if (ball.getVX() > 0) { if (ball.getX() + 25 >
	 * com.getX() + 75) com.moveRight(); } else { if (ball.getX() + 25 <
	 * com.getX() + 75) com.moveLeft(); } }
	 */

	private void drawGameOver(Graphics g) {
		System.out.println(playercount);
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 36);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (700 - fm.stringWidth(msg)) / 2, 700 / 2 - 100);
		g.drawString(winner + " wins", (700 - fm.stringWidth(msg)) / 2, 700 / 2);
	}

	private void checkCollisions() {

		Rectangle paddleRect = paddle.getBounds();
		Rectangle paddlelRect = paddlel.getBounds();
		Rectangle paddlerRect = paddler.getBounds();
		Rectangle paddleupRect = paddleup.getBounds();
		Rectangle ballRect = ball.getBounds();
		// Rectangle comRect = com.getBounds();
		Rectangle powerRect = power.getBounds();
		if (ball2_visi) {
			Rectangle ball2Rect = ball2.getBounds();
			if (ball2Rect.intersects(paddleRect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velY;
				ball2.setVel(velX, temp);

			}
			if (ball2Rect.intersects(paddlelRect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velX;
				ball2.setVel(temp, velY);

			}
			if (ball2Rect.intersects(paddlerRect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velX;
				ball2.setVel(temp, velY);

			}
			if (ball2Rect.intersects(paddleupRect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velY;
				ball2.setVel(velX, temp);

			}
			/*
			 * if (ball2Rect.intersects(comRect)) {
			 * 
			 * System.out.println("ball " + ball.getY() + " " + com.getY() + " "
			 * + (ball.getY() - com.getY())); int velX = ball2.getVX(); int velY
			 * = ball2.getVY(); int temp = -velY; ball2.setVel(velX, temp);
			 * 
			 * }
			 */
			if (ball2.getX() + ball_width > BOARD_WIDTH_R
					|| ball2.getX() < BOARD_WIDTH_L) {
				int temp = -1 * ball2.getVX();

				ball2.setVel(temp, ball2.getVY());

				if (ball2.getX() < BOARD_WIDTH_L) {
					if (ball2.getY() < 525 && ball2.getX() > 125) {
						if (paddlel.getvis()) {
							paddlel.reduLives();
						}
					}

				}
				if (ball2.getX() + ball_width > BOARD_WIDTH_R) {

					if (ball2.getY() < 525 && ball2.getY() > 125) {

						if (paddler.getvis()) {
							paddler.reduLives();
						}
					}

				}

			}
			if (ball2.getY() + ball_width > BOARD_HEIGHT_D
					|| ball2.getY() < BOARD_HEIGHT_U) {
				int temp = -1 * ball2.getVY();
				ball2.setVel(ball2.getVX(), temp);

				if (ball2.getY() < BOARD_HEIGHT_U) {
					if (ball2.getX() < 550 && ball2.getX() > 150) {
						if (paddleup.getvis()) {
							paddleup.reduLives();
						}
					}

				}
				if (ball2.getY() + ball_width > BOARD_HEIGHT_D) {

					if (ball2.getX() < 550 && ball2.getX() > 150) {

						if (paddle.getvis()) {
							paddle.reduLives();
						}
					}

				}
			}
			if (ball2Rect.intersects(ballRect)) {
				int temp1 = ball.getVX();
				int temp2 = ball.getVY();

				ball.setVel(ball2.getVX(), ball2.getVY());
				ball2.setVel(temp1, temp2);
			}
		}

		if (ballRect.intersects(paddleRect)) {

			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velY;
			ball.setVel(velX, temp);

			hitby = "Player";

			if (power.getHitted() == true) {
				if (power.getId() == 1) {

					ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
							ball.getVY() / Math.abs(ball.getVY()) * 4);

					power.setNHitted();
				}
			}

		}
		if (ballRect.intersects(paddleupRect)) {

			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velY;
			ball.setVel(velX, temp);

			hitby = "Player2";

			if (power.getHitted() == true) {
				if (power.getId() == 1) {

					ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
							ball.getVY() / Math.abs(ball.getVY()) * 4);

					power.setNHitted();
				}
			}

		}
		if (ballRect.intersects(paddlelRect)) {

			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velX;
			ball.setVel(temp, velY);

			hitby = "Player3";

			if (power.getHitted() == true) {
				if (power.getId() == 1) {

					ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
							ball.getVY() / Math.abs(ball.getVY()) * 4);

					power.setNHitted();
				}
			}

		}
		if (ballRect.intersects(paddlerRect)) {

			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velX;
			ball.setVel(temp, velY);

			hitby = "Player1";

			if (power.getHitted() == true) {
				if (power.getId() == 1) {

					ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
							ball.getVY() / Math.abs(ball.getVY()) * 4);

					power.setNHitted();
				}
			}

		}

		if (ball.getX() + ball_width > BOARD_WIDTH_R || ball.getX() < BOARD_WIDTH_L) {
			int temp = -1 * ball.getVX();

			ball.setVel(temp, ball.getVY());

			if (ball.getX() < BOARD_WIDTH_L) {
				if (ball.getY() < 525 && ball.getY() > 125) {
					if (paddlel.getvis()) {
						paddlel.reduLives();
					}
				}
				if (power.getHitted() == true) {
					if (power.getId() == 1) {
						ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
								ball.getVY() / Math.abs(ball.getVY()) * 4);
						power.setNHitted();
					}
				}
			}
			if (ball.getX() + ball_width > BOARD_WIDTH_R) {

				if (ball.getY() < 525 && ball.getY() > 125) {

					if (paddler.getvis()) {
						paddler.reduLives();
					}
				}
				if (power.getHitted() == true) {
					if (power.getId() == 1) {
						ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
								ball.getVY() / Math.abs(ball.getVY()) * 4);
						power.setNHitted();
					}
				}
			}

		}
		if (ball.getY() + ball_width > BOARD_HEIGHT_D || ball.getY() < BOARD_HEIGHT_U) {
			int temp = -1 * ball.getVY();
			ball.setVel(ball.getVX(), temp);

			if (ball.getY() < BOARD_HEIGHT_U) {
				if (ball.getX() < 550 && ball.getX() > 150) {
					if (paddleup.getvis()) {
						paddleup.reduLives();
					}
				}
				if (power.getHitted() == true) {
					if (power.getId() == 1) {
						ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
								ball.getVY() / Math.abs(ball.getVY()) * 4);
						power.setNHitted();
					}
				}
			}
			if (ball.getY() + ball_width > BOARD_HEIGHT_D) {

				if (ball.getX() < 550 && ball.getX() > 150) {

					if (paddle.getvis()) {
						paddle.reduLives();
					}
				}
				if (power.getHitted() == true) {
					if (power.getId() == 1) {
						ball.setVel(ball.getVX() / Math.abs(ball.getVX()) * 3,
								ball.getVY() / Math.abs(ball.getVY()) * 4);
						power.setNHitted();
					}
				}
			}
		}
		if (power.getVisible() == true) {
			if (hitby.equals("Player") || hitby.equals("Player1")
					|| hitby.equals("Player2") || hitby.equals("Player3")) {
				if (ballRect.intersects(powerRect)) {

					power.setHitted();
					power.setNotVisible();

					int tempid = power.getId();
					if (tempid == 0) {
						if (hitby.equals("Player")) {
							ball = new Ball();
							if (paddle.getvis()) {
								paddle.reduLives();
							}
						} else if (hitby.equals("Player2")) {
							ball = new Ball();
							if (paddleup.getvis()) {
								paddleup.reduLives();
							}
						} else if (hitby.equals("Player1")) {
							ball = new Ball();
							if (paddler.getvis()) {
								paddler.reduLives();
							}
						} else if (hitby.equals("Player3")) {
							ball = new Ball();
							if (paddlel.getvis()) {
								paddlel.reduLives();
							}
						} else {

						}
					} else if (tempid == 1) {
						int tempvx = ball.getVX();
						int tempvy = ball.getVY();

						ball.setVel(2 * tempvx, 2 * tempvy);
					} else if (tempid == 2) {
						if (hitby.equals("Player")) {
							paddleExpand = true;
							paddle.setEx(true);
							paddleExtime = System.currentTimeMillis();
						}
						if (hitby.equals("Player2")) {
							paddleupExpand = true;
							paddleup.setEx(true);
							paddleupExtime = System.currentTimeMillis();
						}
						if (hitby.equals("Player3")) {
							paddlelExpand = true;
							paddlel.setEx(true);
							paddlelExtime = System.currentTimeMillis();
						}
						if (hitby.equals("Player1")) {
							paddlerExpand = true;
							paddler.setEx(true);
							paddlerExtime = System.currentTimeMillis();
						}
					} else {
						ball2 = new Ball(ball.getX() - ball_width, ball.getY());
						ball2.setVel(ball.getVX(), -ball.getVY());
						ball2_visi = true;
						ball.setVel(-ball.getVX(), ball.getVY());
						balltimer = System.currentTimeMillis();
					}
				}
			} else {

			}
		}
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);
			paddleup.keyReleased(e);
			paddler.keyReleased(e);
			paddlel.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);
			paddleup.keyPressed(e);
			paddler.keyPressed(e);
			paddlel.keyPressed(e);
		}
	}
}
