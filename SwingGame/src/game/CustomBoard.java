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

import multiplayer.active;
import multiplayer.network;
import multiplayer.peer;

public class CustomBoard extends JPanel implements ActionListener {

	public interface func {
		void chutiyapa(String s);

	}

	private Timer timer;
	public static Paddle paddle;
	public static Paddle paddle2;
	//private Com com;
	public static Ball ball;
	private String winner;
	private boolean ingame;
	private final int DELAY = 10;
	private final int BOARD_WIDTH = 400;
	private final int BOARD_HEIGHT = 400;
	active activ;
	String position;
	public CustomBoard(int max ,int peers,active act,String pos) {
this.position=pos;
		initBoard(max,peers);
		activ=act;
	}

	private void initBoard(int max ,int peers) {

		addKeyListener(new TAdapter());

		setFocusable(true);
		setBackground(Color.BLACK);
		
		paddle = new Paddle(peer.my.name);
		paddle2 = new Paddle(network.peermanage.listofpeers.get(0).mydata.name);
//		com = new Com();
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
		if(position.equals("DOWN")){
			paddle.setY(0);
			paddle2.setY(230);
		}else{
			paddle.setY(230);
			paddle2.setY(0);
		}
		g.setColor(Color.RED);
		g.fillRect(paddle.getX(), paddle.getY(), 150, 10);
		g.setColor(Color.RED);
		//g.fillRect(com.getX(), com.getY(), 150, 10);
		
		
		g.fillRect(paddle2.getX(), paddle2.getY(), 150, 10);
		g.setColor(Color.blue);
		g.fillOval(ball.getX(), ball.getY(), 25, 25);
		g.drawString("Lives: " + paddle.getLives(), 5, 345);
		g.drawString("Lives: " + paddle2.getLives(), 5, 15);

	}
int counter=0;
int counters=0;
	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		paddle.move();
		
		
		paddle2.move();
		//updatecom();
		checkCollisions();
		ball.move();
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
	}

	private void checkGame() {
		if (paddle2.getLives() == 0) {
			winner = "Player2";
			ingame = false;

		}
		if (paddle.getLives() == 0) {
			winner = "Player1";
			ingame = false;
			
		}

	}

	private void inGame() {

		if (!ingame) {
			timer.stop();
		}
	}

//	private void updatecom() {
//		if (ball.getVX() > 0) {
//			if (ball.getX() + 25 > com.getX() + 75)
//				com.moveRight();
//		} else {
//			if (ball.getX() + 25 < com.getX() + 75)
//				com.moveLeft();
//		}
//	}

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
		Rectangle r1 = paddle2.getBounds();
		if (r2.intersects(r3)) {
			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velY;
			ball.setVel(velX, temp);
			//network.peermanage.sendtoall("BVel "+" "+velX+" "+temp);
		}
		if (r2.intersects(r1)) {
			int velX = ball.getVX();
			int velY = ball.getVY();
			int temp = -velY;
			ball.setVel(velX, temp);
			//network.peermanage.sendtoall("BVel "+velX+" "+temp);
		}
		if (ball.getX() > BOARD_WIDTH-ball.getBounds().getWidth() || ball.getX() < 0) {
			int temp = -1 * ball.getVX();

			ball.setVel(temp, ball.getVY());
			//network.peermanage.sendtoall("BVel "+temp+" "+ball.getVY());
		}
		if (ball.getY() > BOARD_HEIGHT-2*ball.getBounds().getHeight() || ball.getY() < 0) {
			int temp = -1 * ball.getVY();
			ball.setVel(ball.getVX(), temp);
			//network.peermanage.sendtoall("BVel "+ball.getVX()+" "+temp);
			if (ball.getY() < 0) {
				paddle2.reduLives();
			}
			if (ball.getY() > BOARD_HEIGHT) {
				paddle.reduLives();
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
public void sexy(String line){
	
}
}
