package game;




import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Paddleup {

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int lives;
	private boolean visible;
	private boolean expanded;

	public Paddleup() {

		initPaddleup();
	}

	private void initPaddleup() {

		x = 350;
		y = 75;
		lives = 3;
		expanded = false;
		visible = true;
	}

	public void move() {

		if (dx != 0) {
			x += dx;
		}

		if (x < 150) {
			x = 150;
		}
		if (x > 400) {
			x = 400;
		}

	}

	public int getLives() {

		return lives;
	}

	public void reduLives() {
		lives += -1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getex() {
		return expanded;
	}

	public void setEx(boolean t) {
		expanded = t;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			if (x > 150) {
				dx = -2;
			} else {
				dx = 0;
			}

		}

		if (key == KeyEvent.VK_D) {
			if (!expanded) {
				if (x < 400) {
					dx = 2;
				} else {
					dx = 0;
				}
			} else {
				if (x < 350) {
					dx = 2;
				} else {
					dx = 0;
				}
			}
		}

		
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			//dx = 0;
		}

		if (key == KeyEvent.VK_D) {
			//dx = 0;
		}

		
	}
	public boolean getvis(){
		return visible ;
	}
	public void setNvis(){
		visible = false;
		x = 1000;
		y = 1000;
	}

	public Rectangle getBounds() {
		if (expanded == false) {
			return new Rectangle(x, y, 150, 5);
		} else {
			return new Rectangle(x, y, 200, 5);
		}

	}
}

