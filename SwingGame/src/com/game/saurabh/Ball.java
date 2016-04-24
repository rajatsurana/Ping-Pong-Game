package com.game.saurabh;

import java.awt.Rectangle;

public class Ball {
	
	 private final int BOARD_WIDTH = 373;
	 private final int BOARD_HEIGHT = 245;
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
	        x = 40;
	        y = 60;
	    }

	    public void move() {
	        
	        
	        
	        if (x > BOARD_WIDTH || x < 0){
	        	vx = -vx;
	        	
	        }
	        if(y > BOARD_HEIGHT || y < 0){
	        	vy = -vy;
	        }
	            
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
}
