package game;

import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class PaddleL {

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int lives;
	private boolean visible;
	private boolean expanded;

	public PaddleL() {

		initPaddleL();
	}
	public boolean getvis(){
		return visible ;
	}

	private void initPaddleL() {

		x = 105;
		y = 250;
		lives = 3;
		visible = true;
		expanded = false;
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
	public void setNvis(){
		visible = false;
		y =1000;
		x = 1000;
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

		

		if (key == KeyEvent.VK_W) {
			if (y > 150) {
				dy = -2;
			} else {
				dy = 0;
			}
		}

		if (key == KeyEvent.VK_S) {
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

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		

		if (key == KeyEvent.VK_W) {
			//dy = 0;
		}

		if (key == KeyEvent.VK_S) {
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
