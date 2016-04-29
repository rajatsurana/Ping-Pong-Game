package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import multiplayer.network;

public class Paddle {

	
	private int dx;
    private int dy;
    private int x;
    private int y;
    private int lives;
    private String name;
public Paddle(String name2) {
        
        initPaddle(name2);
    }
    
  

	private void initPaddle(String name2) {
        
        
        x = 150;
        y = 360;
        lives = 100;
        this.name=name2;
    }


    public int getDx() {
		return dx;
	}



	public void setDx(int dx) {
		this.dx = dx;
	}



	public int getDy() {
		return dy;
	}



	public void setDy(int dy) {
		this.dy = dy;
	}



	public void setLives(int lives) {
		this.lives = lives;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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
    public void setX(int x) {
        this.x=x;
    }

    public void setY(int y) {
    	this.y= y;
    }

    
public void keyPressed2(KeyEvent e){
	int key = e.getKeyCode();
	if (key == KeyEvent.VK_A) {
    	if(x > 40){
    		dx = -1;
    	}else{
    		dx = 0;
    	}
    	
    }

    if (key == KeyEvent.VK_D) {
    	if(x < 200){
    		dx = 1;
    	}else{
    		dx = 0;
    	}
    }
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
        return new Rectangle(x, y, 150, 10);
    }
}
