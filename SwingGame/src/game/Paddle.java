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
public Paddle() {
        
        initPaddle();
    }
    
    private void initPaddle() {
        
        
        x = 150;
        y = 260;
        lives = 3;
    }


    public void move() {
    	
    		if(dx != 0){
    			x += dx;
    		}
    	
        
    }
    public int getLives() {
    	
        return lives;
    }
    public void reduLives(){
    	lives += -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	if(x > 40){
        		dx = -1;
        	}else{
        		dx = 0;
        	}
        	
        }

        if (key == KeyEvent.VK_RIGHT) {
        	if(x < 200){
        		dx = 1;
        	}else{
        		dx = 0;
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
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 150, 10);
    }
}
