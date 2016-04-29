package com.game.saurabh;

import java.awt.Rectangle;
import java.util.Random;

public class Power {

	private int x;
	private int y;
	private long starttime;
	private int id;
	private boolean isVisible;
	private boolean isHitted;
	
	public Power(){
		initPower();
		
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	private void initPower(){
		//id = randInt(0,3);
		id = 0;
		x = randInt(200,500);
		y = randInt(175,475);
		isVisible = true;
		isHitted = false;
		starttime = System.currentTimeMillis();
	}
	public int getId(){
		return id;
	}
	public int getX() {
        return x;
    }
	public boolean getVisible(){
		return isVisible;
	}
	public void setNotVisible(){
		isVisible = false;
	}
	public boolean getHitted(){
		return isHitted;
	}
	public void setHitted(){
		isHitted = true;
	}
	public void setNHitted(){
		isHitted = false;
	}

    public int getY() {
        return y;
    }
    public long getStarttime(){
    	return starttime;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 25, 25);
    }
}
