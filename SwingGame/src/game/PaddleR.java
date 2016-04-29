package game;

import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class PaddleR {

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int lives;
	private boolean visible;
	private boolean expanded;

	public PaddleR() {

		initPaddleR();
	}

	private void initPaddleR() {

		x = 595;
		y = 250;
		lives = 3;
		expanded = false;
		visible = true;
	}
	public boolean getvis(){
		return visible ;
	}

	public void move() {

		if (dy != 0) {
			y += dy;
		}

		if (y < 125) {
			y = 125;
		}
		if (!expanded) {
			if (y > 375) {
				y = 375;
			}
		} else {
			if (y > 325) {
				y = 325;
			}
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

		

		if (key == KeyEvent.VK_UP) {
			if (y > 150) {
				dy = -2;
			} else {
				dy = 0;
			}
		}

		if (key == KeyEvent.VK_DOWN) {
			if (!expanded) {
				if (y < 375) {
					dy = 2;
				} else {
					dy = 0;
				}
			} else {
				if (y < 325) {
					dy = 2;
				} else {
					dy = 0;
				}
			}
		}
	}
	public void setNvis(){
		visible = false;
		x = 1000;
		y = 1000;
	}
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

	

		if (key == KeyEvent.VK_UP) {
			//dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			//dy = 0;
		}
	}

	public Rectangle getBounds() {
		if (expanded == false) {
			return new Rectangle(x, y, 5, 150);
		} else {
			return new Rectangle(x, y, 5, 200);
		}

	}
}
