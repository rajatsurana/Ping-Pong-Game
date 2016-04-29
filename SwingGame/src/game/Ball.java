package  game;

import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	private final int BALL_SPEED = 3;
	private int vx;
	private int vy;
	private int x;
	private int y;

	public Ball() {

		initBall();
	}
	public Ball(int x1, int y1){
		
		x= x1;
		y = y1;
	}

	private void initBall() {

		int temp1 = randInt(0, 3);
		int temp2 = randInt(0, 3);
		if (temp1 < 2) {
			vx = BALL_SPEED;
		}else{
			vx = -1*BALL_SPEED;
		}
		if (temp2 < 2) {
			vy = BALL_SPEED+1;
		}else{
			vy = -1*BALL_SPEED-1;
		}
		
		x = randInt(300, 400);

		y = randInt(275, 375);
	}

	public static int randInt(int min, int max) {

		// NOTE: This will (intentionally) not run as written so that folks
		// copy-pasting have to think about how to initialize their
		// Random instance. Initialization of the Random instance is outside
		// the main scope of the question, but some decent options are to have
		// a field that is initialized once and then re-used as needed or to
		// use ThreadLocalRandom (if using at least Java 1.7).
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public void move() {

		x += vx;
		y += vy;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVX() {
		return vx;
	}

	public int getVY() {
		return vy;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 25, 25);
	}

	public void setVel(int velx, int vely) {
		vx = velx;
		vy = vely;
	}
}
