package com.game.saurabh;

import java.awt.Rectangle;

public class Ball {
	
	 private final int BOARD_WIDTH = 365;
	 private final int BOARD_HEIGHT = 345;
	 private final double BALL_SPEED = 2.0;
	 private double vx;
	 private double vy;
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
	    public double getVX() {
	        return vx;
	    }

	    public double getVY() {
	        return vy;
	    }
	    public Rectangle getBounds() {
	        return new Rectangle(x, y, 25, 25);
	    }
	    public void setVel(double velx, double vely){
	    	vx = velx;
	    	vy = vely;
	    }
}
