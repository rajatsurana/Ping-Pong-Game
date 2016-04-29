package game;

import java.awt.Rectangle;

public class Com {

	private int x;
	private int y;
	private int lives;
	private boolean expanded;

	public Rectangle getBounds() {
		if (expanded == false) {
			return new Rectangle(x, y, 150, 5);
		} else {
			return new Rectangle(x, y, 200, 5);
		}

	}

	public Com() {

		initCom();
	}

	private void initCom() {

		x = 350;
		y = 75;
		lives = 3;
		expanded = false;
	}
	
	public boolean getex() {
		return expanded;
	}

	public void setEx(boolean t) {
		expanded = t;
	}
	public int getLives() {

		return lives;
	}

	public void reduLives() {
		lives += -1;
	}

	public void moveLeft() {

		// x -= 1;

		if (x > 160) {
			x -= 2;
		} else {
			x -= 0;
		}

	}

	public void moveRight() {

		// x += 1;
		if(!expanded){
			if (x < 390) {
				x += 2;
			} else {
			x += 0;
			}
		}else{
			if (x < 350) {
				x += 2;
			} else {
			x += 0;
			}
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
