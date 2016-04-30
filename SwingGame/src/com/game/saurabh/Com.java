package com.game.saurabh;

import java.awt.Rectangle;

public class Com {

	private int x;
	private int y;
	private int lives;

	public Rectangle getBounds() {
		return new Rectangle(x, y, 150, 10);
	}

	public Com() {

		initCom();
	}

	private void initCom() {

		x = 150;
		y = 0;
		lives = 3;
	}
	public int getLives() {
    	
        return lives;
    }
    public void reduLives(){
    	lives += -1;
    }

	public void moveLeft() {

		//x -= 1;
		if(x > 40){
			x -= 1;
    	}else{
    		x -= 0;
    	}

	}
	
	public void moveRight() {

		//x += 1;
		if(x < 200){
			x += 1;
    	}else{
    		x += 0;
    	}

	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
