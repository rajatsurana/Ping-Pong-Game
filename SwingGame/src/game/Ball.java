package game;

import java.awt.Rectangle;

public class Ball {
	
	 
	 private final int BALL_SPEED = 2;
	 private int vx;
	 private int vy;
	 private int x;
	 private int y;

	    public Ball() {
	        

	        initBall();
	    }
	    
	    private void initBall() {
	        
	        vx = BALL_SPEED;
	        vy = BALL_SPEED;
	        x = 00;
	        y = 00;
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
	    public void setVel(int velx, int vely){
	    	vx = velx;
	    	vy = vely;
	    }
	    public void setXY(int xx, int yy){
	    	x = xx;
	    	y = yy;
	    }
}
