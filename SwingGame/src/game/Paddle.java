package game;

import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Paddle {

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int lives;
	private boolean visible;
	private boolean expanded;

	public Paddle() {

		initPaddle();
	}

	private void initPaddle() {

		x = 350;
		y = 570;
		lives = 3;
		expanded = false;
		visible = true;
	}
	public void setNvis(){
		visible = false;
		x = 1000;
		y = 1000;
	}
	public boolean getvis(){
		return visible ;
	}
	
	public void move() {
		
		if (dx != 0) {
			x += dx;
		}
		
		if (x < 150) {
			x = 150;
		}
		if(!expanded){
			if (x > 400) {
			x = 400;
			}
		}else{
			if(x > 350){
				x = 350;
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

		if (key == KeyEvent.VK_LEFT) {
			if (x > 150) {
				dx = -2;
			} else {
				dx = 0;
			}

		}

		if (key == KeyEvent.VK_RIGHT) {
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

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			//dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			//dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public Rectangle getBounds() {
		if (expanded == false) {
			return new Rectangle(x, y, 150, 5);
		} else {
			return new Rectangle(x, y, 200, 5);
		}

	}
}
