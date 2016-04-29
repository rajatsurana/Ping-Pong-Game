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

import multiplayer.active;
import multiplayer.network;
import multiplayer.peer;

import com.game.saurabh.Power;

public class CustomBoard extends JPanel implements ActionListener {

	
	public interface func {
		void chutiyapa(String s);

	}
	active activ;
	String position;
	private Timer timer;
	
	public static Paddle paddle;
	public static Paddle paddle2;
	private int ball_width;
	private Ball ball;
	private String winner;
	private Power power;
	private boolean ingame;
	private String hitby;
	// when paddels expanded
	private boolean paddleExpand;
	private long paddleExtime;

	private boolean paddle2Expand;
	private long paddle2Extime;
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

	public CustomBoard(int max ,int peers,active act,String pos) {

		initBoard();
		this.position=pos;
		
		activ=act;
	}

	private void initBoard() {

		addKeyListener(new TAdapter());
		ball_width = 8;
		setFocusable(true);
		setBackground(Color.BLACK);

		paddle = new Paddle(peer.my.name);

		paddle2 = new Paddle(network.peermanage.listofpeers.get(0).mydata.name);
		ball = new Ball();
		ingame = true;
		// pause = true;
		power = new Power();
		// init paddle expand
		paddleExpand = false;
		paddleExtime = 0;

		// pausetime = System.currentTimeMillis();
		paddle2Expand = false;
		paddle2Extime = 0;
		ball2_visi = false;
		hitby = "None";

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
		 * paddle2 player if (paddle2Expand == false) { g.setColor(Color.yellow);
		 * g.fillRect(paddle2.getX(), paddle2.getY(), 150, 5); } else {
		 * g.setColor(Color.magenta); g.fillRect(paddle2.getX(), paddle2.getY(), 200,
		 * 5); }
		 */
		if (ball2_visi) {
			g.setColor(Color.blue);
			g.fillOval(ball2.getX(), ball2.getY(), 8, 8);
		}

		g.setColor(Color.blue);
		g.fillOval(ball.getX(), ball.getY(), 8, 8);

		drawLives(g);
		drawPowers(g);

	}

	private void drawPaddels(Graphics g) {
		if(position.equals("DOWN")){
			paddle.setY(75);
			paddle2.setY(570);
		}else{
			paddle.setY(570);
			paddle2.setY(75);
		}
		if (paddleExpand == false) {
			g.setColor(Color.yellow);
			g.fillRect(paddle.getX(), paddle.getY(), 150, 5);
		} else {
			g.setColor(Color.magenta);
			g.fillRect(paddle.getX(), paddle.getY(), 200, 5);
		}

		if (paddle2Expand == false) {
			g.setColor(Color.yellow);
			g.fillRect(paddle2.getX(), paddle2.getY(), 150, 5);
		} else {
			g.setColor(Color.magenta);
			g.fillRect(paddle2.getX(), paddle2.getY(), 200, 5);
		}
	}

	private void drawLives(Graphics g) {
		Font small = new Font("Helvetica", Font.BOLD, 24);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString("Lives: " + paddle.getLives(), 325, 625);
		g.drawString("Lives: " + paddle2.getLives(), 325, 25);

	}

	private void drawPowers(Graphics g) {
		if (power.getVisible() == true) {
			//System.out.println(power.getId());
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
	int counter=0;
	int counters=0;
	@Override
	public void actionPerformed(ActionEvent e) {

		// if (!pause) {

		inGame();
		paddle.move();
		paddle2.move();
		//updatepaddle2();
		// checkhole();
		checkCollisions();
		updatepower();
		checkpaddle();
		ball.move();
		if (ball2_visi) {
			ball2.move();
		}
		if (counters ==10){
			//network.peermanage.sendtoall("p1 "+paddle.getX()+" "+paddle.getDx());
			counters=0;
		}else if(counters<10){
			counters++;
		}
		if(counter==12){
			//network.peermanage.sendtoall("BXY "+ball.getX()+" "+ball.getY());
			//network.peermanage.sendtoall("BVel "+ball.getVX()+" "+ball.getVY());
			counter=0;
		}else if(counter<12){
			counter++;
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
		if (paddle2.getLives() == 0) {
			
			ingame = false;
			winner = "Player";
			

		}
		if (paddle.getLives() == 0) {
			ingame = false;
			winner = "Player2";

		}
		

	}

	private void inGame() {

		if (!ingame) {
			timer.stop();
		}
	}

	/*private void updatepaddle2() {
		if (ball.getVX() > 0) {
			if (ball.getX() + 25 > paddle2.getX() + 75)
				paddle2.moveRight();
		} else {
			if (ball.getX() + 25 < paddle2.getX() + 75)
				paddle2.moveLeft();
		}
	}*/

	private void drawGameOver(Graphics g) {

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
		
		Rectangle paddle2Rect = paddle2.getBounds();
		Rectangle ballRect = ball.getBounds();
		// Rectangle paddle2Rect = paddle2.getBounds();
		Rectangle powerRect = power.getBounds();
		if (ball2_visi) {
			Rectangle ball2Rect = ball2.getBounds();
			if (ball2Rect.intersects(paddleRect)) {

				if (Math.abs(ball2.getVY() - paddle.getY()) < 4) {
					ball2.setVel(-ball2.getVX(), ball2.getVY());
				} else {
					int velX = ball2.getVX();
					int velY = ball2.getVY();
					int temp = -velY;
					ball2.setVel(velX, temp);
				}

			}
			
			if (ball2Rect.intersects(paddle2Rect)) {

				if (Math.abs(ball2.getVY() - paddle2.getY()) < 4) {
					ball2.setVel(-ball2.getVX(), ball2.getVY());
				} else {
					int velX = ball2.getVX();
					int velY = ball2.getVY();
					int temp = -velY;
					ball2.setVel(velX, temp);
				}

			}
			/*
			 * if (ball2Rect.intersects(paddle2Rect)) {
			 * 
			 * System.out.println("ball " + ball.getY() + " " + paddle2.getY() + " "
			 * + (ball.getY() - paddle2.getY())); int velX = ball2.getVX(); int velY
			 * = ball2.getVY(); int temp = -velY; ball2.setVel(velX, temp);
			 * 
			 * }
			 */
			if (ball2.getX() + ball_width > BOARD_WIDTH_R
					|| ball2.getX() < BOARD_WIDTH_L) {
				int temp = -1 * ball2.getVX();

				ball2.setVel(temp, ball2.getVY());

				

			}
			if (ball2.getY() + ball_width > BOARD_HEIGHT_D
					|| ball2.getY() < BOARD_HEIGHT_U) {
				int temp = -1 * ball2.getVY();
				ball2.setVel(ball2.getVX(), temp);

				if (ball2.getY() < BOARD_HEIGHT_U) {
					if (ball2.getX() < 550 && ball2.getX() > 150) {
						paddle2.reduLives();
					}

				}
				if (ball2.getY() + ball_width > BOARD_HEIGHT_D) {
					
					if (ball2.getX() < 550 && ball2.getX() > 150) {
						//System.out.println("HI");
						paddle.reduLives();
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

			if (Math.abs(ball.getVY() - paddle.getY()) < 4) {
				ball.setVel(-ball.getVX(), ball.getVY());
			} else {
				int velX = ball.getVX();
				int velY = ball.getVY();
				int temp = -velY;
				ball.setVel(velX, temp);
			}

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

			if (Math.abs(ball.getVY() - paddle2.getY()) < 4) {
				ball.setVel(-ball.getVX(), ball.getVY());
			} else {
				int velX = ball.getVX();
				int velY = ball.getVY();
				int temp = -velY;
				ball.setVel(velX, temp);
			}

			hitby = "paddle2";

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

			

		}
		if (ball.getY() + ball_width > BOARD_HEIGHT_D || ball.getY() < BOARD_HEIGHT_U) {
			int temp = -1 * ball.getVY();
			ball.setVel(ball.getVX(), temp);

			if (ball.getY() < BOARD_HEIGHT_U) {
				if (ball.getX() < 550 && ball.getX() > 150) {
					paddle2.reduLives();
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
				//System.out.println("HI");
				if (ball.getX() < 550 && ball.getX() > 150) {
					//System.out.println("HI");
					paddle.reduLives();
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
			if (hitby.equals("Player") || hitby.equals("paddle2")) {
				if (ballRect.intersects(powerRect)) {

					power.setHitted();
					power.setNotVisible();

					int tempid = power.getId();
					if (tempid == 0) {
						if (hitby.equals("Player")) {
							ball = new Ball();
							paddle.reduLives();
						} else if (hitby.equals("paddle2")) {
							ball = new Ball();
							paddle2.reduLives();
						}
						 else {

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
						if (hitby.equals("paddle2")) {
							paddle2Expand = true;
							paddle2.setEx(true);
							paddle2Extime = System.currentTimeMillis();
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
			//if(active.mydata.name.equals(paddle.getName())){
				paddle.keyReleased(e);
				
//			}else{
//				paddle2.keyReleased(e);
//			}
			
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			
			if(position.equals("UP")||position.equals("DOWN")){
				paddle.keyPressed(e);
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					network.peermanage.sendtoall("Left "+paddle.getX()+" "+paddle.getDx()+ " "+paddle.getName());
				}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					network.peermanage.sendtoall("Right "+paddle.getX()+" "+paddle.getDx()+ " "+paddle.getName());
				}
			}
			if(position.equals("UP")||position.equals("DOWN")){
				paddle.keyPressed2(e);
				if(e.getKeyCode()==KeyEvent.VK_A){
					network.peermanage.sendtoall("Left "+paddle.getX()+" "+paddle.getDx()+ " "+paddle.getName());
				}else if(e.getKeyCode()==KeyEvent.VK_D){
					network.peermanage.sendtoall("Right "+paddle.getX()+" "+paddle.getDx()+ " "+paddle.getName());//("R2 "+paddle2.getX()+" "+paddle2.getDx());
				}
			}
//			paddle2.keyPressed2(e);
//			
		}
	}
	
}
