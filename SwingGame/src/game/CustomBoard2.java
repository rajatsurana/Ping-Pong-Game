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

import udp.network;


import com.game.saurabh.Power;

public class CustomBoard2 extends JPanel implements ActionListener {

	private Timer timer;
	public static Paddle paddle;
	public static Paddle paddle3;
	public static Paddle paddle1;
	private int playercount;
	public static Paddle paddle2;
	// private Com com;
	public static Ball ball;
	private int ball_width;
	private String winner;
	private Power power;
	private boolean ingame;
	private String hitby;
	// when paddels expanded
	private boolean paddleExpand;
	private long paddleExtime;
	private boolean paddle3Expand;
	private long paddle3Extime;
	private boolean paddle1Expand;
	private long paddle1Extime;
	private boolean paddle2Expand;
	private long paddle2Extime;
	String peer0pos;
	String peer1pos;
	String peer2pos;
	String mypos;

	String[] peer0posi;
	String[] peer1posi;
	String[] peer2posi;
	String[] myposition;
	// private boolean comExpand;
	// private long comExtime;
	private final int DELAY = 10;
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
	network net; 

	public CustomBoard2() {

		initCustomBoard2();
	}

	public CustomBoard2(int max, int peers,network net ,String pos) {
		this.net = net;
		initCustomBoard2();

	}

	private void initCustomBoard2() {

		addKeyListener(new TAdapter());

		setFocusable(true);
		setBackground(Color.BLACK);
		mypos = net.getName();
		myposition = mypos.split("-");
		peer0pos = network.listofpeers.get(0).connectedto();
		peer0posi = peer0pos.split("-");
		peer1pos = network.listofpeers.get(1).connectedto();
		peer1posi = peer1pos.split("-");

		peer2pos = network.listofpeers.get(2).connectedto();
		peer2posi = peer2pos.split("-");
		// myposition[1] init
		if (myposition[1].equals("DOWN")) {
			paddle = new Paddle(myposition[0]);

		} else if (myposition[1].equals("UP")) {
			paddle2 = new Paddle(myposition[0]);
		} else if (myposition[1].equals("RIGHT")) {
			paddle1 = new Paddle(myposition[0]);
		} else if (myposition[1].equals("LEFT")) {
			paddle3 = new Paddle(myposition[0]);
		}

		// peer0 init
		if (peer0posi[1].equals("DOWN")) {
			paddle = new Paddle(peer0posi[0]);

		} else if (peer0posi[1].equals("UP")) {
			paddle2 = new Paddle(peer0posi[0]);
		} else if (peer0posi[1].equals("RIGHT")) {
			paddle1 = new Paddle(peer0posi[0]);
		} else if (peer0posi[1].equals("LEFT")) {
			paddle3 = new Paddle(peer0posi[0]);
		}

		// peer1 init
		if (peer1posi[1].equals("DOWN")) {
			paddle = new Paddle(peer1posi[0]);

		} else if (peer1posi[1].equals("UP")) {
			paddle2 = new Paddle(peer1posi[0]);
		} else if (peer1posi[1].equals("RIGHT")) {
			paddle1 = new Paddle(peer1posi[0]);
		} else if (peer1posi[1].equals("LEFT")) {
			paddle3 = new Paddle(peer1posi[0]);
		}

		// peer2 init
		if (peer2posi[1].equals("DOWN")) {
			paddle = new Paddle(peer2posi[0]);

		} else if (peer2posi[1].equals("UP")) {
			paddle2 = new Paddle(peer2posi[0]);
		} else if (peer2posi[1].equals("RIGHT")) {
			paddle1 = new Paddle(peer2posi[0]);
		} else if (peer2posi[1].equals("LEFT")) {
			paddle3 = new Paddle(peer2posi[0]);
		}

		// com = new Com();
		ball = new Ball();
		if (myposition[1].equals("UP")) {
			Thread ballSend = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					boolean running = true;
					while (running) {
						net.broadcast("Ballx " + ball.getX()
								+ " " + ball.getY() + " " + ball.getVX() + " "
								+ ball.getVY());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			});
			ballSend.start();
		}
		ball_width = 8;
		ingame = true;
		// pause = true;
		power = new Power();
		power.setNotVisible();
		// init paddle expand
		paddleExpand = false;
		paddleExtime = 0;
		paddle1Expand = false;
		paddle1Extime = 0;
		paddle3Expand = false;
		paddle3Extime = 0;
		paddle2Expand = false;
		paddle2Extime = 0;
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
		if (paddle1.getvis()) {

			int x = 595;
			int y = 250;

			paddle1.setX(x);
			paddle1.setY(y);

			if (paddle1Expand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddle1.getX(), paddle1.getY(), 5, 150);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddle1.getX(), paddle1.getY(), 5, 200);
			}
		}
		if (paddle3.getvis()) {
			int x = 105;
			int y = 250;
			paddle3.setX(x);
			paddle3.setY(y);
			if (paddle3Expand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddle3.getX(), paddle3.getY(), 5, 150);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddle3.getX(), paddle3.getY(), 5, 200);
			}
		}
		if (paddle2.getvis()) {
			int y = 75;
			paddle2.setY(y);
			if (paddle2Expand == false) {
				g.setColor(Color.yellow);
				g.fillRect(paddle2.getX(), paddle2.getY(), 150, 5);
			} else {
				g.setColor(Color.magenta);
				g.fillRect(paddle2.getX(), paddle2.getY(), 200, 5);
			}
		}
	}

	private void drawLives(Graphics g) {
		Font small = new Font("Helvetica", Font.BOLD, 24);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString("Lives: " + paddle.getLives(), 325, 625);
		g.drawString("Lives: " + paddle2.getLives(), 325, 25);
		g.drawString("Lives: " + paddle3.getLives(), 0, 300);
		g.drawString("Lives: " + paddle1.getLives(), 610, 300);
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
		if (paddle1.getvis()) {
			paddle1.move2();
		}
		if (paddle3.getvis()) {
			paddle3.move2();
		}
		if (paddle2.getvis()) {
			paddle2.move();
		}
		// updatecom();
		// checkhole();
		checkCollisions();
		// updatepower();
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
		if (paddle2Expand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddle2Extime) > 45000) {
				paddle2Expand = false;
				paddle2.setEx(false);
			}
		}
		if (paddle1Expand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddle1Extime) > 45000) {
				paddle1Expand = false;
				paddle1.setEx(false);
			}
		}
		if (paddle3Expand == true) {
			long tempt = System.currentTimeMillis();
			if (tempt - (paddle3Extime) > 45000) {
				paddle3Expand = false;
				paddle3.setEx(false);
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
		if (paddle2.getvis()) {
			if (paddle2.getLives() == 0) {

				playercount--;
				// System.out.println("paddle2 " + playercount);
				paddle2.setNvis();
				if (playercount == 1) {
					ingame = false;
				} else if (playercount == 1) {
					if (paddle1.getLives() != 0) {
						winner = "Player1";
					} else if (paddle3.getLives() != 0) {
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
				// System.out.println("paddle " + playercount);
				if (playercount == 1) {
					ingame = false;

					if (paddle1.getLives() != 0) {
						winner = "Player1";
					} else if (paddle3.getLives() != 0) {
						winner = "Player3";
					} else if (paddle2.getLives() != 0) {
						winner = "Player2";
					}
				}

			}
		}
		if (paddle1.getvis()) {
			if (paddle1.getLives() == 0) {
				playercount -= 1;
				paddle1.setNvis();
				// System.out.println("paddle1 " + playercount);
				if (playercount == 1) {
					ingame = false;

					if (paddle.getLives() != 0) {
						winner = "Player";
					} else if (paddle3.getLives() != 0) {
						winner = "Player3";
					} else if (paddle2.getLives() != 0) {
						winner = "Player2";
					}
				}

			}
		}
		if (paddle3.getvis()) {
			if (paddle3.getLives() == 0) {
				playercount -= 1;
				paddle3.setNvis();
				// System.out.println("paddle3 " + playercount);
				if (playercount == 1) {
					ingame = false;

					if (paddle1.getLives() != 0) {
						winner = "Player1";
					} else if (paddle.getLives() != 0) {
						winner = "Player";
					} else if (paddle2.getLives() != 0) {
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

		Rectangle paddle1ect = paddle.getBounds();
		Rectangle paddle3Rect = paddle3.getBounds2();
		Rectangle paddle1Rect = paddle1.getBounds2();
		Rectangle paddle2Rect = paddle2.getBounds();
		Rectangle ballRect = ball.getBounds();
		// Rectangle comRect = com.getBounds();
		Rectangle powerRect = power.getBounds();
		if (ball2_visi) {
			Rectangle ball2Rect = ball2.getBounds();
			if (ball2Rect.intersects(paddle1ect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velY;
				ball2.setVel(velX, temp);

			}
			if (ball2Rect.intersects(paddle3Rect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velX;
				ball2.setVel(temp, velY);

			}
			if (ball2Rect.intersects(paddle1Rect)) {

				int velX = ball2.getVX();
				int velY = ball2.getVY();
				int temp = -velX;
				ball2.setVel(temp, velY);

			}
			if (ball2Rect.intersects(paddle2Rect)) {

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
						if (paddle3.getvis()) {
							paddle3.reduLives();
						}
					}

				}
				if (ball2.getX() + ball_width > BOARD_WIDTH_R) {

					if (ball2.getY() < 525 && ball2.getY() > 125) {

						if (paddle1.getvis()) {
							paddle1.reduLives();
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
						if (paddle2.getvis()) {
							paddle2.reduLives();
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

		if (ballRect.intersects(paddle1ect)) {

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
		if (ballRect.intersects(paddle2Rect)) {

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
		if (ballRect.intersects(paddle3Rect)) {

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
		if (ballRect.intersects(paddle1Rect)) {

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

		if (ball.getX() + ball_width > BOARD_WIDTH_R
				|| ball.getX() < BOARD_WIDTH_L) {
			int temp = -1 * ball.getVX();

			ball.setVel(temp, ball.getVY());

			if (ball.getX() < BOARD_WIDTH_L) {
				if (ball.getY() < 525 && ball.getY() > 125) {
					if (paddle3.getvis()) {
						paddle3.reduLives();
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

					if (paddle1.getvis()) {
						paddle1.reduLives();
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
		if (ball.getY() + ball_width > BOARD_HEIGHT_D
				|| ball.getY() < BOARD_HEIGHT_U) {
			int temp = -1 * ball.getVY();
			ball.setVel(ball.getVX(), temp);

			if (ball.getY() < BOARD_HEIGHT_U) {
				if (ball.getX() < 550 && ball.getX() > 150) {
					if (paddle2.getvis()) {
						paddle2.reduLives();
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
							if (paddle2.getvis()) {
								paddle2.reduLives();
							}
						} else if (hitby.equals("Player1")) {
							ball = new Ball();
							if (paddle1.getvis()) {
								paddle1.reduLives();
							}
						} else if (hitby.equals("Player3")) {
							ball = new Ball();
							if (paddle3.getvis()) {
								paddle3.reduLives();
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
							paddle2Expand = true;
							paddle2.setEx(true);
							paddle2Extime = System.currentTimeMillis();
						}
						if (hitby.equals("Player3")) {
							paddle3Expand = true;
							paddle3.setEx(true);
							paddle3Extime = System.currentTimeMillis();
						}
						if (hitby.equals("Player1")) {
							paddle1Expand = true;
							paddle1.setEx(true);
							paddle1Extime = System.currentTimeMillis();
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
			if (myposition[1].equals("DOWN"))
			paddle.keyReleased(e);
			if (myposition[1].equals("UP"))
			paddle2.keyReleased(e);
			if (myposition[1].equals("RIGHT")) 
			paddle1.keyReleased2(e);
			if (myposition[1].equals("LEFT"))
			paddle3.keyReleased2(e);

		}

		@Override
		public void keyPressed(KeyEvent e) {
		
			
			if (myposition[1].equals("DOWN")) {
				paddle.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					net.broadcast("Left " + paddle.getX() + " "	+ paddle.getDx() + " " + paddle.getName());
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					net.broadcast("Right " + paddle.getX() + " "+ paddle.getDx() + " " + paddle.getName());
				}
			}
			if (myposition[1].equals("UP")) {
				paddle2.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					net.broadcast("Left " + paddle2.getX() + " "
							+ paddle2.getDx() + " " + paddle2.getName());
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					net.broadcast("Right " + paddle2.getX()
							+ " " + paddle2.getDx() + " " + paddle2.getName());
				}
			}
//			paddle1.keyPressed2(e);
//			paddle3.keyPressed2(e);
			if (myposition[1].equals("RIGHT")) {
				paddle1.keyPressed2(e);
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					net.broadcast("UP " + paddle1.getY() + " "
							+ paddle1.getDy() + " " + paddle1.getName());
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					net.broadcast("DOWN " + paddle1.getY() + " "
							+ paddle1.getDy() + " " + paddle1.getName());
				}
			}
			if (myposition[1].equals("LEFT")) {
				paddle3.keyPressed2(e);
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.print("UP");
					net.broadcast("UP " + paddle3.getY() + " "
							+ paddle3.getDy() + " " + paddle3.getName());
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					net.broadcast("DOWN " + paddle3.getY() + " "
							+ paddle3.getDy() + " " + paddle3.getName());
				}
			}
		}
	}
}
